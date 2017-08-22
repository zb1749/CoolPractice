package multithread.cas;

public class Test implements Runnable {
    static int sum;
    private SpinLock lock;

    public Test(SpinLock lock) {
        this.lock = lock;
    }
    public static void main(String[] args) throws InterruptedException {
        SpinLock lock = new SpinLock();
        for (int i = 0; i < 100; i++) {
            Test test = new Test(lock);
            Thread t = new Thread(test);
            t.start();
        }

        Thread.currentThread().sleep(1);//这个例子。。
        System.out.println(sum);
    }

    @Override
    public void run() {
        this.lock.lock();
        sum++;
        this.lock.unLock();
    }
}
