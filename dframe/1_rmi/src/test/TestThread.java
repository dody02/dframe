package test;

import net.sf.dframe.rmi.ProxyFactory;

public class TestThread extends Thread{
	
	
	private ProxyFactory factory = null;
	
	public TestThread ( ProxyFactory factory ){
		this.factory = factory;
	}
	
	public void run (){
		
		System.out.println("线程："+this.getId()+"开始获取代理");
		HelloInterface hello = (HelloInterface) factory.getProxy(HelloInterface.class);
		while ( hello == null ){
			System.out.println("代理为null，等待十秒再试");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 hello = (HelloInterface) factory.getProxy(HelloInterface.class);
		}
		System.out.println("Proxy class:"+hello.getClass());
		String rs = null;
		while (rs == null ){
			try{
				rs = hello.hello(" 线程"+getId());
				Thread.sleep(10000);
			}catch (Exception e){
//				e.printStackTrace();
			}
			
		}
		
		System.out.println("线程"+getId()+"，调用结果是："+rs);
		
		
	}
}
