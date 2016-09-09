package multiThread.deadlock;

/**
 *死锁的必要条件是锁嵌套,然后两个线程互相占用被等待的锁
 */
public class SimpleDeadLock {

    public static final Object lock1 = new Object();
    public static final Object lock2 = new Object();
    private int index;

    public static void main(String[] a) {
        Thread t1 = new Thread1();
        Thread t2 = new Thread2();
        t1.start();
        t2.start();
    }

    private static class Thread1 extends Thread {

        public void run() {
            synchronized (lock1) {
                System.out.println("Thread 1: Holding lock 1...");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignored) {}
                System.out.println("Thread 1: Waiting for lock 2...");
                synchronized (lock2) {
                    System.out.println("Thread 1: Holding lock 1 & 2...");
                }
            }
        }
    }

    private static class Thread2 extends Thread {

        public void run() {
            synchronized (lock2) {
                System.out.println("Thread 2: Holding lock 2...");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignored) {}
                System.out.println("Thread 2: Waiting for lock 1...");
                synchronized (lock1) {
                    System.out.println("Thread 2: Holding lock 2 & 1...");
                }
            }
        }
    }
}
