package multithread.semaphore.dynamicPermits;


import java.util.concurrent.Semaphore;

/**
 * 通过手动调用acquire和release动态改变同时允许运行的线程数
 * Created by Kevin on 2016/11/29.
 */
public class Run {
    public static void main(String[] args) {
        try {
            Semaphore semaphore = new Semaphore(5);
            semaphore.acquire();
            semaphore.acquire();
            semaphore.acquire();
            System.out.println("available permits = "+semaphore.availablePermits());
            semaphore.release();
            System.out.println("available permits = "+semaphore.availablePermits());
            semaphore.release(2);
            System.out.println("available permits = "+semaphore.availablePermits());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     available permits = 2
     available permits = 3
     available permits = 5
     */
}
