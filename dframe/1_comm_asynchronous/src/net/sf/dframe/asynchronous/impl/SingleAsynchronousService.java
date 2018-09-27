package net.sf.dframe.asynchronous.impl;

import java.util.concurrent.TimeUnit;

/**
 * 单线程服务
 * @author dy
 *
 */
public class SingleAsynchronousService extends AbstractAsynchronousService{

	private final static int corePoolSize = 1;
	private final static int maxPoolSize = 1;
	private final static long keepAliveTime = 30000L;
	public SingleAsynchronousService() {
		super (corePoolSize , maxPoolSize , keepAliveTime , TimeUnit.MILLISECONDS);
	}
	
	public SingleAsynchronousService(long keepAliveTime) {
		super (corePoolSize , maxPoolSize , keepAliveTime , TimeUnit.MILLISECONDS);
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
