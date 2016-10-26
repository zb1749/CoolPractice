package dbutil.common;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ObjectUtils;


/**
 * 描述由两个值构成的值对
 *
 * @param <K>
 * @param <V>
 * @author Administrator
 */
public class Entry<K, V> implements Serializable, Map.Entry<K, V>, Groupable<V> {
    private static final long serialVersionUID = 2805658306682403737L;
    private K key;
    private V value;

    public String toString() {
        return ObjectUtils.toString(key) + ":" + ObjectUtils.toString(value);
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public V setValue(V value) {
        V old = this.value;
        this.value = value;
        return old;
    }

    public final void setKey(K key) {
        this.key = key;
    }

    public Entry() {
    }

    ;

    public Entry(K k, V v) {
        this.key = k;
        this.value = v;
    }

    public static <K, V> List<Entry<K, V>> fromMap(Map<K, V> map) {
        List<Entry<K, V>> list = new ArrayList<Entry<K, V>>();
        for (K k : map.keySet()) {
            list.add(new Entry<K, V>(k, map.get(k)));
        }
        return list;
    }

    public V getGroupValue(String name) {
        return value;
    }

    @Override
    public int hashCode() {
        int i = 0;
        if (key != null) i += key.hashCode();
        if (value != null) i += value.hashCode();
        return i;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Entry) {
            if (!ObjectUtils.equals(((Entry) obj).getKey(), key)) return false;
            if (!ObjectUtils.equals(((Entry) obj).getValue(), value)) return false;
            return true;
        } else {
            return false;
        }
    }
}
