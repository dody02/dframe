package net.sf.dframe.rmi.proxy;
/**
 * 远程调用异常
 * @author dy
 *
 */
public class RmException extends Throwable implements IRemoteResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public RmException() {
		super();
	}
	
	public RmException( String ex) {
		super(ex);
	}
	
	public RmException (Exception e){
		super(e);
	}
}
