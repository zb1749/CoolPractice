package test;

import java.util.Arrays;
import java.util.List;

public class StringAsListTest {
    public static void main(String[] args) {
        String[] arr = {"123", "456", "789"};
        long lon = 456L;
        List<String> list = Arrays.asList(arr);
        System.out.println("list new: " + list.toString());

        //
        String ebookInnerCategoryIds = "123456";
        String[] innerIdArr = ebookInnerCategoryIds.split(";");
        System.out.println(innerIdArr[0]);

        System.out.println(innerIdArr.length);
    }
}
