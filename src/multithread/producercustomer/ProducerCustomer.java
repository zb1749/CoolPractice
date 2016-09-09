package multithread.producercustomer;

/**
 * Producer-Consumer pattern in Java using the {@link java.util.concurrent
 * .ArrayBlockingQueue} Java class.
 *
 * Producer-Consumer是一些线程创建资源，而一些线程处理资源的模型，这里通过BlockingQueue存储资源并在线程间传递
 */
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class ProducerCustomer {

    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    producer();
                } catch (InterruptedException ignored) {}
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    consumer();
                } catch (InterruptedException ignored) {}
            }
        });
        t1.start();
        t2.start();

        // Pause for 30 seconds and force quitting the app (because we're
        // looping infinitely)
        Thread.sleep(3000);
        System.out.println("main thread over");
        System.exit(0);
    }

    private static void producer() throws InterruptedException {
        Random random = new Random();
        int i = 1;
        while (true) {//loop indefinitely
            Thread.sleep(i++);
            if(i==10){
                System.out.println("producer sleep(2000)");
                Thread.sleep(2000);
            }
            queue.put(random.nextInt(100));//if queue is full (10) waits
        }
    }

    private static void consumer() throws InterruptedException {
        Random random = new Random();
        Thread.sleep(100);
        while (true) {
            Thread.sleep(1);

            Integer value = queue.take();//if queue is empty waits
            System.out.println("Taken value: " + value + "; Queue size is: " + queue.size());
            if(queue.isEmpty()){
                System.out.println("empty wait");
            }

        }
    }
}
