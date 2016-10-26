package dbutil.portal;

import dbutil.common.Entry;
import dbutil.queryconfig.jsqlparser.expression.Expression;
import dbutil.queryconfig.jsqlparser.parser.JpqlParser;
import dbutil.queryconfig.jsqlparser.parser.ParseException;
import dbutil.queryconfig.jsqlparser.schema.Column;
import dbutil.queryconfig.jsqlparser.statement.update.Update;
import dbutil.queryconfig.nq.NamedQueryConfig;
import dbutil.queryconfig.nq.NativeQuery;
import org.apache.log4j.Logger;

import java.io.StringReader;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLPretreatment {

	private static final Logger logger = Logger.getLogger(SQLPretreatment.class);
	private static Pattern pat = Pattern.compile(":[a-zA-Z0-9_]*<SQL>");
	public static final String defaultDataSourceName = "sessionFactory";
	
	/**
	 * SQL预处理并解析
	 *
	 * @param nqName
	 * @param param
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Entry<String,List<Object>> createEntry(String nqName,Map<String,Object> param) throws SQLException{
		//从系统中取出SQL模板
		NamedQueryConfig nqcTemplate = NamedQueryConfig.getBySys(nqName);
		//这里调整了preHandleSubSql和preHandleUpdate的执行次序，原来先预处理UPDATE语句，再预处理子SQL语句，导致update语句不能带<SQL>标签
		//预处理子SQL的语句，将带<SQL>的字段替换成参数中的值
		Map<String,Object> nqcMap = preHandleSubSql(nqcTemplate,param);
		//预处理UPDATE语句，对于要更新的字段，如果入参不存在，则删除该字段
		nqcTemplate = preHandleUpdate((NamedQueryConfig)nqcMap.get("nqc"),param);
		Map<String,Object> newParam = (Map<String,Object>)nqcMap.get("newParam");
		NativeQuery<Entry> nQuery = nqcTemplate.createNativeQuery(Entry.class);
		nQuery.setParameterMap(newParam);
		if(logger.isInfoEnabled()){
			if(newParam!=null){
				StringBuilder sb = new StringBuilder("execute sql["+nqName+"] parameters: [");
			    for (Iterator<String> i = newParam.keySet().iterator(); i.hasNext();) {
			        Object obj = i.next();
			        if(newParam.get(obj)==null)
			        	sb.append(obj+"=null;");
			        else if(newParam.get(obj).getClass().isArray())
			        	sb.append(obj+"="+Arrays.toString((Object[])newParam.get(obj))+";");
			        else
			        	sb.append(obj+"="+newParam.get(obj)+";");
			    }
			    sb.append("]");
			    logger.info(sb.toString());
			}
		}
		Entry<String,List<Object>> parse = nqcTemplate.getSqlAndParams(nQuery);
		return parse;
	}
	
	/**
	 * 预处理子SQL的语句，将带<SQL>的字段替换成参数中的值
	 * @param nqc
	 * @param param
	 * @return
	 */
	private static Map<String,Object> preHandleSubSql(NamedQueryConfig nqc, Map<String,Object> param){
		Map<String,Object> preResult = new HashMap<String,Object>();
		String sql = nqc.getRawsql();
		String tagName = nqc.getTag();
		String sqlName = nqc.getName();
		Map<String,Object> newParam = param;
		//预处理带<SQL>的参数，然后删除参数Map中的key，这样SQL解析器就不需要对该变量进行替换了。
		if(param!=null&&sql.contains("<SQL>")){
			//由于下面会修改sql的模板，所以需要实例化一个新的NamedQueryConfig，防止模板被修改
			nqc = new NamedQueryConfig("",sql,"",1);
			//要把原来的sql对应的数据源引用过来
			nqc.setTag(tagName);
			nqc.setName(sqlName);
			newParam = new HashMap<String,Object>(param);
			for (Iterator iterator = param.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				Object value = (Object)param.get(key);
				String newKey = ":"+key+"<SQL>";
				if(sql.contains(newKey)){
					sql = sql.replaceAll(":"+key+"<SQL>", (String)value);
					newParam.remove(key);
				}
			}
			//如果<SQL>参数没有赋值，则需要将其删除，此时，可能影响SQL语句的上下衔接，需要开发人员自己设定该参数的前后关系
			if(sql.contains("<SQL>")){
				Matcher matcher = pat.matcher(sql);
				while (matcher.find()) {
					String temp = sql.substring(matcher.start(), matcher.end());
					sql = sql.replaceAll(temp, "");
					//替换后，需要重新匹配
					matcher = pat.matcher(sql);
				}
			}
			nqc.setRawsql(sql);
			nqc.setResovledSql(sql);
		}
		preResult.put("nqc", nqc);
		preResult.put("newParam", newParam);
		return preResult;
	}
	
	/**
	 * 预处理UPDATE语句，对于要更新的字段，如果入参不存在，则删除该字段
	 * @param nqc
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	private static NamedQueryConfig preHandleUpdate(NamedQueryConfig nqc,Map<String,Object> params) throws SQLException{
		String tempSql = new String(nqc.getRawsql()).toUpperCase();
		String tagName = nqc.getTag();
		String sqlName = nqc.getName();
		if(params!=null&&tempSql.startsWith("UPDATE ")){
			try{
				nqc = new NamedQueryConfig("",nqc.getRawsql(),"",1);
				//要把原来的sql对应的数据源引用过来
				nqc.setTag(tagName);
				nqc.setName(sqlName);
				JpqlParser parser = new JpqlParser(new StringReader(nqc.getRawsql()));
				Update update = (Update)  parser.Statement();
				List<Column> c = update.getColumns();
				int i=0;
				List<Column> newC = new ArrayList<Column>();
				List<Expression> newE = new ArrayList<Expression>();
				for (Expression ex : update.getExpressions()) {
					if(!ex.toString().startsWith(":")){
						newC.add(c.get(i));
						newE.add(ex);
					}else if(ex.toString().startsWith(":")&&params.containsKey(ex.toString().substring(1))){
						newC.add(c.get(i));
						newE.add(ex);
					}
					i++;
		        }
				update.setColumns(newC);
				update.setExpressions(newE);
				nqc.setRawsql(update.toString());
				nqc.setResovledSql(update.toString());
				return nqc;
			}catch(ParseException pe){
				logger.error(pe);
				throw new SQLException("preHandleUpdate SQL["+nqc.getRawsql()+"] error.\r\n"+pe.getMessage());
			}
		}else
			return nqc;
	}

}
