package net.sf.dframe.rmi.transport.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.UUID;

import net.sf.dframe.rmi.IRmiConnector;

/**
 * RMI Session
 * @author dy
 *
 */
public class RmiSession implements IRmiConnector{
	
	/**
	 * 连接
	 */
	private Socket socket = null;
	/**
	 * 输入流
	 */
	private InputStream is = null;
	/**
	 * 输入对象流
	 */
	private ObjectInputStream ois = null;
	/**
	 * 输出流
	 */
	private OutputStream os = null;
	/**
	 * 输出对象流
	 */
	private ObjectOutputStream oos = null;  
	
	/**
	 * 关闭标志
	 */
	private boolean close = true;
	/**
	 * 标志
	 */
//	private boolean s = false;
	
	private String id = null;
	
	/**
	 * 构造器
	 * @param socket
	 * @throws IOException 
	 */
	public RmiSession (Socket socket) throws IOException{
		this.socket = socket;
		init();
		
	}
	
	
	
	/**
	 * 读取返回对象
	 */
	@Override
	public Object read() throws Exception {
		return ois.readObject();
		
	}
	
	/**
	 * 关闭会话
	 * @throws Exception
	 */
	public void close() throws Exception {
		try{
			if ( ois != null ){
				ois.close();
			} else {
				if ( is != null ){
					is.close();
				}
			}
			if ( oos != null ){
				oos.close();
			} else {
				if ( os != null ){
					os.close();
				}
			}
			if (socket != null){
				socket.close();	
			}	
		} finally{
			ois =null;
			oos = null;
			is = null;
			os = null;
			socket = null;
			close = true;
		}
		
		
	}
	/**
	 * 获取属性值
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Object getAttribute(Object key) throws Exception {
		return null;
	}
	/**
	 * 获取ID
	 * @return
	 */
	public String getId() {		
		return this.id;
	}
	/**
	 * 获取地址
	 * @return
	 */
	public SocketAddress getLocalAddress() {
		if ( socket != null ){
			return new InetSocketAddress(socket.getLocalAddress().getHostAddress(),socket.getLocalPort());
		} else {
			return null;
		}
	}
	/**
	 * 获取远程地址
	 * @return
	 */
	public SocketAddress getRemoteAddress() {
		if ( socket != null ){
			return socket.getRemoteSocketAddress();
		}
		return null;
	}
	/**
	 * 设置属性
	 * @param key
	 * @param value
	 * @throws Exception
	 */
	public void setAttribute(Object key, Object value) throws Exception {
		throw new Exception ("Un Support Exception");
	}
	/**
	 * 写
	 * @param message
	 * @throws Exception
	 */
	public void write(Object message) throws Exception {
		oos.writeObject(message);
		oos.flush();
	}

	
		
	/**
	 * 初始化操作
	 * @throws IOException 
	 */
	private void init () throws IOException {
		UUID objUUID = java.util.UUID.randomUUID();
		id = objUUID.toString();
				
		/**
		 * 初始化输出流
		 */
		os = socket.getOutputStream();
		oos = new ObjectOutputStream(os);
		//oos.flush();
		/**
		 * 初始化输入流
		 */
//		System.err.println("close: "+socket.isClosed() + ", input:"+socket.isInputShutdown() +" output :"+socket.isOutputShutdown());
		is = socket.getInputStream();
		ois = new ObjectInputStream (is);
		
		
		this.close = false;
	}


	/**
	 * 是否关闭
	 */
	@Override
	public boolean isClose() {
		return close;
	}

	
	
}
