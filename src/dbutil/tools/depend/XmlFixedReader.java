package dbutil.tools.depend;

import java.io.BufferedReader;
import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by Kevin on 2016/10/26.
 *
 * 过滤xml的无效字符。
 *
 * XML中出现以下字符就是无效的，此时Parser会抛出异常，仅仅因为个别字符导致整个文档无法解析，是不是小题大作了点？
 * 为此编写了这个类来过滤输入流中的非法字符。
 * 不过这个类的实现不够好，性能比起原来的Reader实现和nio的StreamReader下降明显，尤其是read(char[] b, int
 * off, int len)方法. 如果不需要由XmlFixedReader带来的容错性，还是不要用这个类的好。
 * <ol>
 * <li>0x00 - 0x08</li>
 * <li>0x0b - 0x0c</li>
 * <li>0x0e - 0x1f</li>
 * </ol>
 */
public class XmlFixedReader extends FilterReader {
    public XmlFixedReader(Reader reader) {
        super(new BufferedReader(reader));
    }

    public int read() throws IOException {
        int ch = super.read();
        while ((ch >= 0x00 && ch <= 0x08) || (ch >= 0x0b && ch <= 0x0c) || (ch >= 0x0e && ch <= 0x1f) || ch == 0xFEFF) {
            ch = super.read();
        }
        return ch;
    }

    // 最大的问题就是这个方法，一次读取一个字符速度受影响。

    public int read(char[] b, int off, int len) throws IOException {
        if (b == null) {
            throw new NullPointerException();
        } else if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return 0;
        }
        int c = read();
        if (c == -1) {
            return -1;
        }
        b[off] = (char) c;
        int i = 1;
        try {
            for (; i < len; i++) {
                c = read();
                if (c == -1) {
                    break;
                }
                b[off + i] = (char) c;
            }
        } catch (IOException ee) {
        }
        return i;
    }
}
