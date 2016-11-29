package multithread.semaphore.moreOneMore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Kevin on 2016/11/29.
 */
public class Service {
    private Semaphore semaphore = new Semaphore(3);//中的数量限制同时允许运行的并发线程数
    private ReentrantLock lock = new ReentrantLock();


    public void sayHello() {
        try {

            if (semaphore.tryAcquire(3, TimeUnit.SECONDS)) {
                System.out.println("Thread name=" + Thread.currentThread().getName() + " 进入！ time= " + System.currentTimeMillis());
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " begin hello！ time= " + System.currentTimeMillis());
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + " 打印！" + (i + 1) + " time= " + System.currentTimeMillis());

                }
                System.out.println(Thread.currentThread().getName() + " end hello！ time= " + System.currentTimeMillis());
                lock.unlock();
                semaphore.release();
                System.out.println(Thread.currentThread().getName() + " end time= " + System.currentTimeMillis());
            } else {
                System.out.println("Thread name=" + Thread.currentThread().getName() + " 未成功进入！");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
