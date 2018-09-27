package net.sf.dframe.concurrenty.test;

import net.sf.dframe.concurrenty.impl.DefaultSynchronizedInvokeService;

public class TestServiceMain extends Thread{
	public static void main(String[] args) {
		for  ( int i = 0 ; i < 10000; i ++ ){
			new TestServiceMain().start();
		}
	}
	
	public void run (){
		test();
	}
	
	public static void test (){
		String key = DefaultSynchronizedInvokeService.getInstance().register();
		System.out.println("注册了 key是："+key);
		TestServiceThread tt = new TestServiceThread(key);
		System.out.println("开始执行");
		tt.start();
		System.out.println("开始阻塞等待");
		DefaultSynchronizedInvokeService.getInstance().synchronizedInvoke(key, 10000);
		System.out.println("完毕！是否完成："+DefaultSynchronizedInvokeService.getInstance().getCompleteStatus(key));
		DefaultSynchronizedInvokeService.getInstance().unRegister(key);
		System.out.println("执行完毕");
	}
}
