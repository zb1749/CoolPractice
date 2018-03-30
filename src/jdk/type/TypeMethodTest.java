package jdk.type;

public class TypeMethodTest {
    public static void main(String[] args) {
        TypeMethod t = new TypeMethod();
        int i = t.getValue("30.0011", Integer.class);
        System.out.println(i);
        double d = t.getValue("40.0022", Double.class);
        System.out.println(d);
    }
}
