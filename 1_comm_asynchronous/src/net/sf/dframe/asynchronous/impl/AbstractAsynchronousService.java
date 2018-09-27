package net.sf.dframe.asynchronous.impl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import net.sf.dframe.asynchronous.AsynchronousService;
import net.sf.dframe.asynchronous.IBusinessProcess;
import net.sf.dframe.asynchronous.ExceptionListener;
import net.sf.dframe.asynchronous.IResultListener;
import net.sf.dframe.asynchronous.TimingThreadPool;

/**
 * 通用异步服务
 * @author Yu
 * @version 1.0
 */
public abstract class AbstractAsynchronousService implements AsynchronousService{

	private final static int DEFAULT_MAX_POOL_SIZE = 20;
	
	private final static long DEFAULT_KEEPALIVE_TIME = 30000L;
	
	private final static int QUEUE_SIZE = Integer.MAX_VALUE;
	
	private ReadWriteLock rwl = new ReentrantReadWriteLock();
	Lock rl = rwl.readLock();
	Lock wl = rwl.writeLock();
	private BlockingQueue<Runnable> queue = null;		
	
	protected ThreadPoolExecutor tp = null;
	
	private IBusinessProcess businessProcess = null;
	
	private IResultListener result ;
	
	private ExceptionListener exception;
	
	
	
	public AbstractAsynchronousService (){
		this (DEFAULT_MAX_POOL_SIZE,DEFAULT_KEEPALIVE_TIME);
	}
	
	public AbstractAsynchronousService (int maxPoolSize){
		this (maxPoolSize,DEFAULT_KEEPALIVE_TIME);
	}
	
	public AbstractAsynchronousService (long keepAliveTime){
		this (DEFAULT_MAX_POOL_SIZE,keepAliveTime);
	}
	
	
	public AbstractAsynchronousService (int maxPoolSize,long keepAliveTime){
		int minmumPoolSize = Runtime.getRuntime().availableProcessors();
		if ( minmumPoolSize > maxPoolSize  ) {
			minmumPoolSize = 0;
		}
		queue = new LinkedBlockingDeque<Runnable>(QUEUE_SIZE);
		tp =  new TimingThreadPool (minmumPoolSize , maxPoolSize,keepAliveTime,TimeUnit.MILLISECONDS,queue);
		tp.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy ());
		
	}
	
	public AbstractAsynchronousService ( int corePoolSize , int maxPoolSize ,long keepAliveTime , TimeUnit unit) {
		queue = new LinkedBlockingDeque<Runnable>(QUEUE_SIZE);
		tp =  new TimingThreadPool (corePoolSize , maxPoolSize,keepAliveTime,unit,queue);
		tp.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy ());
	}
	
	public AbstractAsynchronousService ( int corePoolSize , int maxPoolSize ,long keepAliveTime , TimeUnit unit , BlockingQueue<Runnable> workQueue) {
		tp =  new TimingThreadPool (corePoolSize , maxPoolSize,keepAliveTime,unit,workQueue);
		tp.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy ());
	}
	
	public AbstractAsynchronousService ( int corePoolSize , int maxPoolSize ,long keepAliveTime , TimeUnit unit , BlockingQueue<Runnable> workQueue , RejectedExecutionHandler RejectedExecutionHandler) {
		tp =  new TimingThreadPool (corePoolSize , maxPoolSize,keepAliveTime,unit,workQueue);
		tp.setRejectedExecutionHandler(RejectedExecutionHandler);
	}
	
//	@Override
	public void setBusinessProcess(IBusinessProcess process) {
		wl.lock();
		try{
			this.businessProcess = process;
		}finally{
			wl.unlock();
		}
		
	}

//	@Override
	public void setExceptionListener(ExceptionListener listener) {
		this.exception = listener;
	}

//	@Override
	public void setResultListener(IResultListener listener) {
		this.result = listener;		
	}
	
	public IBusinessProcess getBusinessProcess(){
		rl.lock();
		try{
			return this.businessProcess;
		}finally{
			rl.unlock();
		}
	}
	
	public ExceptionListener getExceptionListener(){
		return this.exception;
	}
	
	public IResultListener getResultListener(){
		return this.result;
	}


	
	public abstract Object submit(final Object obj) throws Exception;
	
	/**
	 * 关闭
	 */
	public void close( boolean isnow){
		if (tp != null ){
			if ( isnow ){
				tp.shutdownNow();
			} else {
				tp.shutdown();	
			}
		}
	}
	
}
