package dy.dframe.communicator;

import java.util.concurrent.TimeUnit;

/**
 * Acceptor配置文件
 * @author Yu
 * @version 1.0
 *
 */
public class AcceptorConfig {

	/**
	 * 连接类型
	 * @author Yu
	 * @version 1.0
	 */
	public enum Type {
		TCPSERVER,
		UDPSERVER,
		TCPCLIENT,
		UDPCLIENT;
	}
	
	/**
	 * 服务器IP
	 */
	private String ip;
	/**
	 * 服务器端口
	 */
	private int port;
	/**
	 * 服务器类型
	 */
	private Type type;
	/**
	 * 超时时间
	 * 默认20秒
	 */
	private long idle = 20;
	/**
	 * 缓存大小
	 */
	private int buffersize ;
	/**
	 * 超时时间单位
	 * 默认为毫秒
	 */
	private TimeUnit idleTimeunit = TimeUnit.MILLISECONDS;
	
	
	
	/**
	 * 设置IP
	 * @param ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * 设置端口
	 * @param port
	 */
	public void setPort(int port) {
		this.port = port;
	}
	/**
	 * 设备类型
	 * @param type
	 */
	public void setType(Type type) {
		this.type = type;
	}
	/**
	 * 获取服务器端口
	 * @return
	 */
	public int getPort() {
		return port;
	}
	/**
	 * 获取服务器IP
	 * @return
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * 获取服务器类型
	 * @return
	 */
	public Type getType() {
		return type;
	}
	/**
	 * 获取缓存大小
	 * @return
	 */
	public int getBuffersize() {
		return buffersize;
	}
	/**
	 * 设置缓存大小
	 * @param buffersize
	 */
	public void setBuffersize(int buffersize) {
		this.buffersize = buffersize;
	}
	/**
	 * 获取超时时间
	 * @return
	 */
	public long getIdle() {
		return idle;
	}
	/**
	 * 设置超时时间
	 * @param idle
	 */
	public void setIdle(long idle) {
		this.idle = idle;
	}
	/**
	 * 获取超时时间单位
	 * @return
	 */
	public TimeUnit getIdleTimeunit() {
		return idleTimeunit;
	}
	/**
	 * 设置超时时间单位
	 * @param idleTimeunit
	 */
	public void setIdleTimeunit(TimeUnit idleTimeunit) {
		this.idleTimeunit = idleTimeunit;
	}
	
}
