package dy.dframe.communicator;

import java.net.SocketAddress;

/**
 * 抽象出来的会话层
 * @author Yu
 * @version 1.0 2009-08-04
 */
public interface ISession {
	/**
	 * 写数据
	 * @param message
	 * @throws Exception
	 */
	public void write (Object message) throws Exception;
	
//	public void read () throws Exception;
	/**
	 * 获取id
	 * @return
	 */
	public String getId () ;
	/**
	 * 关闭
	 * @throws Exception
	 */
	public void close() throws Exception;
	/**
	 * 获取属性
	 * @param key
	 * @return
	 * @throws Exception 
	 */
	public Object getAttribute (Object key) throws Exception;
	/**
	 * 设置属性
	 * @param key
	 * @param value
	 * @throws Exception 
	 */
	public void setAttribute (Object key , Object value) throws Exception;
	
	/**
	 * 获取远程计算机地址
	 * @return
	 */
	public SocketAddress getRemoteAddress ();
	/**
	 * 获取本地计算机地址
	 * @return
	 */
	public SocketAddress getLocalAddress();
}
