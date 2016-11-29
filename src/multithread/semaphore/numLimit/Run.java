package multithread.semaphore.numLimit;

/**
 * Created by Kevin on 2016/11/29.
 */
public class Run {
    public static void main(String[] args) {
        Service service = new Service();
        ThreadA a = new ThreadA(service);
        a.setName("A");
        ThreadB b = new ThreadB(service);
        b.setName("B");
        ThreadC c = new ThreadC(service);
        c.setName("C");

        a.start();
        b.start();
        c.start();
    }
    /**
     A begin time=1480382010084
     C begin time=1480382010084
     A end time=1480382015085
     C end time=1480382015085
     B begin time=1480382015085
     B end time=1480382020085
     */
    /**
     * new Semaphore(2) 中的数量限制同时允许运行的并发线程数
     */
}
