package asynchronous.test;

import net.sf.dframe.asynchronous.IBusinessProcess;
import net.sf.dframe.asynchronous.impl.SimpleAsynchronousService;

public class Main {
	
	public static void main(String[] args) {
		
		//每次只执行四个，
		SimpleAsynchronousService sas = new SimpleAsynchronousService(20,100,600000000);
		sas.setBusinessProcess(new IBusinessProcess(){

//			@Override
			public Object doProcess(Object obj) {
				
				System.out.println(obj+" , "+ Thread.currentThread().getId()+" time:"+System.nanoTime());
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("执行完毕："+obj);
				return null;
			}});
		
		
		for ( int i = 0 ; i < 20 ; i ++ ){
			try {
				sas.submit("Hello"+i);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		try {
			sas.submit("最后一个");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sas.close(true);
		
		
	}
	
}
