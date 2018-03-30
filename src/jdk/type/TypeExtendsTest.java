package jdk.type;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 泛型的上限extends和下限super很奇葩啊
 *
 */
public class TypeExtendsTest {
    public static void main(String[] args) {
        //super
        List<? super Integer> intSuperList = new ArrayList<>();
        intSuperList.add(1);
        intSuperList.add(new Integer(2));
        //intExtendList.add(1.2);
        //intExtendList.add(new Object());
        Integer a = (Integer) intSuperList.get(1);

        System.out.println(intSuperList);

        List<? super WeakExtend> super2List = new ArrayList<>();
        //super2List.add(new WeakReference(""));
        super2List.add(new WeakExtend(""));
        //super2List.add(new Reference(""));

        //List<? extends T> 无法调用 List的原生方法，因为类型不匹配 TODO
        List<? extends Integer> mapExtendList = new ArrayList<>();
       // mapExtendList.add(new HashMap());
       // Map aMap = new HashMap();
        //mapExtendList.add(aMap);

        //用反射可以绕过编译错误检查，但是运行起来依然报错=。=
        /**
        try {
            Method method = mapExtendList.getClass().getMethod("add", Integer.class);
            method.invoke(mapExtendList,5);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        */

        System.out.println(mapExtendList);

    }
    /**
     java.lang.NoSuchMethodException: java.util.ArrayList.add(java.lang.Integer)
     [1, 2]
     at java.lang.Class.getMethod(Class.java:1786)
     []
     at jdk.type.TypeExtendsTest.main(TypeExtendsTest.java:28)
     */
}
