package dy.dframe.communicator;
/**
 * 接收器
 * @author Yu
 * @version 1.0 
 */
public interface IAcceptor extends IService{
	/**
	 * 设置配置信息
	 * @param config
	 */
	public void setConfigInfo(AcceptorConfig config);
	
	/**
	 * 获取配置信息
	 * @return
	 */
	public AcceptorConfig getConfigInfo ();
	
	
	/**
	 * 追加过滤器
	 * @param filter
	 */
	public void addFilter(IFilter filter);
	
}
