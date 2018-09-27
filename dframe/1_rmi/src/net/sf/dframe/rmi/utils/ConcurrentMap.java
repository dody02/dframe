package net.sf.dframe.rmi.utils;

import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * 线程安全Mapping类
 * @author JerryY
 *
 * @param <K>
 * @param <V>
 */
public class ConcurrentMap <K,V>  extends  HashMap<K, V>{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HashMap<K,V> map = new HashMap<K,V> ();
	
	private ReadWriteLock rwl = new ReentrantReadWriteLock();
	private ReadLock rl = (ReadLock) rwl.readLock();
	private WriteLock wl = (WriteLock) rwl.writeLock();
	
	public V put (K key , V value) {
		wl.lock();
		try{
			map.put(key, value);	
		}finally{
			wl.unlock();
		}
		return value;
		
	}
	
	public V get (Object key) {
		rl.lock();
		try{
			return map.get(key);	
		}finally{
			rl.unlock();
		}
	}
	
	public void clear () {
		wl.lock();
		try{
			map.clear();
		}finally{
			wl.unlock();
		}
	}
	
	public V removed ( Object key ) {
		wl.lock();
		try{
			return map.remove(key);	
		}finally{
			wl.unlock();
		}		
	}
	
}
