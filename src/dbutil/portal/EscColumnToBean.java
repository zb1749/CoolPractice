package dbutil.portal;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.property.ChainedPropertyAccessor;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.property.PropertyAccessorFactory;
import org.hibernate.transform.ResultTransformer;

/** 
 * 自定义的数据库字库转换成POJO 
 */  
public class EscColumnToBean implements ResultTransformer {  
	private static final Logger logger = Logger.getLogger(EscColumnToBean.class);
    private static final long serialVersionUID = 1L;  
    private final Class<?> resultClass;  
    private PropertyAccessor propertyAccessor;  
      
    public EscColumnToBean(Class<?> resultClass) {  
        if(resultClass==null) throw new IllegalArgumentException("resultClass cannot be null");  
        this.resultClass = resultClass;  
        propertyAccessor = new ChainedPropertyAccessor(new PropertyAccessor[] { PropertyAccessorFactory.getPropertyAccessor(resultClass,null), PropertyAccessorFactory.getPropertyAccessor("field")});        
    }  
  
    //结果转换时，HIBERNATE调用此方法  
    public Object transformTuple(Object[] tuple, String[] aliases) {  
    	Object result;
    	long end = System.currentTimeMillis();
    	Field[] fs = getDeclaredField(this.resultClass);
		Map<String,Field> fieldMap = new HashMap<String,Field>();
		for (Field field : fs) {
			fieldMap.put(field.getName().toUpperCase(), field);
		}
		try{
			result = this.resultClass.newInstance();
			for (int i = 0; i < aliases.length; i++) {
				if(tuple[i]==null)
					continue;
				String rsField = aliases[i];
				if(fieldMap.get(rsField)!=null){
					Field field = fieldMap.get(rsField);
					Type tp = field.getGenericType();
					String fieldName = field.getName().substring(0, 1)
							.toUpperCase()
							+ field.getName().substring(1);
					// 如果以后还有什么其它的 类型，可以在这里补充
					if (tp == String.class) {
						Method m = result.getClass().getMethod(
								"set" + fieldName, String.class);
						m.invoke(result, String.valueOf(tuple[i]));
					} else if (tp == Integer.class) {
						Method m = result.getClass().getMethod(
								"set" + fieldName, Integer.class);
						m.invoke(result, Integer.parseInt(String.valueOf(tuple[i])));
					} else if (tp == int.class) {
						Method m = result.getClass().getMethod(
								"set" + fieldName, int.class);
						m.invoke(result, Integer.parseInt(String.valueOf(tuple[i])));
					} else if (tp == Long.class) {
						Method m = result.getClass().getMethod(
								"set" + fieldName, Long.class);
						m.invoke(result, Long.parseLong(String.valueOf(tuple[i])));
					} else if (tp == long.class) {
						Method m = result.getClass().getMethod(
								"set" + fieldName, long.class);
						m.invoke(result, Long.parseLong(String.valueOf(tuple[i])));
					} else if (tp == Date.class) { // 这里Date是java.util.Date
						Method m = result.getClass().getMethod(
								"set" + fieldName, Date.class);
						m.invoke(result, (Date) tuple[i]); // 使用getObject不会丢失时，分，秒的信息
					} else if (tp == Timestamp.class) { 
						Method m = result.getClass().getMethod(
								"set" + fieldName, Timestamp.class);
						m.invoke(result, (Timestamp) tuple[i]); 
					}  else if (tp == Float.class) {
						Method m = result.getClass().getMethod(
								"set" + Float.parseFloat(String.valueOf(tuple[i])));
						m.invoke(result, (Timestamp) tuple[i]);
					} else if (tp == float.class) {
						Method m = result.getClass().getMethod(
								"set" + fieldName, float.class);
						m.invoke(result, Float.parseFloat(String.valueOf(tuple[i])));
					} else if (tp == Double.class) {
						Method m = result.getClass().getMethod(
								"set" + fieldName, Double.class);
						m.invoke(result, Double.parseDouble(String.valueOf(tuple[i])));
					} else if (tp == double.class) {
						Method m = result.getClass().getMethod(
								"set" + fieldName, double.class);
						m.invoke(result, Double.parseDouble(String.valueOf(tuple[i])));
					} else if (tp == Boolean.class) {
						Method m = result.getClass().getMethod(
								"set" + fieldName, Boolean.class);
						m.invoke(result, Boolean.parseBoolean(String.valueOf(tuple[i])));
					} else if (tp == boolean.class) {
						Method m = result.getClass().getMethod(
								"set" + fieldName, boolean.class);
						m.invoke(result, Boolean.parseBoolean(String.valueOf(tuple[i])));
					} else {
						logger.warn("未识别字段类型【" + tp + "】字段值【"
								+ tuple[i] + "】");
					}
				}
			}
			return result;
		} catch(NoSuchMethodException e){
			logger.error(org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e));
		} catch(InstantiationException e){
			logger.error(org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e));
		} catch(IllegalAccessException e){
			logger.error(org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e));
		} catch(InvocationTargetException e){
			logger.error(org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e));
		}
		fieldMap = null;
		long end2 = System.currentTimeMillis();
		logger.info("convert to List<"+this.resultClass+">  cost["+(end2-end)+"ms]");
		return null;

    }  
  
    @SuppressWarnings({ "rawtypes" })  
    public List transformList(List collection) {  
        return collection;  
    }  
    
    /**
	 * 获取java类定义的公共，默认，保护，私有，父类的所有字段属性
	 * @param clazz
	 * @return
	 */
	private static Field[] getDeclaredField(Class<?> clazz){
		Map<String,String> tmpMap = new HashMap<String,String>();
    	List<Field> tmpList = new ArrayList<Field>();
        for(; clazz != Object.class ; clazz = clazz.getSuperclass()) {  
            try {  
            	Field[] cuField = clazz.getDeclaredFields(); 
                for (Field field2 : cuField) {
                	if(tmpMap.get(field2.getName())==null){
                		tmpMap.put(field2.getName(), field2.getName());
                		tmpList.add(field2);
                	}
				}
            } catch (Exception e) {  
            }   
        }
        Field[] field = tmpList.toArray(new Field[tmpList.size()]);
		tmpMap = null;
		tmpList = null;
        return field;  
    }
  
}  