package test;

import net.sf.dframe.rmi.IProxyBuilder;
import net.sf.dframe.rmi.ProxyFactory;
import net.sf.dframe.rmi.proxy.DefaultProxyBuilder;
import net.sf.dframe.rmi.transport.socket.DefaultRmiConnectionBuilder;
import net.sf.dframe.rmi.transport.socket.DefaultRmiConnectionPool;
import net.sf.dframe.rmi.transport.socket.IRmiConnectionBuilder;
import net.sf.dframe.rmi.transport.socket.IRmiConnectionPool;
import net.sf.dframe.rmi.transport.socket.PoolManager;
import net.sf.dframe.rmi.transport.socket.RmiConnectionConfig;
//import dy.dframe.rmi.transport.socket.IRmiConnectionPool;

public class TestClient {
	
	public static void main (String[] arg){
		
		/**
		 * 初始化连接池
		 */
		RmiConnectionConfig config = new RmiConnectionConfig();
		config.setServer("localhost");
		config.setPort(9999);
		IRmiConnectionBuilder connectorBuilder = new DefaultRmiConnectionBuilder(config);
		   
		try {
			IRmiConnectionPool pool = new DefaultRmiConnectionPool( connectorBuilder , 0, 1 );
			PoolManager.addPool("defualt", pool);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * 建立代理工厂
		 * 
		 */
		IProxyBuilder builder = null;
		try {
			builder = new DefaultProxyBuilder ("defualt");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		final ProxyFactory factory = new ProxyFactory();
		factory.setBuilder(builder);
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < 1000 ; i ++){
//			
//			/**
//			 * 获取代理
//			 */
			System.out.println("开始获取代理");
			HelloInterface hello = (HelloInterface) factory.getProxy(HelloInterface.class);
			System.out.println("Proxy class:"+hello.getClass());
			System.out.println("call:"+hello.hello("abc"));	
		}
		
		
		
//		for ( int i = 0 ; i < 2000 ; i ++){
//			
//			new Thread(){
//				
//				public void run (){
////					System.out.println("新开一个线程");
//					/**
//					 * 获取代理
//					 */
////					System.out.println("开始获取代理");
//					HelloInterface hello = (HelloInterface) factory.getProxy(HelloInterface.class);
////					System.out.println("Proxy class:"+hello.getClass());
//					System.out.println("call:"+hello.hello("abc") );					
//					
//					Object a = new Object();
//					synchronized ( a ){
//						try {
//							a.wait();
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//					}		
//				}
//				
//			}.start();
//				
//		}
		
		
		
		
		
		
//		for (int i =0 ; i < 200; i ++){
//			new TestThread (factory).start();
//		}
		
	}
	
}
