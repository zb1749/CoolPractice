package multithread.semaphore.producerAndCustomer;


/**
 * Created by Kevin on 2016/11/29.
 */
public class ThreadProducer extends Thread {
    private RepastService service;

    public ThreadProducer(RepastService service){
        super();
        this.service = service;
    }

    public void run(){
        service.set();
    }
}
