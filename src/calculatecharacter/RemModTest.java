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
        System.out.println();
        //前面那个例子看不出问题，这个才是关键
        //展现了取模和取余的本质区别
        System.out.println("17 rem 3 = " + 17 % 3);
        System.out.println("17 mod 3 = " + Math.floorMod(17, 3));
        System.out.println();
        System.out.println("-17 rem 3 = " + -17 % 3);
        System.out.println("-17 mod 3 = " + Math.floorMod(-17, 3));
        System.out.println();
        System.out.println("17 rem -3 = " + 17 % -3);
        System.out.println("17 mod -3 = " + Math.floorMod(17, -3));
        //这个例子更接近本质=。=
        System.out.println("4 rem -3 = " + 4 % -3);
        System.out.println("4 mod -3 = " + Math.floorMod(4, -3));
        System.out.println("-4 rem 3 = " + -4 % 3);
        System.out.println("-4 mod 3 = " + Math.floorMod(-4, 3));

    }
    /**
     rem表示取余，mod表示取模：
     9 rem 2 = 1
     9 mod 2 = 1

     -9 rem -2 = -1
     -9 mod -2 = -1

     -9 rem 2 = -1
     -9 mod 2 = 1

     9 rem -2 = 1
     9 mod -2 = -1

     17 rem 3 = 2
     17 mod 3 = 2

     -17 rem 3 = -2
     -17 mod 3 = 1

     17 rem -3 = 2
     17 mod -3 = -1
     */
}
