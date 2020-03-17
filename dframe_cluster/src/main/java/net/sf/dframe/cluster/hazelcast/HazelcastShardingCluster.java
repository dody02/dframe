package net.sf.dframe.cluster.hazelcast;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import net.sf.dframe.cluster.ICluster;

public class HazelcastShardingCluster implements  ICluster {

	protected HazelcastInstance hz;
	
	public HazelcastShardingCluster(Config cfg) {
		hz = Hazelcast.getOrCreateHazelcastInstance(cfg);
	}


	public HazelcastInstance getHz() {
		return hz;
	}


	@Override
	public void shutdown() {
		hz.shutdown();
	}

}
