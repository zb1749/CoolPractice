package implement;

/**
 * 继承实例创建test
 */
public class ImplementNewTest {
    public static void main(String[] args) throws InterruptedException {
        ImplementA a = new ImplementA();
        ImplementB b = new ImplementB();
        //System.out.println(ImplementA.HI);
        Thread.sleep(10000000000000L);
        //System.out.println(ImplementA.HI);
        a.say();
        b.say();
    }
}
