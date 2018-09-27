package net.sf.dframe.asynchronous.impl;

import java.util.concurrent.TimeUnit;


/**
 * 通用异步服务
 * @author Yu
 * @version 1.0
 */
public class SimpleAsynchronousService extends AbstractAsynchronousService{

	public SimpleAsynchronousService (){
		super ();
	}
	
	public SimpleAsynchronousService (int maxPoolSize){
		super (maxPoolSize);
	}
	
	public SimpleAsynchronousService (long keepAliveTime){
		super (keepAliveTime);
	}
	
	public SimpleAsynchronousService (int maxPoolSize,long keepAliveTime){
		super (maxPoolSize,keepAliveTime);
	}
	
	public SimpleAsynchronousService (int corePoolSize , int maxPoolSize , long keepAliveTime){
		super (corePoolSize,maxPoolSize,keepAliveTime,TimeUnit.MILLISECONDS);
	}
	
	@Override
	public Object submit(final Object obj) throws Exception {
		if ( getBusinessProcess() == null ){
			throw new Exception ("cound't find the business process object ! make sure set the business process object before you submit.");
		}else {
			tp.execute(new Runnable(){
				public void run() {
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
					
				}});
		}
		return null;
	}

	
	
	
}
