package net.sf.dframe.rmi;
/**
 * 代理Builder
 * @author dy
 *
 */
public interface IProxyBuilder {
	/**
	 * 构建远程代理对象
	 * @return
	 */
	public Object createProxy(final Class<?> classType);

}
