package net.sf.dframe.cluster.hazelcast;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.config.XmlConfigBuilder;

import net.sf.dframe.cluster.hazelcast.h2.DataBase;
import net.sf.dframe.cluster.pojo.ConfigMessage;

/**
 * 简化用户配置项
 * @author dy02
 *
 */
public class HazelcastConfig {

	private static Logger log = LoggerFactory.getLogger(HazelcastConfig.class);
	private final String configUrl = "cluster.json";
	private final String xmlUrl = "hazelcast.xml";
	private ConfigMessage cm ;
	
	private DataBase db;
	/**
	 * 读取
	 * @param url
	 * @return
	 */
	public Config readConfig(String url) throws Exception {
		if(url == null) {
			url = this.configUrl;
			log.info("use default config file:"+url);
		}
		String cfgContent = FileUtils.readFileToString(new File(url),"utf8");
		cm = JSONObject.parseObject(cfgContent,ConfigMessage.class);
		Config config = new Config();
		NetworkConfig network = config.getNetworkConfig();
		if (cm.getPort() != 0) {
			network.setPort(cm.getPort());
			network.setPortAutoIncrement(false);
		} else {
			network.setPortAutoIncrement(true);
		}
		
		JoinConfig join = network.getJoin();
		if (cm.getMulticast() != null ) { //使用多播
			if (cm.getMulticast().getGroup() == null || cm.getMulticast().getGroup().isEmpty()) {
				throw new Exception ("could not found multicast group config value!");
			} else {
				join.getMulticastConfig().setEnabled(true);
				join.getMulticastConfig().setMulticastGroup(cm.getMulticast().getGroup());
				join.getMulticastConfig().setMulticastPort(cm.getMulticast().getPort());
			} 
		} else 	if (cm.getMembers()!= null && cm.getMembers().size() > 0) { //使用ip表
			join.getMulticastConfig().setEnabled(false);
			join.getTcpIpConfig().setMembers(cm.getMembers()).setEnabled(true);
		}
		
		
		config.setInstanceName(cm.getName());
		return config;
	}
	
	/**
	 * 读取配置文件异常
	 * @return
	 * @throws Exception
	 */
	public Config readConfig() throws Exception {
		return readConfig(null);
	}
	
	/**
	 * 读取xml配置
	 * @param xmlConfigUrl
	 * @return
	 * @throws Exception
	 */
	public Config readXmlConfig(String xmlConfigUrl) throws Exception {
		if (xmlConfigUrl == null)
			xmlConfigUrl = this.xmlUrl;
		return new XmlConfigBuilder(xmlConfigUrl).build();
	}
	
	public Config readXmlConfig() throws Exception {
		return readXmlConfig(null);
	}
	
	public ConfigMessage getCm() {
		return cm;
	}
	
}
