package net.sf.dframe.rmi;


/**
 * 远程连接器
 * @author dy
 *
 */
public interface IRmiConnector {
	
	/**
	 * 关闭
	 * @throws Exception
	 */
	public void close() throws Exception ;
	
	/**
	 * 读取对象
	 * @return
	 * @throws Exception
	 */
	public Object read() throws Exception;
	/**
	 * 是否关闭
	 * @return
	 */
	public boolean isClose() ;
//	/**
//	 * 是否正在等待结果
//	 * @return
//	 */
//	public boolean isWaitResult();
	
}
