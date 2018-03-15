package gen;

import java.io.*;
import java.util.Random;

/**
 * 随机生成访问日志
 */
public class GenLog {

    /**
     * 生成访问log
     */
    public void genAccessLog(String fileName) {

        String[] date = {"[12/Oct/2015:00:00:00 +0800]",
                "[12/Oct/2015:00:00:01 +0800]",
                "[12/Oct/2015:00:00:02 +0800]",
                "[12/Oct/2015:00:00:03 +0800]",
                "[12/Oct/2015:00:00:04 +0800]",
                "[12/Oct/2015:00:00:05 +0800]",
                "[12/Oct/2015:00:00:06 +0800]",
                "[12/Oct/2015:00:00:07 +0800]",
                "[12/Oct/2015:00:00:08 +0800]",
                "[12/Oct/2015:00:00:09 +0800]",
                "[12/Oct/2015:00:00:10 +0800]",};
        String[] remoteAdd = new String[10];
        for (int i = 0; i < 10; i++) {
            remoteAdd[i] = randomIp();
        }

        String[] returnTime = {"21", "436", "67", "599", "12", "740", "260", "124", "23", "312", "44"};

        String[] method = {"GET", "POST"};

        String[] url = {"www.xxxx.com/index.html",
                "www.xxxx.com/list.html",
                "www.xxxx.com/detail.html",
                "www.xxxx.com/userinfo.html",
                "www.xxxx.com/publish.html",
                "www.xxxx.com/user.html",
                "www.xxxx.com/friend.html",
                "www.xxxx.com/home.html",
                "www.xxxx.com/plugin.html",
                "www.xxxx.com/edit.html",
                "www.xxxx.com/news.html"};

        String[] refer = {"www.google.com", "www.baidu.com", "www.bing.com", "www.yahoo.com"};

        String[] status = {"200", "301", "302", "404", "500"};

        String[] sendBytes = {"33556", "2223", "56542", "5445", "520", "464654", "455211", "11121", "1231", "544556", "455550"};

        File accLog = new File(fileName);
        try {
            FileOutputStream out = new FileOutputStream(accLog);
            BufferedOutputStream buf = new BufferedOutputStream(out);

            for (int i = 0; i < 10000; i++) {
                buf.write((getRandomVal(date) + " " + getRandomVal(remoteAdd) + " " + getRandomVal(returnTime) + " " + getRandomVal(method)
                        + " " + getRandomVal(url) + " " + getRandomVal(refer) + " " + getRandomVal(status) + " " + getRandomVal(sendBytes) + "\n").getBytes());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 随机获得arr中的值
     *
     * @param arr
     * @return
     */
    public String getRandomVal(String[] arr) {
        int index = (int) (Math.random() * arr.length);
        String random = arr[index];
        return random;
    }

    /**
     * 随机生成国内IP地址
     */
    public static String randomIp() {

        // ip范围
        int[][] range = {{607649792, 608174079},// 36.56.0.0-36.63.255.255
                {1038614528, 1039007743},// 61.232.0.0-61.237.255.255
                {1783627776, 1784676351},// 106.80.0.0-106.95.255.255
                {2035023872, 2035154943},// 121.76.0.0-121.77.255.255
                {2078801920, 2079064063},// 123.232.0.0-123.235.255.255
                {-1950089216, -1948778497},// 139.196.0.0-139.215.255.255
                {-1425539072, -1425014785},// 171.8.0.0-171.15.255.255
                {-1236271104, -1235419137},// 182.80.0.0-182.92.255.255
                {-770113536, -768606209},// 210.25.0.0-210.47.255.255
                {-569376768, -564133889}, // 222.16.0.0-222.95.255.255
        };

        Random rdint = new Random();
        int index = rdint.nextInt(10);
        String ip = num2ip(range[index][0] + new Random().nextInt(range[index][1] - range[index][0]));
        return ip;
    }

    /**
     * 将十进制转换成ip地址
     */
    public static String num2ip(int ip) {
        int[] b = new int[4];
        String x = "";

        b[0] = (int) ((ip >> 24) & 0xff);
        b[1] = (int) ((ip >> 16) & 0xff);
        b[2] = (int) ((ip >> 8) & 0xff);
        b[3] = (int) (ip & 0xff);
        x = Integer.toString(b[0]) + "." + Integer.toString(b[1]) + "." + Integer.toString(b[2]) + "." + Integer.toString(b[3]);

        return x;
    }

    public static void main(String[] args) {
        GenLog gen = new GenLog();
        String fileName = "./accLog.txt";
        gen.genAccessLog(fileName);
    }

}
