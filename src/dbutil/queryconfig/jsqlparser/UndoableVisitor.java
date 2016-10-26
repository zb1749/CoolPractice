package dbutil.queryconfig.jsqlparser;

import dbutil.common.SimpleMap;
import dbutil.common.Entry;

public abstract class UndoableVisitor<K,V> extends VisitorAdapter{
	private SimpleMap<K,V> rollback=new SimpleMap<K,V>();
	
	protected void savePoint(K k,V v){
		rollback.put(k, v);
	}
	protected abstract void undo(K key, V value) ;
	
	public void undo(){
		for(Entry<K,V> e: rollback.getEntries()){
			undo(e.getKey(),e.getValue());
		}
	}
}
