package multithread.semaphore.numLimit;

import java.util.concurrent.Semaphore;

/**
 * Created by Kevin on 2016/11/29.
 */
public class Service {
    private Semaphore semaphore = new Semaphore(2);//中的数量限制同时允许运行的并发线程数

    /**
     * 控制同时运行的acquire和release之间的代码的线程数量
     */
    public void testMethod(){
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName()+" begin time="+System.currentTimeMillis());
            Thread.sleep(5000L);
            System.out.println(Thread.currentThread().getName()+" end time="+System.currentTimeMillis());
            semaphore.release();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
