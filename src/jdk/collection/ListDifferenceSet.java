package jdk.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 2016/9/21.
 */
public class ListDifferenceSet {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<String>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("4");
        list1.add("5");
        List<String> list2 = new ArrayList<String>();
        list2.add("3");
        list2.add("4");

        System.out.println(list1.removeAll(list2));

        for (String val : list1){
            System.out.println(val);
        }
    }
}
