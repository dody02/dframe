package net.sf.dframe.rmi.transport.socket;

import net.sf.dframe.rmi.IRmiConnector;

/**
 * 远程连接池
 * @author dy
 *
 */
public interface IRmiConnectionPool {
		
	/**
	 * 初始化池
	 * @throws Exception
	 */
	public void init () throws Exception;
	/**
	 * 关闭
	 */
	public void close();
	/**
	 * 获取RMI连接
	 * @throws Exception
	 */
	public IRmiConnector getConnection()throws Exception ;
	/**
	 * 返回连接
	 * @param connector
	 * @throws Exception
	 */
	public boolean returnConnection(IRmiConnector connector) throws Exception;
	/**
	 * 报告坏连接
	 * @param connector
	 * @throws Exception
	 */
	public void reportBadConnector (IRmiConnector connector) throws Exception ;
	
	/**
	 * 池大小
	 * @return
	 */
	public int getPoolSize () ;
	/**
	 * 设置池大小
	 */
	public void setPoolSize (  int poolsize );
	/**
	 * 获取核心数
	 * @return
	 */
	public int getCoresize();
	/**
	 * 设置核心数
	 * @param coresize
	 */
	public void setCoresize(int coresize) ;
}
