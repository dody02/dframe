package net.sf.dframe.asynchronous;
/**
 * 异步服务
 * @author dy
 * @version 1.0
 */
public interface AsynchronousService {
/*#IBusinessProcess lnkIBusinessProcess*/
/*#IExceptionListener lnkIExceptionListener*/
/*#IResultListener lnkIResultListener*/

	/**
	 * 提交处理的消�?
	 * @param obj
	 * @return
	 * @throws Exception 
	 */
	public Object submit (Object obj) throws Exception;
	
	/**
	 * 设置结果监听�?
	 * @param listener
	 */
	public void setResultListener (IResultListener listener);
	/**
	 * 设置异常监听
	 * @param listener
	 */
	public void setExceptionListener(ExceptionListener listener);
	/**
	 * 设置处理逻辑,当只提供消息时，将使用该逻辑处理消息
	 */
	public void setBusinessProcess (IBusinessProcess process);
	
	public void close( boolean isnow);
	
}
