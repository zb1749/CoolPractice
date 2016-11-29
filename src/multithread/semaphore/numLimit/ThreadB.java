package multithread.semaphore.numLimit;

/**
 * Created by Kevin on 2016/11/29.
 */
public class ThreadB extends Thread {
    private Service service;

    public ThreadB(Service service){
        super();
        this.service = service;
    }

    public void run(){
        service.testMethod();
    }
}
