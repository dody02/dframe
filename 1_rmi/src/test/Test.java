package test;

import net.sf.dframe.rmi.transport.socket.RmiBlockingServer;

public class Test {
	public static void main (String[] arg){
		
		RmiBlockingServer server = new RmiBlockingServer("localhost",9999);
		//注册一个远程对象
		server.register(test.HelloInterface.class.getName(), new HelloImpl());
		try {
			System.out.println("启动");
			server.start();
			
			/**
			 * 测试关闭
			 */
			Thread.sleep(1000);
			server.stop();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
