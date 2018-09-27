package net.sf.dframe.asynchronous.impl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;


/**
 * �?��异步消息服务
 * 在线程池之上再加�?��缓存
 * @author Yu
 * @version 1.0
 *
 */
public class SimpleCacheAsynchronousService extends AbstractAsynchronousService{

	private BlockingQueue<Object> cache = null;
	
	private final int DEFAULT_CACHE_SIZE = 2000;
	
	private final int DEFAULT_TIMEOUT = 2;
	
	private int cacheSize ;
	
	private ExecutorService exec = Executors.newSingleThreadExecutor();
	
	public SimpleCacheAsynchronousService (){
		super ();
		cacheSize = DEFAULT_CACHE_SIZE;		
	}

	public SimpleCacheAsynchronousService (int cacheSize){
		super ();
		this.cacheSize = cacheSize;
	}
	
	
	public SimpleCacheAsynchronousService (int maxPoolSize , int cacheSize){
		super (maxPoolSize);
		this.cacheSize = cacheSize;
	}
	
	public SimpleCacheAsynchronousService (long keepAliveTime,int cacheSize){
		super (keepAliveTime);
		this.cacheSize = cacheSize;
	}
	
	public SimpleCacheAsynchronousService (int maxPoolSize,long keepAliveTime , int cacheSize){
		super (maxPoolSize,keepAliveTime);
		this.cacheSize = cacheSize;		
	}
	
	public void start (){
		init();
	}
	
	@Override
	public Object submit(Object obj) throws Exception {
		if ( cache.offer(obj)){
			return Boolean.TRUE;	
		}else{
			return Boolean.FALSE;
		}
		
	}

	private void init (){
		cache = new LinkedBlockingQueue<Object> (this.cacheSize);
		exec.submit(new Runnable (){
//			@Override
			public void run() {
				while ( true ){
					try {
						final Object obj = cache.poll(DEFAULT_TIMEOUT,TimeUnit.SECONDS);
						if ( obj != null ){
							tp.execute(new Runnable (){
								public void run() {
									wl.lock();
									try{
										if ( getBusinessProcess() != null ){
											if ( getResultListener() != null ) {
												try {
													getResultListener() .onResult(getBusinessProcess().doProcess(obj));
												} catch (Exception ex) {
													ex.printStackTrace();
													getExceptionListener().onException(ex);
												}	
											} else {
												getBusinessProcess().doProcess(obj);
											}
											
										}
									}finally{
										wl.unlock();
									}
								}								
							});
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		});
	}
	
	public void close (boolean isNow){
		if ( this.exec != null ){
			if ( isNow ){
				exec.shutdownNow();	
			}else {
				exec.shutdown();
			}
			
		}
	}
	
}
