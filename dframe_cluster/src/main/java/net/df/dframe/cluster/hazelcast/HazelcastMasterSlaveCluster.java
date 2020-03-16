package net.df.dframe.cluster.hazelcast;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.cluster.MembershipListener;
import com.hazelcast.config.Config;
import com.hazelcast.map.IMap;

/**
 * HazelcastMaster 
 * @author dy02
 *
 */
public class HazelcastMasterSlaveCluster extends HazelcastShardingCluster {

	private static Logger log = LoggerFactory.getLogger(HazelcastMasterSlaveCluster.class);
	
	public final static String ACTIVE_MEMBER = "ACTIVE";

	public HazelcastMasterSlaveCluster(Config cfg) {
		super(cfg);
		MembershipListener listener = new HazelcastClusterMemberLisenter(hz);
		hz.getCluster().addMembershipListener(listener );
	}
	
	/**
	 * current member is active one
	 * @return
	 */
	public boolean isMeActive() {
		return hz.getCluster().getLocalMember().getAddress().getHost().equals(getActive());
	}
	
	/**
	 * get the active host 
	 * @return active member host
	 */
	public String getActive() {
		String active = null;
		try{
			IMap<String, String> map = hz.getMap(HazelcastMasterSlaveCluster.ACTIVE_MEMBER);
			if (!map.isEmpty()) { 
				active = map.get(HazelcastMasterSlaveCluster.ACTIVE_MEMBER);
			}
		} catch (Exception e) {
			log.error("Exception when get current active member",e);
		} finally {
			
		}
		log.info("cureent active member is : "+active);
		return active;
	}
}
