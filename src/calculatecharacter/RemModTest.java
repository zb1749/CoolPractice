package calculatecharacter;

public class RemModTest {
    public static void main(String[] args) {
        System.out.println("rem表示取余，mod表示取模：");
        System.out.println("9 rem 2 = " + 9 % 2);
        System.out.println("9 mod 2 = " + Math.floorMod(9, 2));
        System.out.println();
        System.out.println("-9 rem -2 = " + -9 % -2);
        System.out.println("-9 mod -2 = " + Math.floorMod(-9, -2));
        System.out.println();
        System.out.println("-9 rem 2 = " + -9 % 2);
        System.out.println("-9 mod 2 = " + Math.floorMod(-9, 2));
        System.out.println();
        System.out.println("9 rem -2 = " + 9 % -2);
        System.out.println("9 mod -2 = " + Math.floorMod(9, -2));
    }
}
