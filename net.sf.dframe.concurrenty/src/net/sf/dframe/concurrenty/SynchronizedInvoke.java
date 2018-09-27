package net.sf.dframe.concurrenty;

import java.util.concurrent.TimeUnit;

/**
 * 调用同步信号；没有必要使用CompleteInvoke信号。同一个信号对象两个方法就行。
 * 从{@link SynchronizedInvokeFactory}获取的完成信号对象
 * 在 SynchronizedInvokeFactory调用同步等待前，需要先建立一个
 * @see SynchronizedInvokeFactory
 * @author do
 *
 */
public interface SynchronizedInvoke {
	
	/**
	 * 同步，该方法将引起调用线程阻塞。
	 * 在调用本方法前，需要先调用 方法，获取完成信号对象, 以便唤醒时使用。
	 */	
	public void pause(long timeout , TimeUnit unit);
	/**
	 * 唤醒阻塞线程
	 */
	public void resume();
	/**
	 * 获取完成调用状态
	 * @return true 顺利完成，没有超时；false 超时
	 */
	public boolean getCompleteStatus();
	/**
	 * true 则超时
	 * @return
	 */
	public boolean isTimeout();
}
