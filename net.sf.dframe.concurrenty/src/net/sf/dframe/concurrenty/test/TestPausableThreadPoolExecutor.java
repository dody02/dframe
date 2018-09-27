package net.sf.dframe.concurrenty.test;

public class TestPausableThreadPoolExecutor {
	
	PausableThreadPoolExecutor t = new PausableThreadPoolExecutor();
	public static void main(String[] args) {
		
		TestPausableThreadPoolExecutor test = new TestPausableThreadPoolExecutor();
		
		TestT tt = test.new TestT();
		tt.start();
		System.out.println("开始调用暂停");
		test.t.pause();
		System.out.println("开始调用暂停结束");
	}
	
	
	
	
	
	class TestT extends Thread {
		public void run (){
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			t.resume();
		}
	}
}
