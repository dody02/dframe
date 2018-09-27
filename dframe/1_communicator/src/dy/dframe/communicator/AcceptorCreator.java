package dy.dframe.communicator;

/**
 * Acceptor构建者
 * @author Yu
 * @version 1.0
 */
public class AcceptorCreator {
	
	/*#IAcceptorFactory lnkIAcceptorFactory*/
/**
	 * 根据注入的factory提供Acceptor实例
	 * @param acceptorfactory
	 * @return
	 */
	public IService getAcceptor ( IServerFactory acceptorfactory ){
		return acceptorfactory.createAcceptor();
	} 
}

