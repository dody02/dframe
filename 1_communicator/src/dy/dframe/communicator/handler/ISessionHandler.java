package dy.dframe.communicator.handler;

import dy.dframe.communicator.ISession;

/**
 * 事件处理
 * @author Yu
 * @version 1.0
 */
public interface ISessionHandler {
	/**
	 * 创建
	 * @throws Exception
	 */
	public void onCreate(ISession session) throws Exception;
	/**
	 * 开打
	 * @throws Exception
	 */
	public void onOpen ( ISession session ) throws Exception;
	/**
	 * 关闭
	 * @throws Exception
	 */
	public void onClose ( ISession session ) throws Exception;
	/**
	 * 超时
	 * @throws Exception
	 */
	public void onIdle (ISession session) throws Exception;
	/**
	 * 异常捕捉
	 * @param cause
	 * @throws Exception
	 */
	public void onException ( ISession session , Throwable cause) throws Exception;
	/**
	 * 接收到消息
	 * @throws Exception
	 */
	public void onMessageReceived (ISession session , Object message) throws Exception;
	/**
	 * 发送消息通知
	 * @throws Exception
	 */
	public void onMessageSent( ISession session , Object message) throws Exception;
}
