package multithread.volatileTest;

/**
 * Created by Kevin on 2017/1/25.
 */
public class VolatileTest {
    volatile boolean isExit;
    public void tryExit(){
        if(isExit == !isExit){ //?what
            System.exit(0);
        }
    }
    public void swapValue(){
        isExit = !isExit;
    }
    public void test() throws InterruptedException{
        final VolatileTest volObj = new VolatileTest();
        Thread mainThread = new Thread(){
          public void run(){
              System.out.println("mainThread start");
              while (true){
                  volObj.tryExit();
              }
          }
        };
        mainThread.start();
        Thread swapThread = new Thread(){
          public void run(){
              System.out.println("swapThread start");
              while (true){
                  volObj.swapValue();
              }
          }
        };
        swapThread.start();
        Thread.sleep(10000L);
    }
}

