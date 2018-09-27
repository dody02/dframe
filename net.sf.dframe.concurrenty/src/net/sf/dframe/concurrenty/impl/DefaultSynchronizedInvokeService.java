package net.sf.dframe.concurrenty.impl;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import net.sf.dframe.concurrenty.SynchronizedInvoke;
import net.sf.dframe.concurrenty.SynchronizedInvokeService;
import net.sf.dframe.concurrenty.TimeoutException;

/**
 * 同步调用服务的默认实现。
 * @author dell
 *
 */
public class DefaultSynchronizedInvokeService implements SynchronizedInvokeService{

	
	private static DefaultSynchronizedInvokeService instance = new DefaultSynchronizedInvokeService();
	
	private ConcurrentHashMap <String , SynchronizedInvoke> synchInvokeMap = new ConcurrentHashMap<String, SynchronizedInvoke>();
	
	
	public static DefaultSynchronizedInvokeService getInstance (){
		return instance;
	}
	
	public void complete(String key) throws TimeoutException{
		if (synchInvokeMap.get(key) == null || synchInvokeMap.get(key).isTimeout()  ){
			throw new TimeoutException("Task already Time out");
		} else {
			synchInvokeMap.get(key).resume();
		}
		
	}

	public String register(Object... args) {
		String key = this.getUuidKey();
		synchInvokeMap.put(key, new DefaultSynchronizedInvoke());
		return key;
	}

	
	/**
	 * 同步调用开始，引起阻塞
	 */
	public void synchronizedInvoke(String key ,long timeout, TimeUnit unit) {
		synchInvokeMap.get(key).pause(timeout, unit);
	}

	public void synchronizedInvoke(String key ,long timeout) {
		synchInvokeMap.get(key).pause(timeout, TimeUnit.MILLISECONDS);
	}
	
	
	
	/**
	 * get uuid key
	 * @return
	 */
	private String getUuidKey (){
		return UUID.randomUUID().toString();
	}

	public boolean getCompleteStatus(String key ) {
		return synchInvokeMap.get(key).getCompleteStatus();
	}

	public void unRegister(String key) {
		synchInvokeMap.get(key).resume();
		synchInvokeMap.remove(key);
	}

	public String register() {
		String key = this.getUuidKey();
		synchInvokeMap.put(key, new DefaultSynchronizedInvoke());
		return key;
	}

}
