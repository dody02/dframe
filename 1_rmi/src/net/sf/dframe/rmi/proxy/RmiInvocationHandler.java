package net.sf.dframe.rmi.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.rmi.RemoteException;

import net.sf.dframe.rmi.transport.socket.PoolManager;
import net.sf.dframe.rmi.transport.socket.RmiSession;


/**
 * 远程调用Handler
 * @author dy
 *
 */
public class RmiInvocationHandler implements InvocationHandler{

	private String className = null;
	private String poolName = null;
	public RmiInvocationHandler( String className , String poolName) {
		this.className = className;
		this.poolName = poolName;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		
		/**
		 * 从连接池中获取连接。
		 */
		//RmiSession session = (RmiSession) DefaultRmiConnectionPool.getInstance().getConnection();
		RmiSession session = (RmiSession)PoolManager.getPool(poolName).getConnection();
		Object result  =  null;
		try{
			// connector = new Connector (host,port);
			RmCall call = new RmCall(className, method.getName(), method
					.getParameterTypes(), args);
System.out.println("session id : "+session.getId());
			synchronized (session){
				session.write(call);//发送				
				result =  session.read();// 这是一个阻塞方法	
			}
			
			// Object result = call.getResult();
			if (result instanceof RmException) {
				throw new RemoteException("invokeException", (Throwable) result);// 将异常包装成RemoteException
			} 
			//DefaultRmiConnectionPool.getInstance().returnConnection(session);
			PoolManager.getPool(poolName).returnConnection(session);
		}catch (Exception e){
			//e.printStackTrace();
			System.out.println("RmiInvocationHandle 异常："+ e+" ,将报告失败");
//			DefaultRmiConnectionPool.getInstance().reportBadConnector(session);
			PoolManager.getPool(poolName).reportBadConnector(session);
			throw new Exception("invokeException", e);
		}
		return result;
	}

}
