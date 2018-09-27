package net.sf.dframe.concurrenty;

import java.util.concurrent.TimeUnit;

/**
 * 同步调用服务
 * 该服务使用内部线程池；提供同步一些异步的操作。
 * 例如在GPS系统中，下发信息给终端到等待终端回复到来这段时间一直阻塞，
 * 直到超时或者等待条件成立时操作才返回。
 * 
 * @author Do
 *
 */
public interface SynchronizedInvokeService {
	
	
	/**
	 * 注册一个同步处理。返回一个String类型的{@link SynchronizedInvoke} 信号量KEY；
	 * 创建一个{@link SynchronizedInvoke}信号量。
	 * @see SynchronizedInvoke
	 * @return
	 */
	public String register (Object...args);
	public String register ();
	
	/**
	 * 
	 * @param key
	 */
	public void unRegister (String key );
	/**
	 * 获取任务执行状态
	 * @return
	 */
	public boolean getCompleteStatus (String key );
	
	/**
	 * 同步调用，该方法将使调用线程引起阻塞
	 * @param key 同步信号key 
	 * @param timeout 等待超时时间
	 * @param unit timeout参数的时间单位
	 */
	public void synchronizedInvoke (String key ,long timeout , TimeUnit unit) ;
	/**
	 * 该方法作用同synchronizedInvoke (long timeout , TimeUnit unit)，时间单位为ms
	 * @param timeout
	 */
	public void synchronizedInvoke (String key , long timeout) ;
	/**
	 * 完成
	 * @param key
	 */
	public void complete(String key) throws TimeoutException;
	
}
