package test;

public class HelloImpl implements HelloInterface{

	@Override
	public String hello(String arg) {
		// TODO Auto-generated method stub
//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return "hello"+arg;		
	}
	
}
