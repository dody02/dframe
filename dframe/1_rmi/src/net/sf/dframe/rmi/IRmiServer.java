package net.sf.dframe.rmi;


/**
 * RMI服务器 
 * @author dy
 *
 */
public interface IRmiServer {
	
	
	/**
	 * 远程对象
	 * @param id
	 * @param remoteObject
	 */
	public void register (String id , Object remoteObject);
}
