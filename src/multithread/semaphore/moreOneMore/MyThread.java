package multithread.semaphore.moreOneMore;


/**
 * Created by Kevin on 2016/11/29.
 */
public class MyThread extends Thread {
    private Service service;

    public MyThread(Service service){
        super();
        this.service = service;
    }

    public void run(){
        service.sayHello();
    }
}
