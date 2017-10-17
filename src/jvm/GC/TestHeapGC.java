package jvm.GC;

/**
 * -XX:+PrintGCDetails  -Xms40M -Xmx40M -Xmn20M -XX:MaxTenuringThreshold=15 -XX:SurvivorRatio=8
 */
public class TestHeapGC {
    public static void main(String[] args) {
        byte[] b1 = new byte[1024 * 1024 / 2];
        byte[] b2 = new byte[1024 * 1024 * 8];
        b2 = null;
        b2 = new byte[1024 * 1024 * 8];
        System.gc();
    }
}
