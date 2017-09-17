package test;

import java.util.HashMap;
import java.util.Map;

public class MapContentTest {
    public static void main(String[] args) {
        String key = "1";
        Map<Integer, Integer> position = new HashMap<>();
        System.out.printf("position 1 value: " + position.get(1));
        System.out.printf("position key 1 value: " + position.get(key));
    }
}
