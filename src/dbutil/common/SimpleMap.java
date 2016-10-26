package dbutil.common;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import org.apache.commons.lang.ObjectUtils;
import dbutil.tools.ArrayUtils;

/**
 * 用List实现的最简单的Map，目标是占用内存最小，不考虑性能，事实上元素不多的情况下性能不是什么问题
 * @param <K>
 * @param <V>
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class SimpleMap<K, V> extends AbstractMap<K, V> implements Map<K, V>, Serializable {
	private static final long serialVersionUID = -4930667933312037159L;
	
	@XmlElement(nillable = false, name = "simpleEntry")
	private List<dbutil.common.Entry<K, V>> entries;

	public List<dbutil.common.Entry<K, V>> getEntries() {
		return entries;
	}

	public void setEntries(List<dbutil.common.Entry<K, V>> entries) {
		this.entries = entries;
	}


	public SimpleMap(dbutil.common.Entry<K, V>[] entries) {
		this.entries = ArrayUtils.asList(entries);
	}
	
	public SimpleMap() {
		this(16);
	}
	
	public SimpleMap(int size) {
		this.entries = new ArrayList<dbutil.common.Entry<K, V>>(size);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SimpleMap(Map<K,V> map) {
		if(map instanceof SimpleMap){
			entries=((SimpleMap) map).entries;
		}else{
			entries = new ArrayList<dbutil.common.Entry<K, V>>(map.size());
			for(Entry<K,V> e: map.entrySet()){
				entries.add(new dbutil.common.Entry<K, V>(e.getKey(), e.getValue()));
			}	
		}
	}

	@Override
	public V put(K key, V value) {
		int index = -1;
		for (int i = 0; i < entries.size(); i++) {
			if (ObjectUtils.equals(entries.get(i).getKey(), key)) {
				index = i;
				break;
			}
		}
		if (index > -1) {
			entries.set(index, new dbutil.common.Entry<K, V>(key, value));
		} else {
			entries.add(new dbutil.common.Entry<K, V>(key, value));
		}
		return value;
	}
	
	private class EntriesIterator implements Iterator<Entry<K, V>>{
		private int n=0;
		public boolean hasNext() {
			return n<entries.size();
		}

		public Entry<K, V> next() {
			Entry<K, V> result=entries.get(n);
			n++;
			return result;
		}

		public void remove() {
			n--;
			entries.remove(n);
		}
	}

	public Set<Entry<K, V>> entrySet() {
		Set<Entry<K, V>> set = new AbstractSet<Entry<K, V>>() {
			@Override
			public Iterator<Entry<K, V>> iterator() {
				return new EntriesIterator();
			}
			@Override
			public int size() {
				return entries.size();
			}
		};
		return set;
	}
}
