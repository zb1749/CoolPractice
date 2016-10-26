package dbutil.queryconfig.nq;

import dbutil.common.IntRange;
import dbutil.queryconfig.jsqlparser.expression.JpqlDataType;
import dbutil.queryconfig.jsqlparser.expression.JpqlParameter;
import dbutil.tools.DateUtils;
import dbutil.tools.StringUtils;
import org.apache.log4j.Logger;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.*;


/**
 * @param <X> 返回结果的参数类型
 */
@SuppressWarnings({"unchecked","hiding"})
public class NativeQuery<X> implements TypedQuery<X>,ParameterProvider {
	private static Logger logger = Logger.getLogger(NativeQuery.class);

	private LockModeType lock=null;
	private FlushModeType flushType=null;
	
	
	private NamedQueryConfig config;				//查询本体
	private IntRange range;							//额外的范围要求
	//实例数据
	private Class<X> resultType;				//返回类型
	private Map<Object,Object> nameParams=new HashMap<Object,Object>();//按名参数
	/**
	 * 从SQL语句构造
	 * @param sql2
	 * @throws SQLException 
	 */
	public NativeQuery( String sql2) throws SQLException {
		this(sql2,null);
	}
	/**
	 * 从SQL语句加上返回类型构造
	 * @param sql
	 * @param resultClass
	 */
	public NativeQuery(String sql,Class<X> resultClass) throws SQLException{
		this.resultType=resultClass;
		if(StringUtils.isEmpty(sql)){
			throw new IllegalArgumentException("Please don't input an empty SQL.");
		}
		this.config=new NamedQueryConfig("", sql,null,0);
	}
	/**
	 * 从一个已有的解析后的命名查询构造
	 * @param namedQueryConfig
	 */
	public NativeQuery(NamedQueryConfig namedQueryConfig,Class<X> resultClass) {
		this.config=namedQueryConfig;
		this.resultType=resultClass;
	}


	private int fetchSize=0;
	
	/**
	 * 返回fetchSize
	 * @return 每次游标获取的缓存大小
	 */
	public int getFetchSize() {
		if(fetchSize>0)return fetchSize;
		return config.getFetchSize();
	}
	
	/**
	 * 设置fetchSize
	 * @param size 设置每次获取的缓冲大小
	 */
	public void setFetchSize(int size){
		this.fetchSize=size;
	}
	
	
  
	/**
	 * 设置查询结果的条数限制，即分页 
	 * 包含了setMaxResult和setFirstResult的功能
	 * @param range
	 */
	public void setRange(IntRange range) {
		this.range = range;
	}
	


	
	/**
	 * 限制返回的最大结果数
	 */
	public TypedQuery<X> setMaxResults(int maxResult) {
		if(range==null){
			range=new IntRange(1,maxResult);	
		}else{
			range=new IntRange(range.getStart(),range.getStart()+maxResult-1);
		}
		return this;
	}
	


	/**
	 * 获取当前设置的最大结果设置
	 */
	public int getMaxResults() {
		if(range!=null)return range.getGreatestValue();
		return 0;
	}

	/**
	 * 设置结果的开始偏移。
	 */
	public TypedQuery<X> setFirstResult(int startPosition) {
		if(range==null){
			range=new IntRange(startPosition+1,Integer.MAX_VALUE);	
		}else{
			range=new IntRange(startPosition+1,range.size()+startPosition);
		}
		return this;
	}

	/**
	 * 得到目前的开始偏移 
	 */
	public int getFirstResult() {
		if(range==null)return 0;
		return range.getStart()-1;
	}
	
	/**
	 * 目前不支持的JPA方法
	 * 抛出异常
	 * @deprecated
	 */
	public TypedQuery<X> setHint(String hintName, Object value) {
		throw new UnsupportedOperationException();
	}
	/**
	 * 目前不支持的JPA方法
	 * 抛出异常
	 * @deprecated
	 */
	public Map<String, Object> getHints() {
		throw new UnsupportedOperationException();
	}
	/**
	 * 目前不支持的JPA方法
	 * 抛出异常
	 * @deprecated
	 */
	public <X> X unwrap(Class<X> cls) {
		throw new UnsupportedOperationException();
	}
	/**
	 * 设置查询的绑定变量参数
	 */
	public <T> TypedQuery<X> setParameter(Parameter<T> param, T value) {
		if(param.getPosition()!=null){
			setParameter(param.getPosition(), value);
		}else if(StringUtils.isNotEmpty(param.getName())){
			setParameter(param.getName(), value);
		}
		return this;
	}
	/**
	 * 设置查询的绑定变量参数
	 */
	public TypedQuery<X> setParameter(Parameter<Calendar> param, Calendar value, TemporalType temporalType) {
		setParameter(param,value);
		return this;
	}
	/**
	 * 设置查询的绑定变量参数
	 */
	public TypedQuery<X> setParameter(Parameter<Date> param, Date value, TemporalType temporalType) {
		setParameter(param,value);
		return this;
	}
	/**
	 * 设置查询的绑定变量参数
	 */
	public TypedQuery<X> setParameter(String name, Calendar value, TemporalType temporalType) {
		return setParameter(name,value);
	}
	/**
	 * 设置查询的绑定变量参数
	 */
	public TypedQuery<X> setParameter(String name, Date value, TemporalType temporalType) {
		return setParameter(name,value);
	}
	/**
	 * 设置查询的绑定变量参数
	 */
	public TypedQuery<X> setParameter(String name, Object value) {
		if(StringUtils.isNotEmpty(name)){
			JpqlParameter p=config.getParams().get(name);
			if(p==null){
				logger.error("the parameter [" + name +"] doesn't exist in the query:"+config.getName());
				throw new RuntimeException("the parameter [" + name +"] doesn't exist in the query:"+config.getName());
			}
			value=processValue(p,value);
			this.nameParams.put(name, value);
		}
		return this;
	}
	/**
	 * 设置查询的绑定变量参数
	 */
	public TypedQuery<X> setParameter(int position, Object value) {
		JpqlParameter p=config.getParams().get(Integer.valueOf(position));
		if(p==null){
			logger.error("the parameter [" + position +"] doesn't exist in the query:"+config.getName());
			throw new RuntimeException("the parameter [" + position +"] doesn't exist in the named query:"+config.getName());
		}
		value=processValue(p,value);
		nameParams.put(Integer.valueOf(position), value);
		return this;
	}
	/*
	 * 
	 */
	private Object processValue(JpqlParameter p, Object value) {
		if(value==null)
			return null;
		if(value instanceof String){
			if(p.getType()!=null){
				value = toProperType(p.getType(), (String)value);
			}
		}else if((value instanceof Date)){
			Class<?> clz=value.getClass();
			if (clz == java.sql.Time.class || clz == java.sql.Timestamp.class) {
				return value;
			}else if(p.getType()==JpqlDataType.TIMESTAMP){
				value=new java.sql.Timestamp(((Date) value).getTime());
			}else{
				value=new java.sql.Date(((Date) value).getTime());
			}
		} else if(value.getClass().isArray()){
			//if(p.getType()!=null){
				value = toProperType(JpqlDataType.ARRAY, (Object[])value);
			//}
		}
		return value;
	}
	/**
	 * 设置参数的值，传入的参数类型为String，
	 * @param name
	 * @param value
	 * @return
	 */
	public TypedQuery<X> setParameterByString(String name, String value) {
		if(StringUtils.isNotEmpty(name)){
			JpqlParameter p=config.getParams().get(name);
			if(p==null){
				logger.error("the parameter [" + name +"] doesn't exist in the query:"+config.getName());
				throw new RuntimeException("the parameter [" + name +"] doesn't exist in the query:"+config.getName());
			}
			Object v=value;
			if(p.getType()!=null){
				v=toProperType(p.getType(),value);
			}
			this.nameParams.put(name, v);
		}
		return this;
	}
	/**
	 * 设置参数的值，传入的参数类型为String[]
	 * @param name
	 * @param value
	 * @return
	 */
	public TypedQuery<X> setParameterByString(String name, String[] value) {
		if(StringUtils.isNotEmpty(name)){
			JpqlParameter p=config.getParams().get(name);
			if(p==null){
				logger.error("the parameter [" + name +"] doesn't exist in the query:"+config.getName());
				throw new RuntimeException("the parameter [" + name +"] doesn't exist in the query:"+config.getName());
			}
			Object v=value;
			if(p.getType()!=null){
				v=toProperType(p.getType(),value);
			}
			this.nameParams.put(name, v);
		}
		return this;
	}
	/**
	 * 设置参数的值，传入的参数类型为String，
	 * @param position
	 * @param value
	 * @return
	 */
	public TypedQuery<X> setParameterByString(int position, String value) {
		JpqlParameter p=config.getParams().get(position);
		if(p==null){
			logger.error("the parameter [" + position +"] doesn't exist in the query:"+config.getName());
			throw new RuntimeException("the parameter [" + position +"] doesn't exist in the query:"+config.getName());
		}
		Object v=value;
		if(p.getType()!=null){
			v=toProperType(p.getType(),value);
		}
		nameParams.put(Integer.valueOf(position), v);
		return this;
	}
	/**
	 * 设置参数的值，传入的参数类型为String[]
	 * @param position
	 * @param value
	 * @return
	 */
	public TypedQuery<X> setParameterByString(int position, String[] value) {
		JpqlParameter p=config.getParams().get(position);
		if(p==null){
			logger.error("the parameter [" + position +"] doesn't exist in the query:"+config.getName());
			throw new RuntimeException("the parameter [" + position +"] doesn't exist in the query:"+config.getName());
		}
		Object v=value;
		if(p.getType()!=null){
			v=toProperType(p.getType(),value);
		}
		nameParams.put(Integer.valueOf(position), v);
		return this;
	}
	/*
	 * 将参数按照命名查询中的类型提示转换为合适的类型
	 */
	private Object toProperType(JpqlDataType type, Object[] value) {

		Object[] result=new Object[value.length];
		for(int i=0;i<value.length;i++){
			result[i]=toProperType(type, String.valueOf(value[i]));
		}
		return result;
	}
	/*
	 * 转换String为合适的参数类型
	 * @param type
	 * @param value
	 * @return
	 */
	private Object toProperType(JpqlDataType type, String value) {
		switch(type){
		case DATE:
			return DateUtils.toSqlDate(DateUtils.autoParse(value));
		case BOOLEAN:
			return StringUtils.toBoolean(value, null);
		case DOUBLE:
			return StringUtils.toDouble(value, 0.0);
		case FLOAT:
			return StringUtils.toFloat(value, 0.0f);
		case INT:
			return StringUtils.toInt(value, 0);
		case LONG:
			return StringUtils.toLong(value, 0L);
		case SHORT:
			return (short)StringUtils.toInt(value, 0);
		case TIMESTAMP:
			return DateUtils.toSqlTimeStamp(DateUtils.autoParse(value));
		case $STRING:
			return "%".concat(value);
		case STRING$:
			return value.concat("%");
		case $STRING$:
			StringBuilder sb=new StringBuilder(value.length()+2);
			return sb.append('%').append(value).append('%').toString();	
		case SQL:
			return value;
		default:
			return value;
		}
	}
	/**
	 * 设置参数的值
	 */
	public TypedQuery<X> setParameter(int position, Calendar value, TemporalType temporalType) {
		return setParameter(position, value);
	}
	/**
	 * 以Map形式设置参数的值
	 * @param params
	 * @return
	 */
	public TypedQuery<X> setParameterMap(Map<String,Object> params) {
		if(params==null)return this;
		for(String key: params.keySet()){
			setParameter(key, params.get(key));
		}
		return this;
	}
	/**
	 * 设置参数的值
	 */
	public TypedQuery<X> setParameter(int position, Date value, TemporalType temporalType) {
		return setParameter(position, value);
	}
	
	/**
	 * 设置动态片段
	 * <p>
	 * 注：当在where条件中使用动态SQL片段时，需要在第一时间调用该方法进行设置，即要在调用任一setParameter方法之前调用该方法
	 * </p>
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public TypedQuery<X> setFragment(String name, String value) {
		config.applyFragment(name, value);
		return this;
	}

	/**
	 * 不支持的特性，抛出异常
	 * @deprecated
	 */
	public Set<Parameter<?>> getParameters() {
		throw new UnsupportedOperationException();
	}
	/**
	 * 不支持的特性，抛出异常
	 * @deprecated
	 */
	public Parameter<?> getParameter(String name) {
		throw new UnsupportedOperationException();
	}
	/**
	 * 不支持的特性，抛出异常
	 * @deprecated
	 */
	public <X> Parameter<X> getParameter(String name, Class<X> type) {
		throw new UnsupportedOperationException();
	}
	/**
	 * 不支持的特性，抛出异常
	 * @deprecated
	 */
	public Parameter<?> getParameter(int position) {
		throw new UnsupportedOperationException();
	}
	/**
	 * 不支持的特性，抛出异常
	 * @deprecated
	 */
	public <X> Parameter<X> getParameter(int position, Class<X> type) {
		throw new UnsupportedOperationException();
	}
	/**
	 * JPA接口，目前相关特性未实现，总是返回false
	 */
	public boolean isBound(Parameter<?> param) {
		return false;
	}
	/**
	 * 得到参数的值
	 */
	public Object getParameterValue(String name) {
		return nameParams.get(name);
	}
	/**
	 * 得到参数的值
	 */
	public Object getParameterValue(int position) {
		return nameParams.get(position);
	}
	/**
	 * 得到参数的值
	 */
	public <T> T getParameterValue(Parameter<T> param) {
		if(param.getPosition()!=null && param.getPosition()>-1){
			return (T)getParameterValue(param.getPosition());
		}else{
			return (T)getParameterValue(param.getName());
		}
	}	
	/**
	 * 设置FlushType
	 * 目前JEF未实现相关特性，该方法可以调用，但对数据库操作无实际影响
	 */
	public TypedQuery<X> setFlushMode(FlushModeType flushMode) {
		this.flushType=flushMode;
		return this;
	}

	/**
	 * 返回FlushMode
	 * 目前JEF未实现相关特性，该方法可以调用，但对数据库操作无实际影响
	 */
	public FlushModeType getFlushMode() {
		return flushType;
	}
	
	/**
	 * 设置lockMode
	 * 目前JEF未实现相关特性，该方法可以调用，但对数据库操作无实际影响
	 */
	public TypedQuery<X> setLockMode(LockModeType lockMode) {
		this.lock=lockMode;
		return this;
	}

	/**
	 * 返回LockMode
	 * 目前JEF未实现相关特性，该方法可以调用，但对数据库操作无实际影响
	 */
	public LockModeType getLockMode() {
		return lock;
	}
	/**
	 * 返回结果的类型
	 * @return
	 */
	public Class<X> getResultType() {
		return resultType;
	}
	/**
	 * 设置是否为Native查询，
	 * SQL即为Native,JPQL则不是
	 * @param isNative
	 */
	public void setIsNative(boolean isNative) {
		this.config.setType(isNative?NamedQueryConfig.TYPE_SQL:NamedQueryConfig.TYPE_JPQL);
	}
	/**
	 * 对于以名称为key的参数，获取其参数值
	 */
	public Object getNamedParam(String name) {
		if(this.nameParams==null)return null;
		return nameParams.get(name);
	}
	/**
	 * 对于以序号排列的参数，获取其第index个参数的值
	 */
	public Object getIndexedParam(int index) {
		if(this.nameParams==null)return null;
		return nameParams.get(index);
	}
	/**
	 * 对于命名查询，获取其tag
	 * @return
	 */
	public String getTag(){
		return config.getTag();
	}
	
	public NamedQueryConfig getConfig(){
		return this.config;
	}

	/**
	 * 查询是否包含指定的参数
	 */
	public boolean containsParam(Object key) {
		return nameParams.containsKey(key);
	}
	public int executeUpdate() {
		return 0;
	}
	public List<X> getResultList() {
		return null;
	}
	public X getSingleResult() {
		return null;
	}
}

