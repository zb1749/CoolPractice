package multithread.semaphore.producerAndCustomer;


/**
 * Created by Kevin on 2016/11/29.
 */
public class ThreadCustomer extends Thread {
    private RepastService service;

    public ThreadCustomer(RepastService service){
        super();
        this.service = service;
    }

    public void run(){
        service.get();
    }
}
