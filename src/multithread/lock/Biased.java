package multithread.lock;

import java.util.List;
import java.util.Vector;

/**
 * 开启偏向锁的程序运行时间明显较短
 * 有synchronized的方法就可以进入 偏向锁
 */
public class Biased {
    public static List<Integer> numberList = new Vector<Integer>();

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        int count = 0;
        int startnum = 0;
        while (count < 10000000) {
            numberList.add(startnum);
            startnum += 2;
            count++;
        }
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
        //-XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0 -client -Xmx512m -Xms512m
        //use biased lock cost - 328
        //-XX:-UseBiasedLocking -client -Xmx512m -Xms512m
        //none biased lock cost - 503
    }
}
