package net.df.dframe.cluster.pojo;

import java.util.List;

/**
 * 简化配置信息
 * @author dy02
 *
 */
public class ConfigMessage {
	
	private String name;
	
	private int port;
	
	private boolean isMasterSalve;
	
	private Multicast multicast;
	
	private List<String> members;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public int getPort() {
		return port;
	}
	
	public Multicast getMulticast() {
		return multicast;
	}

	public void setMulticast(Multicast multicast) {
		this.multicast = multicast;
	}

	public List<String> getMembers() {
		return members;
	}

	public void setMembers(List<String> members) {
		this.members = members;
	}
	
	public void setMasterSalve(boolean isMasterSalve) {
		this.isMasterSalve = isMasterSalve;
	}
	
	public boolean isMasterSalve() {
		return isMasterSalve;
	}
}
