package dy.dframe.communicator;


/**
 * 连接器接口。
 * 连接器对外提供的操作接口
 * @author Yu
 * @version 1.0 2009-07-15
 */

public interface IService {

	/**
	 * 启动连接器
	 * @throws Exception 
	 */
	public void start() throws Exception;
	/**
	 * 停止连接器
	 * @throws Exception 
	 */
	public void stop() throws Exception;
	/**
	 * 获取连接器状态
	 * @return
	 */
	public Object getStatus();
	
	
}
