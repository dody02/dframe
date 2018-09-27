package net.sf.dframe.concurrenty.impl;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import net.sf.dframe.concurrenty.SynchronizedInvoke;
/**
 * 默认同步调用信号
 * @author JerryY
 *
 */
public class DefaultSynchronizedInvoke implements SynchronizedInvoke{


	boolean complete = false;
	boolean intime = true;
	
//	boolean pause = true;
	
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	
	public boolean getCompleteStatus() {
		return complete;
	}

	
	/**
	 * 暂停
	 */
	public void pause(long timeout ,TimeUnit unit) {
		lock.lock();
	     try {
//	    	while (pause) 
	    	 intime = condition.await(timeout , unit);	 //true,则没有超时，false超时   	
	    	
	     } catch(InterruptedException ie) {
	        
	     } finally {
	    	 lock.unlock();
	     }
	}

	public void resume() {
		lock.lock();
	     try {
//	    	 pause = false;
	    	 complete = true;
	    	 condition.signalAll();
	     } finally {
	       lock.unlock();
	     }
	}


	public boolean isTimeout() {
		if(intime){
			return false;
		} else {
			return true;
		}
		
	}

}
