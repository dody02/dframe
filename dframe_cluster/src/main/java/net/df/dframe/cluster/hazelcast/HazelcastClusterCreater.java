package net.df.dframe.cluster.hazelcast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.hazelcast.config.Config;

import net.df.dframe.cluster.ICluster;

/**
 * 
 * @author dy02
 *
 */
public class HazelcastClusterCreater {
	
    private static Logger log = LoggerFactory.getLogger(HazelcastClusterCreater.class);
	
	private HazelcastConfig config = new HazelcastConfig();
	
	private Config cfg;

	
	/**
	 *  构建Cluster
	 * @param url
	 * @return
	 * @throws Exception 
	 */
	public ICluster getCluster(String url) throws Exception {
		
		ICluster result = null;
		
		if (url == null) {
			cfg = config.readConfig();
			log.info("config url is null, use default config info to create Cluster");
		} else {
			if ( url.endsWith("xml")) {
				cfg = config.readXmlConfig(url);
				log.info("create cluster by xml file "+url);
			}else {
				cfg = config.readConfig(url);
				log.info("create cluster by json file "+ url);
			}
		}
		
		if (config.getCm().isMasterSalve()) {
			result = new HazelcastMasterSlaveCluster(cfg);
			log.info("create HazelcastMasterSlaveCluster by :"+JSONObject.toJSONString(cfg));
		} else {
			result = new HazelcastShardingCluster(cfg);
			log.info("create HazelcastShardingCluster by :"+JSONObject.toJSONString(cfg));

		}
		
		return result;
	}
	
}
