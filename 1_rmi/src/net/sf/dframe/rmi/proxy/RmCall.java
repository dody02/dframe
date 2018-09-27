package net.sf.dframe.rmi.proxy;

import java.io.Serializable;
/**
 * 远程传递接口
 * @author JerryY
 *
 */
public class RmCall implements Serializable{
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 类名
	 */
	private String className;
	/**
	 * 方法名
	 */
	private String methodName;
	/**
	 * 参数类型
	 */
	private Class<?>[] paramTypes;
	/**
	 * 参数 
	 */
	private Object[] params;
	
	
	
	public RmCall(String className, String name, Class<?>[] parameterTypes,
			Object[] args) {
		this.className = className;
		this.methodName = name;
		this.paramTypes = parameterTypes;
		this.params = args;
	}
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Class<?>[] getParamTypes() {
		return paramTypes;
	}
	public void setParamTypes(Class<?>[] paramTypes) {
		this.paramTypes = paramTypes;
	}
	public Object[] getParams() {
		return params;
	}
	public void setParams(Object[] params) {
		this.params = params;
	} 
	
	
}
