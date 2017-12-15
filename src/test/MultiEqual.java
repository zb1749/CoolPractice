package test;

public class MultiEqual {
    public static void main(String[] args) {
        String a = "1";
        String b = "2";
        String c = "3";
        a = b = c;
        System.out.println("a "+ a);
        System.out.println("b "+ b);
        System.out.println("c "+ c);
    }
}
