package io.nio.buffer;

import java.nio.CharBuffer;

/**
 * 填充和释放缓冲区
 */
public class BufferFillDrain {
    public static void main(String[] argv) throws Exception {
        CharBuffer buffer = CharBuffer.allocate(100);
        while (fillBuffer(buffer)) {
            buffer.flip();
            drainBuffer(buffer);
            buffer.clear();
        }
    }

    /**
     * 释放
     * @param buffer
     */
    private static void drainBuffer(CharBuffer buffer) {
        while (buffer.hasRemaining()) {
            System.out.print(buffer.get());
        }
        System.out.println("");
    }

    /**
     * 填充
     * @param buffer
     * @return
     */
    private static boolean fillBuffer(CharBuffer buffer) {
        if (index >= strArr.length) {
            return (false);
        }
        String string = strArr[index++];
        for (int i = 0; i < string.length(); i++) {
            buffer.put(string.charAt(i));
        }
        //缓冲区并不是多线程安全的。如果您想以多线程同时存取特定的缓冲区，您需要在存取缓冲区之前进行同步（例如对缓冲区对象进行跟踪）。
        return (true);
    }

    private static int index = 0;
    private static String[] strArr = {"A random string value", "The product of an infinite number of monkeys",
            "Hey hey we're the Monkees", "Opening act for the Monkees: Jimi Hendrix", "'Scuse me while I kiss this fly",
            "Help Me! Help Me!",};
}
