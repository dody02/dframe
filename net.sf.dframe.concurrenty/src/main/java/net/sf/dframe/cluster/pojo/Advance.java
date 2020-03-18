package net.sf.dframe.cluster.pojo;

/**
 * 
 * @author dy02
 *
 */
public class Advance {
	
	private String persistencedir;
	
	private long timeout;
	
	private int size;

	private String h2user;
	private String h2password;
	

	public String getPersistencedir() {
		return persistencedir;
	}

	public void setPersistencedir(String persistencedir) {
		this.persistencedir = persistencedir;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getH2user() {
		return h2user;
	}

	public void setH2user(String h2user) {
		this.h2user = h2user;
	}

	public String getH2password() {
		return h2password;
	}

	public void setH2password(String h2password) {
		this.h2password = h2password;
	}
	
	
	
}
