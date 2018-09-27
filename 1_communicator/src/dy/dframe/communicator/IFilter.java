package dy.dframe.communicator;


/**
 * 过滤器
 * @author Yu
 * @version 1.0
 */
public interface IFilter {
	
	/**
	 * 过滤数据方法 
	 * @return true如果通过
	 */
	public boolean doGet (ISession session, Object message);
	
	/**
	 * 创建
	 * @throws Exception
	 */
	public void init() throws Exception;
	
	/**
	 * 销毁
	 * @throws Exception
	 */
	public void destory() throws Exception;
	/**
	 * 发送数据时
	 * @return
	 */
	public boolean doSend();
	
}
