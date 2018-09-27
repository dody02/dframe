package dy.dframe.communicator.nio;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;

import dy.dframe.communicator.AbstractAcceptor;
/**
 * Acceptor
 * @author dy
 *
 */
public class NioTcpAcceptor extends AbstractAcceptor{
	
	/**
	 * 通道
	 */
	ServerSocketChannel serverSocketChannel = null;
	
	
	@Override
	public Object getStatus() {																	
		return toString();
	}

	@Override
	public void start() throws Exception {
		
	}

	@Override
	public void stop() throws Exception {
		
	}
	/**
	 * 初始化操作
	 * @throws IOException
	 */
	public void init () throws IOException {
		serverSocketChannel =  ServerSocketChannel.open();	
		
	}
}
