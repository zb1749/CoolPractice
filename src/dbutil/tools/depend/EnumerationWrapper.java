package dbutil.tools.depend;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * 将讨厌的Enumeration 对象封装为Iterable的对象
 *
 * 一些早期接口使用Enumeration对象，并且不支持泛型。
 */
public class EnumerationWrapper<T> implements Iterable<T> {
    Enumeration<T> e;

    public EnumerationWrapper(Enumeration<T> values) {
        this.e = values;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            public boolean hasNext() {
                return e.hasMoreElements();
            }

            public T next() {
                return e.nextElement();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

}
