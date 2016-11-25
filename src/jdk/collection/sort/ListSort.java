package jdk.collection.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 2016/11/6.
 */
public class ListSort {
    public static void main(String[] args) {
        List l = new ArrayList();
        l.add(2);
        l.add(1);
        l.add(8);
        System.out.println(l.toString());
        //l.sort((o1,o2)->(Integer.valueOf(o1)-Integer.valueOf(o2)));

    }
}
