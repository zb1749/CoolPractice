package multithread.future;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {

    private String userName;
    private long sleepValue;

    public MyCallable(String userName, long sleepValue){
        super();
        this.userName = userName;
        this.sleepValue = sleepValue;
    }

    @Override
    public String call() throws Exception {
        System.out.println(userName + " run "+ System.currentTimeMillis());
        Thread.sleep(sleepValue);
        return "return: " + userName;
    }
}
