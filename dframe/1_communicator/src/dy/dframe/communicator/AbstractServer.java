package dy.dframe.communicator;

/**
 * 强制实例化时需要注入ITcpAcceptor与IUdpAcceptor
 * @author Yu
 * @version 1.0
 */
public abstract class AbstractServer implements IService{

	
	/**
	 * 构建器,注入Acceptor 
	 * @param tcpAcceptor
	 * @param udpAcceptor
	 */
	public AbstractServer ( IAcceptor tcpAcceptor , IAcceptor udpAcceptor ){
		this.tcpAcceptor = tcpAcceptor;
		this.udbAcceptor = udpAcceptor;
	}
	
	public abstract Object getStatus();

	public abstract void start() throws Exception ;

	public abstract void stop() ;
	
	/**
	 * 服务名称
	 */
	private String name ;
	
	/**
	 * TCP通讯器
	 */
	private IAcceptor tcpAcceptor;
	/**
	 * UDP通讯器
	 */
	private IAcceptor udbAcceptor;
	
	
	/**
	 * 获取TCP接收器
	 * @return
	 */
	public IAcceptor getTcpAcceptor(){
		return this.tcpAcceptor;
	}
	/**
	 * 获取Udp接收器
	 * @return
	 */
	public IAcceptor getUdpAcceptor(){
		return this.udbAcceptor;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
