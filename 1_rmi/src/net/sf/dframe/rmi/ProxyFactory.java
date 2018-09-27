package net.sf.dframe.rmi;
/**
 * 代理工厂
 * @author dy
 *
 */
public class ProxyFactory {
	
	IProxyBuilder builder = null;
	/**
	 * 设置Builder类
	 * @param builder
	 */
	public void setBuilder(IProxyBuilder builder){
		this.builder = builder;
	}
	/**
	 * 获取远程代理对象
	 * @return
	 */
	public Object getProxy(final Class<?> classType){
		return builder.createProxy(classType);
		
	}
}
