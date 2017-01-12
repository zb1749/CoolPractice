package io.file.incomeFile;

/**
 * Created by Kevin on 2016/12/30.
 */
public class RandomAccessIndex {
    public static void main(String[] args) {
        String line = "D|20161220094524|01|null|81||1561|1|20150910|0x0A\n";
        int length = line.length();
        int lastIndex = line.lastIndexOf("0x0A");
        System.out.print(line);
        System.out.println("line length: " + length + " ,lastIndex: " + lastIndex);

    }
}
