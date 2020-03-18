package net.df.dframe.cluster.test;


import com.hazelcast.map.IMap;

import net.sf.dframe.cluster.hazelcast.HazelcastClusterCreater;
import net.sf.dframe.cluster.hazelcast.HazelcastMasterSlaveCluster;

public class TestCluster2 {
	public static void main(String[] args) throws Exception {
		HazelcastClusterCreater cc = new HazelcastClusterCreater();
		HazelcastMasterSlaveCluster c = (HazelcastMasterSlaveCluster) cc.getCluster("cluster3.json");
		
		System.out.println(c.isMeActive());
		while (true) {
			IMap<String, String>  aaa = c.getArributesMap();
			System.out.println(aaa.get("1"));
			Thread.sleep(1000);
		}
	}
}
