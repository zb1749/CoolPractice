package problem.letterAndstringConvert;

/**
 * Created by Kevin on 2016/12/8.
 */
public class LetterToString {


    public static void main(String[] args) {
        String i1 = "ABC";
        String i2 = "123";
        String i3 = "abc";

        System.out.println(letterToNum(i1));
        System.out.println(numToLetterUpper(i2));
        System.out.println(letterToNum(i3));
        System.out.println(t1(i3));
    }

    // 将字母转换成数字_1
    public static String t1(String input) {
        String reg = "[a-zA-Z]";
        StringBuffer strBuf = new StringBuffer();
        input = input.toLowerCase();
        if (null != input && !"".equals(input)) {
            for (char c : input.toCharArray()) {
                if (String.valueOf(c).matches(reg)) {
                    strBuf.append(c - 96);
                } else {
                    strBuf.append(c);
                }
            }
            return strBuf.toString();
        } else {
            return input;
        }
    }

    // 将字母转换成数字
    public static String letterToNum(String input) {
        StringBuffer strBuf = new StringBuffer();
        for (byte b : input.getBytes()) {
            strBuf.append((char) (b - 96));
        }
        return strBuf.toString();
    }

    // 将数字转换成字母
    public static void numToLetter(String input) {
        for (byte b : input.getBytes()) {
            System.out.println(b);
            System.out.println((char) (b + 48));
        }
    }

    public static String numToLetterUpper(String input) {
        StringBuffer strBuf = new StringBuffer();
        for (byte b : input.getBytes()) {
            strBuf.append((char) (b + 16));
        }
        return strBuf.toString();
    }

}
