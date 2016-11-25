package jdk.collection.traversal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 每5个长度为一个周期，分批遍历显示list
 * <p>
 * Created by Kevin on 2016/11/9.
 */
public class ListPeriodTraversal {
    public static void main(String[] args) {
        String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        List<String> primeList = Arrays.asList(arr);
        System.out.println("prime list: " + primeList.toString());
        List<String> periodList = new ArrayList<String>();
        int count = 0;
        for (String val : primeList) {
            periodList.add(val);
            count++;
            if (count % 5 == 0) {
                //每个周期遍历显示一次
                System.out.println("call traverseShow");
                traverseShow(periodList);
                periodList = new ArrayList<String>();
            }
        }
        //遍历显示最后剩余的
        traverseShow(periodList);
    }

    private static void traverseShow(List<String> list) {
        if (list != null && !list.isEmpty()) {
            for (String it : list) {
                System.out.println(it);
            }
        }
    }

}
