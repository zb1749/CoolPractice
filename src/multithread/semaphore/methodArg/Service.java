package multithread.semaphore.methodArg;

import java.util.concurrent.Semaphore;

/**
 * 总共有10个令牌，每次运行占用2个，所以最多同时5个线程运行=。=
 * 这种控制方式也是醉了=。=方法占用几个令牌有什么用？
 * Created by Kevin on 2016/11/29.
 */
public class Service {
    private Semaphore semaphore = new Semaphore(10);//中的数量限制同时允许运行的并发线程数

    /**
     * 控制同时运行的acquire和release之间的代码的线程数量
     */
    public void testMethod() {
        try {
            //获得许可
            semaphore.acquire(2);//acquire多少，release多少
            System.out.println(Thread.currentThread().getName() + " begin time=" + System.currentTimeMillis());
            int sleepValue = (int) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() + " sleep " + sleepValue / 1000 + " seconds");
            Thread.sleep(sleepValue);
            System.out.println(Thread.currentThread().getName() + " end time=" + System.currentTimeMillis());
            //释放许可
            semaphore.release(2);//acquire多少，release多少
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
