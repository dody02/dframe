package dy.dframe.communicator;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dy.dframe.communicator.handler.ISessionHandler;

/**
 * 接收器
 * @author Yu
 *
 */
public abstract class AbstractAcceptor implements IAcceptor{
	/**
	 * 过滤器列表
	 * 更改操作本身是线程安全的
	 */
	private List<IFilter> filters = new CopyOnWriteArrayList<IFilter>();
	/**
	 * 配置信息
	 */
	private AcceptorConfig config = null;
	/**
	 * 事件处理
	 */
	private ISessionHandler handle = null;
	
	
	/**
	 * 追加过滤器
	 */
	public void addFilter(IFilter filter) {
		filters.add(filter);
	}
	/**
	 * 将过滤器添加到第一个位置
	 * @param filter
	 */
	public void addFirstFilter ( IFilter filter ){
		filters.add(0,filter);
	}
	/**
	 * 获取过滤器列表
	 * @return
	 */
	public List<IFilter> getFilters(){
		return this.filters;
	}
	
	public AcceptorConfig getConfigInfo() {
		return config;
	}

	public void setConfigInfo(AcceptorConfig config) {
		this.config = config;
	}
	
	/**
	 * 事件处理
	 * @param handle
	 */
	public void setHandle(ISessionHandler handle) {
		this.handle = handle;
	}
	/**
	 * 事件处理
	 * @return
	 */
	public ISessionHandler getHandle() {
		return handle;
	}
	
	
}
