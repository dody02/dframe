package dy.dframe.communicator;

import java.util.concurrent.TimeUnit;

/**
 * 服务配置
 * @author dy
 *
 */
public interface IServiceConfig {
	/**
	 * 读取Buffer大小
	 * @return
	 */
	public int getBufferSize();
	/**
	 * 设置Buffer大小
	 */
	public void setBufferSize();
	/**
	 * 心跳超时时间
	 * @param idleTime
	 * @param timeunit
	 */
	public void setIdleTime(long idleTime, TimeUnit timeunit);
	/**
	 * 心跳超时时间
	 * @return
	 */
	public long getIdleTime ();
	/**
	 * 心跳超时时间单位
	 * @return
	 */
	public TimeUnit getIdleTimeUnit();
	/**
	 * 设置写超时时间
	 * @param timeout
	 * @param timeUnit
	 */
	public void setWriteTimeOut (long timeout, TimeUnit timeUnit);
	/**
	 * 读取写超时时间
	 * @return
	 */
	public long getWriteTimeOut ();
	/**
	 * 获取写超时时间单位
	 * @return
	 */
	public TimeUnit getWriteTimeOutUnit();
}
