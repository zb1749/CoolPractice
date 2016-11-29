package multithread.semaphore.numLimit;

/**
 * Created by Kevin on 2016/11/29.
 */
public class ThreadC extends Thread {
    private Service service;

    public ThreadC(Service service){
        super();
        this.service = service;
    }

    public void run(){
        service.testMethod();
    }
}
