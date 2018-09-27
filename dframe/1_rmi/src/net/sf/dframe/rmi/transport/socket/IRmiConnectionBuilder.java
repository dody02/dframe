package net.sf.dframe.rmi.transport.socket;

import net.sf.dframe.rmi.IRmiConnector;

/**
 * RMI连接构造器
 * @author dy
 *
 */
public interface IRmiConnectionBuilder {
	public IRmiConnector create() throws Exception ; 
}
