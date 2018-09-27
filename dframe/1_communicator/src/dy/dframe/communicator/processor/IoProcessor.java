package dy.dframe.communicator.processor;

import java.util.concurrent.TimeUnit;

import net.sf.dframe.asynchronous.IBusinessProcess;
import net.sf.dframe.asynchronous.impl.AbstractAsynchronousService;



/**
 * IO处理线程程服务
 * @author dy
 *
 */
public class IoProcessor extends AbstractAsynchronousService {

	public IoProcessor ( int corePoolSize , int maximumPoolSize, long keepAliveTime , TimeUnit timeunit,IBusinessProcess process ) {				
		super(corePoolSize,maximumPoolSize,keepAliveTime ,timeunit);
		setBusinessProcess(process);
	}
	
	public IoProcessor ( IBusinessProcess process ){
		super();
		setBusinessProcess(process);
	}
	
	
	
	@Override
	public Object submit(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
