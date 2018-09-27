package net.sf.dframe.rmi.transport.socket;

import net.sf.dframe.rmi.proxy.RmCall;

/**
 * 服务端调用
 * @author dy
 *
 */
public class ServerObjectInvokeRunnable implements Runnable{
	
	private RmiSession session = null;
	private RmiServerHandler rmiServerHandler = null;
	public ServerObjectInvokeRunnable( RmiSession session , RmiServerHandler rsh) {
		this.session = session;
		this.rmiServerHandler = rsh;
	}
	
	@Override
	public void run() {
		while ( session != null && session.isClose() == false ){
			try {
				RmCall call = (RmCall) session.read();				
				Object rs = rmiServerHandler.invoke(call);
				session.write(rs);
			} catch (Exception e) {
				e.printStackTrace();
				try {
					session.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				} finally{
					session = null;
				}
			}	
		}
		
		
	}
	/**
	 * 设置句柄
	 * @param rmiServerHandler
	 */
	public void setRmiServerHandler(RmiServerHandler rmiServerHandler) {
		this.rmiServerHandler = rmiServerHandler;
	}

}
