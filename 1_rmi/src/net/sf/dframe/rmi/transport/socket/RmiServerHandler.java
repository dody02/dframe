package net.sf.dframe.rmi.transport.socket;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.sf.dframe.rmi.proxy.IRemoteResult;
import net.sf.dframe.rmi.proxy.RemoteObjectRegister;
import net.sf.dframe.rmi.proxy.RmCall;
import net.sf.dframe.rmi.proxy.RmException;


/**
 * 事件处理,有请求来时
 * @author dy
 *
 */
public class RmiServerHandler {
	
	
	public Object invoke ( RmCall call ) throws SecurityException, NoSuchMethodException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		
		Object result = null;
		
		String className = call.getClassName();
		String methodName = call.getMethodName();
		Object[] params = call.getParams();
		Class<?> classType = Class.forName(className);
		Class<?>[] paramTypes = call.getParamTypes();
		Method method = classType.getMethod(methodName, paramTypes);
		//从注册表中获取
		Object remoteObject = RemoteObjectRegister.getInstance().getRemoteObject(className);
		
		if ( remoteObject == null ){
			result = new RmException (className + "的远程对象不存在！");
		} else {
			result =  method.invoke(remoteObject, params);
		}
		
		
		return result;
	}
}
