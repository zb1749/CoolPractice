package test;

import util.StringUtil;

public class SplitArrayTest {
    public static void main(String[] args) {

        String ebookInnerCategoryIds = "590592090;590595090;;;590595290";
        String categoryIdStr = "590595090";

        String[] innerIdArr = ebookInnerCategoryIds.split(";");
        StringBuilder buf = new StringBuilder(innerIdArr.length * 5);
        int move = 0;
        int empty = 0;
        System.out.println(innerIdArr.length);
        for (int i = 0; i < innerIdArr.length; i++) {
            if (StringUtil.isNotNull(innerIdArr[i])) {
                if (!categoryIdStr.equals(innerIdArr[i])) {
                    buf.append(innerIdArr[i]);
                    buf.append(";");
                }
            }
        }
        String result = buf.toString();

        result = result.substring(0, result.length() - 1);

        System.out.println(result);
    }
    //590592090;590595090;
}
