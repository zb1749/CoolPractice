package multithread.semaphore.methodArg;


/**
 * Created by Kevin on 2016/11/29.
 */
public class Run {
    public static void main(String[] args) {
        Service service = new Service();
        ThreadA[] arr = new ThreadA[10];//好吧，我以为只有String可以这样初始化数组呢，我low了
        for (ThreadA a : arr) {
            a = new ThreadA(service);
            a.start();
        }

    }
    /**
     Thread-0 begin time=1480385353481
     Thread-1 begin time=1480385353481
     Thread-2 begin time=1480385353481
     Thread-3 begin time=1480385353481
     Thread-4 begin time=1480385353481
     Thread-3 sleep 9 seconds
     Thread-4 sleep 3 seconds
     Thread-2 sleep 0 seconds
     Thread-1 sleep 7 seconds
     Thread-0 sleep 8 seconds
     Thread-2 end time=1480385353631
     Thread-5 begin time=1480385353631
     Thread-5 sleep 6 seconds
     Thread-4 end time=1480385356925
     Thread-6 begin time=1480385356925
     Thread-6 sleep 6 seconds
     Thread-5 end time=1480385360257
     Thread-7 begin time=1480385360257
     Thread-7 sleep 8 seconds
     Thread-1 end time=1480385361047
     Thread-8 begin time=1480385361047
     Thread-8 sleep 7 seconds
     Thread-0 end time=1480385362360
     Thread-9 begin time=1480385362360
     Thread-9 sleep 3 seconds
     Thread-3 end time=1480385362682
     Thread-6 end time=1480385363195
     Thread-9 end time=1480385366255
     Thread-8 end time=1480385368151
     Thread-7 end time=1480385368356
     */

}
