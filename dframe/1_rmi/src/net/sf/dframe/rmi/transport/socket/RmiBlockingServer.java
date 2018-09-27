package net.sf.dframe.rmi.transport.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.sf.dframe.rmi.IRmiServer;
import net.sf.dframe.rmi.proxy.RemoteObjectRegister;

import org.apache.log4j.Logger;


/**
 * 远程服务
 * 阻塞方式
 * @author dy
 *
 */
public class RmiBlockingServer implements IRmiServer{
	
	Logger log = Logger.getLogger (RmiBlockingServer.class);
	private String host = "localhost";
	private int port ;
	private int clientSize = 10;
	private boolean stop = false;
	/**
	 * 处理句柄
	 */
	private RmiServerHandler rsh = new RmiServerHandler();
//	/**
//	 * 服务注册表
//	 */
//	private Map<String, Object> remoteObjects = new HashMap<String, Object>();
	/**
	 * 处理线程池
	 */
	private ExecutorService execs  = null;//Executors.newFixedThreadPool(clientSize);
	private ExecutorService singleExe = null;//Executors.newSingleThreadExecutor();
	/**
	 * 服务
	 */
	private ServerSocket server = null; 
	
	public RmiBlockingServer() {
	}
	
	public RmiBlockingServer(String host , int port ) {
		this.host = host;
		this.port = port;
	}
	
	public RmiBlockingServer(String host , int port , int clientSize) {
		this.host = host;
		this.port = port;
		this.clientSize = clientSize;
	}
	
	
	/**
	 * 注册服务对象
	 */
	public void register (String id , Object remoteObject){
		RemoteObjectRegister.getInstance().register(id, remoteObject);
	}


	/**
	 * 获取状态
	 * @return
	 */
	public Object getStatus() {
		return toString();
	}


	/**
	 * 启动
	 * @throws Exception
	 */
	public void start() throws Exception {
		init();
//		int clientCount = 0 ;
		singleExe.execute(new Runnable(){

			@Override
			public void run() {
				while ( !stop ) {
					//System.out.println("开始");			
								Socket socket;
								try {
									socket = server.accept();
									log.info("accept a connector from :"+socket.getRemoteSocketAddress());
									RmiSession session = createSession(socket);
									Runnable run = new ServerObjectInvokeRunnable(session,rsh);
									execs.execute(run);
								} catch (IOException e) {
									log.error("接受客户端连接异常 ",e);
								}
					//System.out.println("有连接");			
								//log.info("accept a connector from :"+socket.getRemoteSocketAddress());
								//clientCount ++;
								
								///if ( clientCount >= clientSize ) {
								//	socket.close();
								//	System.err.println("超出客户端连接数");
									//log.warn("超出客户端连接数");
								//} else {
//									RmiSession session = createSession(socket);
//									Runnable run = new ServerObjectInvokeRunnable(session,rsh);
//									execs.execute(run);
								//}
								
								
							}
			}});
		
		
//		singleExe.execute(new Runnable(){
//
//			@Override
//			public void run() {
//				while (true){
//					try {
//						Socket socket = server.accept();
//						RmiSession session = createSession(socket);
//						Runnable run = new ServerObjectInvokeRunnable(session,rsh);
//						execs.execute(run);
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}	
//				}
//				
//			}
//			
//		});
	
	
	
	
	}
	
	
		


	/**
	 * 停止
	 * @throws Exception
	 */
	public void stop() throws Exception {
		this.stop = true;
		if (singleExe != null)
		singleExe.shutdown();
		if (server != null)
			server.close();
		if (execs!= null)
			execs.shutdown();
	}
	
	
	/**
	 * 初始化 
	 */
	private void init () throws Exception {
		server = new ServerSocket();
//		server.setReuseAddress(true);
		server.setPerformancePreferences(2, 3, 1);
		server.bind(new InetSocketAddress(host,port));		
		execs  = Executors.newFixedThreadPool(clientSize);
		singleExe = Executors.newSingleThreadExecutor();
		log.info("RMI服务器初始化完毕.");
	}
		
	/**
	 * 建立session
	 * @param sck
	 * @return
	 * @throws IOException
	 */
	private RmiSession createSession (Socket sck) throws IOException{
		RmiSession session = new RmiSession (sck);
		return session;
	}
	
}
