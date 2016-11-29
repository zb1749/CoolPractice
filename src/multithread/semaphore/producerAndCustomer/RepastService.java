package multithread.semaphore.producerAndCustomer;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Kevin on 2016/11/29.
 */
public class RepastService {
    volatile private Semaphore setSemaphore = new Semaphore(10);//厨师
    volatile private Semaphore getSemaphore = new Semaphore(10);//食客
    volatile private ReentrantLock lock = new ReentrantLock();
    volatile private Condition setCondition = lock.newCondition();
    volatile private Condition getCondition = lock.newCondition();
    volatile private Object[] producePosition = new Object[4];//最多只有4个盒子存放菜品

    private boolean isEmpty() {
        boolean isEmpty = true;
        for (int i = 0; i < producePosition.length; i++) {
            if (producePosition[i] != null) {
                isEmpty = false;
                break;
            }
        }
        return isEmpty;
    }

    private boolean isFull() {
        boolean isFull = true;
        for (int i = 0; i < producePosition.length; i++) {
            if (producePosition[i] != null) {
                isFull = false;
                break;
            }
        }
        return isFull;
    }

    public void set(){
        try {
            setSemaphore.acquire();
            lock.lock();
            while (isFull()){
                setCondition.await();
            }

            for (int i = 0; i < producePosition.length; i++){
                if (producePosition[i] != null) {
                    producePosition[i] = " 数据 ";
                    System.out.println(Thread.currentThread().getName()+" 生产了 "+producePosition[i]);
                    break;
                }
            }
            getCondition.signalAll();
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            setSemaphore.release();
        }
    }

    public void get(){
        try {
            getSemaphore.acquire();
            lock.lock();
            while (isEmpty()){
                getCondition.await();
            }

            for (int i = 0; i < producePosition.length; i++){
                if (producePosition[i] != null) {
                    producePosition[i] = " 数据 ";
                    System.out.println(Thread.currentThread().getName()+" 消费了 "+producePosition[i]);
                    break;
                }
            }
            setCondition.signalAll();
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            getSemaphore.release();
        }
    }
}
