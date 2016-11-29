package multithread.semaphore.unInterruptible;


/**
 * Created by Kevin on 2016/11/29.
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();
        ThreadA a = new ThreadA(service);
        a.setName("A");
        ThreadB b = new ThreadB(service);
        b.setName("B");
        a.start();
        b.start();

        Thread.sleep(1000L);
        b.interrupt();
        System.out.println("main 中断了 " + b.getName());
    }


}
