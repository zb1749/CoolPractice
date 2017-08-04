package multithread.waitAndNotify;

import java.util.ArrayList;
import java.util.List;

/**
 * one example for wait and notify synchronized
 * Created by Kevin on 2017/1/25.
 */
public class BlockQueue {
    private List list = new ArrayList();

    public synchronized Object pop() throws InterruptedException{
        while (list.size()==0){
            this.wait();
        }
        if(list.size()>0){
            return list.remove(0);
        }else {
            return null;
        }
    }

    public synchronized void put(Object o){
        list.add(o);
        this.notify();
    }
}
