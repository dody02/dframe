package net.sf.dframe.rmi.transport.socket;
/**
 * 配置
 * @author dy
 *
 */
public class RmiConnectionConfig {
	
	private String server;
	private int port;
	private long readTimeout;
	private long writeTimeout;
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public long getReadTimeout() {
		return readTimeout;
	}
	public void setReadTimeout(long readTimeout) {
		this.readTimeout = readTimeout;
	}
	public long getWriteTimeout() {
		return writeTimeout;
	}
	public void setWriteTimeout(long writeTimeout) {
		this.writeTimeout = writeTimeout;
	}
	
	
	
}
