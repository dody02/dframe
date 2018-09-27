package net.sf.dframe.rmi.transport.socket;

import java.net.InetSocketAddress;
import java.net.Socket;

import net.sf.dframe.rmi.IRmiConnector;

import org.apache.log4j.Logger;


/**
 * 默认构建器
 * @author dy
 *
 */
public class DefaultRmiConnectionBuilder implements IRmiConnectionBuilder{
	
	Logger log = Logger.getLogger(DefaultRmiConnectionBuilder.class); 
	/**
	 * 配置信息
	 */
	private RmiConnectionConfig config = null;
	
	public DefaultRmiConnectionBuilder (RmiConnectionConfig config ){
		this.config = config;
	}
	
	
	@Override
	public IRmiConnector create() throws Exception {
		log.info("start to create a socket");
		Socket socket = new Socket ();		
		RmiSession session = null;
		try{
			socket.connect(new InetSocketAddress(config.getServer(),config.getPort()));
			session = new RmiSession(socket);
System.err.println("新建立一个session");	
		} catch (Exception e){
			e.printStackTrace();
			try{
				session.close();	
			}finally{
				session = null;
			}
			
			
			throw new Exception ("新建Session 异常 ",e);
		}
		
		log.info("sucess to create a session");
		return session;
	}

}
