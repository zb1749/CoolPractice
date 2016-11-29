package multithread.semaphore.moreOneMore;


/**
 * Created by Kevin on 2016/11/29.
 */
public class Run {
    public static void main(String[] args) {
        Service service = new Service();
        MyThread[] arr = new MyThread[9];
        for (MyThread a : arr) {
            a = new MyThread(service);
            a.start();
        }
    }
    /**
     Thread name=Thread-1 进入！ time= 1480409520189
     Thread-1 begin hello！ time= 1480409520189
     Thread name=Thread-4 进入！ time= 1480409520189
     Thread name=Thread-2 进入！ time= 1480409520189
     Thread-1 打印！1 time= 1480409520189
     Thread-1 打印！2 time= 1480409520189
     Thread-1 打印！3 time= 1480409520189
     Thread-1 打印！4 time= 1480409520189
     Thread-1 打印！5 time= 1480409520189
     Thread-1 end hello！ time= 1480409520189
     Thread-1 end time= 1480409520189
     Thread name=Thread-3 进入！ time= 1480409520189
     Thread-4 begin hello！ time= 1480409520189
     Thread-4 打印！1 time= 1480409520190
     Thread-4 打印！2 time= 1480409520190
     Thread-4 打印！3 time= 1480409520190
     Thread-4 打印！4 time= 1480409520190
     Thread-4 打印！5 time= 1480409520190
     Thread-4 end hello！ time= 1480409520190
     Thread-4 end time= 1480409520190
     Thread name=Thread-7 进入！ time= 1480409520190
     Thread-2 begin hello！ time= 1480409520190
     Thread-2 打印！1 time= 1480409520190
     Thread-2 打印！2 time= 1480409520190
     Thread-2 打印！3 time= 1480409520190
     Thread-2 打印！4 time= 1480409520190
     Thread-2 打印！5 time= 1480409520190
     Thread-2 end hello！ time= 1480409520190
     Thread-2 end time= 1480409520190
     Thread name=Thread-8 进入！ time= 1480409520190
     Thread-3 begin hello！ time= 1480409520190
     Thread-3 打印！1 time= 1480409520190
     Thread-3 打印！2 time= 1480409520190
     Thread-3 打印！3 time= 1480409520190
     Thread-3 打印！4 time= 1480409520190
     Thread-3 打印！5 time= 1480409520190
     Thread-3 end hello！ time= 1480409520190
     Thread-3 end time= 1480409520190
     Thread name=Thread-6 进入！ time= 1480409520190
     Thread-7 begin hello！ time= 1480409520190
     Thread-7 打印！1 time= 1480409520190
     Thread-7 打印！2 time= 1480409520190
     Thread-7 打印！3 time= 1480409520190
     Thread-7 打印！4 time= 1480409520190
     Thread-7 打印！5 time= 1480409520190
     Thread-7 end hello！ time= 1480409520190
     Thread-7 end time= 1480409520190
     Thread name=Thread-5 进入！ time= 1480409520190
     Thread-8 begin hello！ time= 1480409520190
     Thread-8 打印！1 time= 1480409520190
     Thread-8 打印！2 time= 1480409520190
     Thread-8 打印！3 time= 1480409520190
     Thread-8 打印！4 time= 1480409520190
     Thread-8 打印！5 time= 1480409520190
     Thread-8 end hello！ time= 1480409520190
     Thread-8 end time= 1480409520190
     Thread name=Thread-0 进入！ time= 1480409520190
     Thread-6 begin hello！ time= 1480409520190
     Thread-6 打印！1 time= 1480409520190
     Thread-6 打印！2 time= 1480409520190
     Thread-6 打印！3 time= 1480409520190
     Thread-6 打印！4 time= 1480409520190
     Thread-6 打印！5 time= 1480409520191
     Thread-6 end hello！ time= 1480409520191
     Thread-6 end time= 1480409520191
     Thread-5 begin hello！ time= 1480409520191
     Thread-5 打印！1 time= 1480409520191
     Thread-5 打印！2 time= 1480409520191
     Thread-5 打印！3 time= 1480409520191
     Thread-5 打印！4 time= 1480409520191
     Thread-5 打印！5 time= 1480409520191
     Thread-5 end hello！ time= 1480409520191
     Thread-5 end time= 1480409520191
     Thread-0 begin hello！ time= 1480409520192
     Thread-0 打印！1 time= 1480409520192
     Thread-0 打印！2 time= 1480409520192
     Thread-0 打印！3 time= 1480409520192
     Thread-0 打印！4 time= 1480409520192
     Thread-0 打印！5 time= 1480409520192
     Thread-0 end hello！ time= 1480409520192
     Thread-0 end time= 1480409520192
     */
}
