package multithread.semaphore.methodArg;


/**
 * Created by Kevin on 2016/11/29.
 */
public class ThreadA extends Thread {
    private Service service;

    public ThreadA(Service service){
        super();
        this.service = service;
    }

    public void run(){
        service.testMethod();
    }
}
