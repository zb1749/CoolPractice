package jdk.type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 限制泛型+通配泛型
 */
public class WildcardType {
    public static void main(String[] args) {
        Map<String, Class<? extends Number>> map = new HashMap<String, Class<? extends Number>>();
        map.put("Integer", Integer.class);
        map.put("Double", Double.class);

        for (Map.Entry<String, Class<? extends Number>> entry : map.entrySet()) {
            System.out.println("key：" + entry.getKey() + " value：" + entry.getValue());
        }

        Map<String, List<? extends Number>> mapL = new HashMap<String, List<? extends Number>>();
        mapL.put("list", new ArrayList<Integer>());
        mapL.put("list", new ArrayList<Float>());
    }
}
