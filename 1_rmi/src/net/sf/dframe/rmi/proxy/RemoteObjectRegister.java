package net.sf.dframe.rmi.proxy;

import java.util.HashMap;
import java.util.Map;
/**
 * 对象注册,服务器端需要注册后，客户端才能使用该RemoteObject
 * @author dy
 *
 */
public class RemoteObjectRegister {
	
	private static RemoteObjectRegister instance = new RemoteObjectRegister();
	
	private Map<String  , Object > remoteObjects = new HashMap<String  , Object >();
	
	private RemoteObjectRegister() {
	}
	
	public static RemoteObjectRegister getInstance(){
		return instance;
	}
	
	public Object getRemoteObject (String id){
		return remoteObjects.get(id);
	}
	
	public void register (String id , Object obj){
		remoteObjects.put(id, obj);
	}
	
	public void remveRemoteObject (String id ){
		remoteObjects.remove(id);
	} 
}
