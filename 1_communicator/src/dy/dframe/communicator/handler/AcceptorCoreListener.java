package dy.dframe.communicator.handler;
/**
 * 监听
 * @author JerryY
 *
 */
public interface AcceptorCoreListener {
	/**
	 * 启动
	 */
	public void onStart();
	/**
	 * 停止
	 */
	public void onStop ();
	/**
	 * 连接
	 */
	public void onConnected ();
	/**
	 * 断开连接
	 */
	public void onDisconnected ();
	
}
