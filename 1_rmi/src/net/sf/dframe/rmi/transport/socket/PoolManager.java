package net.sf.dframe.rmi.transport.socket;

import java.util.Collection;

import net.sf.dframe.rmi.utils.ConcurrentMap;


/**
 * 连接池管理器
 * @author dy
 *
 */
public class PoolManager {
	/**
	 * 池
	 */
	private static ConcurrentMap<String ,IRmiConnectionPool> pools = new ConcurrentMap<String,IRmiConnectionPool> ();
	
	
	private PoolManager() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 获取连接池
	 * @param poolName
	 * @return
	 */
	public static IRmiConnectionPool getPool ( String poolName ){
		return pools.get(poolName);
	}
	/**
	 * 添加连接池
	 * @param poolName
	 * @param pool
	 * @throws Exception 
	 */
	public static void addPool (String poolName , IRmiConnectionPool pool) throws Exception{
		if (pools.get(poolName) != null ){
			throw new Exception (" pool name already exist");
		} else {
			pools.put(poolName, pool);	
		}	
	}
	
	/**
	 * 是否存在
	 * @param poolName
	 * @return
	 */
	public static boolean isExist ( String poolName ){
		return pools.containsKey(poolName);		
	}
	/**
	 * 返回
	 * @return
	 */
	public static Collection<IRmiConnectionPool> getPools () {
		return pools.values();
	}
	/**
	 * 关闭指定的池
	 * @param poolName
	 */
	public static void closePool (String poolName){
		IRmiConnectionPool pool = pools.get(poolName);
		if (pool != null){
			pool.close();
		}
	}
}
