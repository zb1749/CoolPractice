package jdk.type;

public class TypeMethod {
    /**
     * 泛型方法
     */
    public <T> T getValue(Object s,Class<T> clazz) {
        System.out.println(clazz.getName());
        T t =null;
        if(clazz.getName().equals("java.lang.Integer")){
            Double d = Double.parseDouble(s.toString());
            int i =d.intValue();
            t=(T)new Integer(i);
        }
        if(clazz.getName().equals("java.lang.Double")){
            t=(T)new Double(s.toString());
        }
        return t;
    }
}
