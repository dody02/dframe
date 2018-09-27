package net.sf.dframe.concurrenty.test;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class PausableThreadPoolExecutor  {
	   private boolean isPaused;
	   private ReentrantLock pauseLock = new ReentrantLock();
	   private Condition unpaused = pauseLock.newCondition();

	   
	 
	   protected void pause() {
	    
	     pauseLock.lock();
	     isPaused = true;
	     try {
//	       while (isPaused) {
	    	   System.out.println("开始等.......");
	    	   boolean overtime = unpaused.await(10000L,TimeUnit.MILLISECONDS);
//	    	   unpaused.await();
	    	   System.out.println("等.......不超时："+overtime);
//	       }
	     } catch(InterruptedException ie) {
//	       t.interrupt();
	     } finally {
	       pauseLock.unlock();
	     }
	   }
	 
//	   public void pause() {
//	     pauseLock.lock();
//	     try {
//	       isPaused = true;
//	     } finally {
//	       pauseLock.unlock();
//	     }
//	   }
	 
	   public void resume() {
	     pauseLock.lock();
	     try {
	       isPaused = false;
	       unpaused.signalAll();
	     } finally {
	       pauseLock.unlock();
	     }
	   }
	 }
