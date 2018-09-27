package net.sf.dframe.concurrenty.test;

import net.sf.dframe.concurrenty.TimeoutException;
import net.sf.dframe.concurrenty.impl.DefaultSynchronizedInvokeService;

public class TestServiceThread extends Thread{
	
	private String key;
	long millis = 15000;
	public TestServiceThread(String key){
		this.key = key;
	}
	public void run (){
		try {
			System.out.println("开始执行逻辑");
			try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("执行逻辑结束，唤醒");
			DefaultSynchronizedInvokeService.getInstance().complete(key);
			
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("执行线程结束");
	}
	
}
