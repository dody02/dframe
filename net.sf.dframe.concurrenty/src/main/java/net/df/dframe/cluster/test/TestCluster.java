package net.df.dframe.cluster.test;

import com.hazelcast.collection.IQueue;

import net.sf.dframe.cluster.hazelcast.HazelcastClusterCreater;
import net.sf.dframe.cluster.hazelcast.HazelcastMasterSlaveCluster;

public class TestCluster {
	public static void main(String[] args) throws Exception {
		HazelcastClusterCreater cc = new HazelcastClusterCreater();
		HazelcastMasterSlaveCluster c = (HazelcastMasterSlaveCluster) cc.getCluster("cluster.json");
		
		System.out.println(c.isMeActive());
	
		System.out.println("OVer");
	}
}
