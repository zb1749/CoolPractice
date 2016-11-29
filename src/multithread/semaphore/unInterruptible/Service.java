package multithread.semaphore.unInterruptible;

import java.util.concurrent.Semaphore;

/**
 * Created by Kevin on 2016/11/29.
 */
public class Service {
    private Semaphore semaphore = new Semaphore(2);//中的数量限制同时允许运行的并发线程数

    /**
     * 控制同时运行的acquire和release之间的代码的线程数量
     */
    public void testMethod() {
        semaphore.acquireUninterruptibly();
        System.out.println(Thread.currentThread().getName() + " begin time=" + System.currentTimeMillis());
        for (int i = 0; i < Integer.MAX_VALUE / 50; i++) {
            String newString = new String();
            Math.random();
        }
        System.out.println(Thread.currentThread().getName() + " end time=" + System.currentTimeMillis());
        semaphore.release();
    }
    /**
     Thread.sleep(5000L);

     A begin time=1480390821902
     B begin time=1480390821902
     java.lang.InterruptedException: sleep interrupted
     at java.lang.Thread.sleep(Native Method)
     at multithread.semaphore.numLimit.Service.testMethod(Service.java:18)
     at multithread.semaphore.numLimit.ThreadB.run(ThreadB.java:15)
     main 中断了 B
     A end time=1480390826903
     */
    /**
     for (int i = 0; i < Integer.MAX_VALUE / 50; i++) {
     String newString = new String();
     Math.random();
     }

     A begin time=1480400113965
     B begin time=1480400113965
     main 中断了 B
     A end time=1480400123431
     B end time=1480400123448
     */
}
