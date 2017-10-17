package multithread.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Test {
    public static void main(String[] args) {
        try{
            MyCallable callable1 = new MyCallable("userName1", 5000);
            MyCallable callable2 = new MyCallable("userName2", 4000);
            MyCallable callable3 = new MyCallable("userName3", 3000);
            MyCallable callable4 = new MyCallable("userName4", 2000);
            MyCallable callable5 = new MyCallable("userName5", 1000);

            List<Callable> callableList = new ArrayList<>();
            callableList.add(callable1);
            callableList.add(callable2);
            callableList.add(callable3);
            callableList.add(callable4);
            callableList.add(callable5);

            List<Future> futureList = new ArrayList<>();

            ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
            for(int i=0;i<5;i++){
                futureList.add(executor.submit(callableList.get(i)));
            }
            System.out.println("run first time = "+ System.currentTimeMillis());
            for(int i=0;i<5;i++){
                System.out.println(futureList.get(i).get() + " "+ System.currentTimeMillis());
            }

        }catch (Exception e){

        }
    }
}
