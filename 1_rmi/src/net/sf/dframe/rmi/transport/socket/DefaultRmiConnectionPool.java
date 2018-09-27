package net.sf.dframe.rmi.transport.socket;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import net.sf.dframe.rmi.IRmiConnector;

import org.apache.log4j.Logger;


/**
 * 默认RMI连接池
 * @author dy
 *
 */
public class DefaultRmiConnectionPool implements IRmiConnectionPool {
	
	
	Logger log = Logger.getLogger (DefaultRmiConnectionPool.class);
	
	//private static DefaultRmiConnectionPool instance = null;
	/**
	 * 默认池大小
	 */
	final static int DEFAULT_MAXPOOLSIZE = 10;
	/**
	 * 核心大小
	 */
	final static int DEFAULT_COREPOOLSIZE = 2;
	/**
	 * 配置信息
	 */
	private IRmiConnectionBuilder rmiConnectionBuilder = null;
	/**
	 * 当前连接数
	 */
	private int currentSessionCount = 0;
	
	/**
	 * 连接池队列
	 */
	private LinkedBlockingQueue<IRmiConnector> connectors = new LinkedBlockingQueue<IRmiConnector>();
	/**
	 * 池大小
	 */
	private int poolsize = DEFAULT_MAXPOOLSIZE;
	/**
	 * 核心池大小
	 */
	private int coresize = DEFAULT_COREPOOLSIZE;
	
	
	
	
	/**
	 * 构造器
	 * @param rcb
	 * @throws Exception
	 */
	public DefaultRmiConnectionPool( IRmiConnectionBuilder rcb , int coresize , int poolsize ) throws Exception {
		this.rmiConnectionBuilder = rcb;
		this.coresize = coresize;
		this.poolsize = poolsize;
		synchronized (connectors){
			for (int i = 0 ; i < coresize ;  i ++){
				try{
					connectors.offer(rmiConnectionBuilder.create());
					
				}catch (Exception e){
//					e.printStackTrace();
					throw e;
				}finally{
					currentSessionCount++;
				}
				
			}	
		}		
		
	}
	
	public DefaultRmiConnectionPool( IRmiConnectionBuilder rcb , int coresize ) throws Exception {
		this( rcb , coresize , DEFAULT_MAXPOOLSIZE ) ;
	}
	
	public DefaultRmiConnectionPool(IRmiConnectionBuilder rcb) throws Exception {
		this( rcb , DEFAULT_COREPOOLSIZE , DEFAULT_MAXPOOLSIZE ) ;
	}
	
//	
//	public static IRmiConnectionPool getInstance ( ) throws Exception {
//		return instance;
//	} 
//	
//	public static IRmiConnectionPool getInstance ( IRmiConnectionBuilder builder) throws Exception{
//		return getInstance(DEFAULT_COREPOOLSIZE,DEFAULT_MAXPOOLSIZE,builder);
//	}
//
//	
//	public static IRmiConnectionPool getInstance (int coresize, int maxsize , IRmiConnectionBuilder builder) throws Exception{
//		if ( instance == null ){
//			instance = new DefaultRmiConnectionPool (builder,coresize);
//			instance.setCoresize(coresize);
//			instance.setPoolSize(maxsize);
//			
//		}
//		return instance;
//	}
	
	/**
	 * 关闭
	 */
	public void close() {
		synchronized (connectors){
			Iterator<IRmiConnector> it = connectors.iterator();
			while ( it.hasNext() ){
				try {
					it.next().close();
				} catch (Exception e) {
					e.printStackTrace();
				} finally{
					it.remove();
				} 
			}	
		}
		
	}
	/**
	 * 获取连接
	 */
	public IRmiConnector getConnection() throws Exception {
		synchronized (connectors){
			IRmiConnector rs = connectors.poll();
			log.debug("has rmiconnector "+ rs);
//			System.out.println("rs is : "+rs +" connectorssize is :"+connectors.size());
			
			if (rs == null){
				if (this.getPoolSize() > currentSessionCount){
					//新建一个。
					try{
//						System.err.println("pool size: "+this.getPoolSize() +" connectedsize : "+ connectors.size() + "; 新建立一个连接");
						rs = rmiConnectionBuilder.create();
						connectors.offer(rs);
//						System.err.println("connected size : "+connectors);
						log.info("the connect count is little than pool size,create a new connector");
						currentSessionCount++;
						
						System.err.println("有效连接数："+currentSessionCount);
					}catch( Exception e){
						throw new Exception (e);
					}
				}
				
			} 
			
			return rs;	
		}
		
	}
	/**
	 * 获取连接，并指定等待超时时间
	 * @param timeout
	 * @param timeunit
	 * @return
	 */
	public IRmiConnector getConnection( long timeout , TimeUnit timeunit ){
		synchronized (connectors){
			try {
				return connectors.poll(timeout, timeunit);
			} catch (InterruptedException e) {
				log.error("get connectors exception",e);
				return null;
			}	
		}
		
	}
	/**
	 * 初始化操作
	 * @param rcb2 
	 */
	public void init() throws Exception {
		
		for (int i = 0 ; i < DEFAULT_COREPOOLSIZE ;  i ++){
			connectors.offer(rmiConnectionBuilder.create());
			currentSessionCount++;
			System.err.println("有效连接数："+currentSessionCount);
		}
				
	}
	
	/**
	 * 报告连接失效
	 */
	public void reportBadConnector(IRmiConnector connector) throws Exception {
		log.warn("报告失效connector:"+connector+",将关闭并清理出连接池");
		System.err.println("报告失效connector:"+connector+",将关闭并清理出连接池");
		if (connector == null)
			return;
		synchronized (connectors){
			try{
				connector.close();			
			} catch (Exception e){
				System.out.println("关才连接异常 :"+e);
			} 
			finally{
				connectors.remove(connector);				
				currentSessionCount--;
				if (currentSessionCount < 0 )
					currentSessionCount= 0;
				System.err.println("有效连接数："+currentSessionCount);
			}	
		}
		
	}
	/**
	 * 归还连接
	 */
	public boolean returnConnection(IRmiConnector connector) throws Exception {
		synchronized (connectors){
			return connectors.offer(connector);	
		}
		
	}
	
	/**
	 * 获取池大小
	 */
	@Override
	public int getPoolSize() {
		return poolsize;
	}
	/**
	 * 设置池大小
	 */
	@Override
	public void setPoolSize( int poolsize) {
		this.poolsize = poolsize;
	}
	
	public int getCoresize() {
		return coresize;
	}
	public void setCoresize(int coresize) {
		this.coresize = coresize;
	}
	
	public IRmiConnectionBuilder getRmiConnectionBuilder() {
		return rmiConnectionBuilder;
	}
	public void setRmiConnectionBuilder(
			IRmiConnectionBuilder rmiConnectionBuilder) {
		this.rmiConnectionBuilder = rmiConnectionBuilder;
	}
	
	
}
