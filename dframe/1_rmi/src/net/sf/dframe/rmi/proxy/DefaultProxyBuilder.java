package net.sf.dframe.rmi.proxy;

import java.lang.reflect.Proxy;

import net.sf.dframe.rmi.IProxyBuilder;


/**
 * 默认代理构建器
 * @author dy
 *
 */
public class DefaultProxyBuilder implements IProxyBuilder{
	
	String poolName = null;
	public DefaultProxyBuilder( String poolName ) throws Exception {
		if (poolName == null){
			throw new Exception (" pool name could not been null");
		}
		this.poolName = poolName;
	}
	
	@Override
	public Object createProxy(final Class<?> classType) {
		Object rs = null;
		try{
			rs = Proxy.newProxyInstance(classType.getClassLoader(),new Class[]{classType},new RmiInvocationHandler(classType.getName(),poolName));	
		}catch (Exception e){
			System.out.println(getClass().getName() +" 获取代理异常.返回结果为null");
			rs = null;
		}
		return rs;
	}

	
}
