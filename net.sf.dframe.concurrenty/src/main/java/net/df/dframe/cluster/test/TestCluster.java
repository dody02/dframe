package net.df.dframe.cluster.test;


import com.hazelcast.map.IMap;

import net.sf.dframe.cluster.hazelcast.HazelcastClusterCreater;
import net.sf.dframe.cluster.hazelcast.HazelcastMasterSlaveCluster;

public class TestCluster {
	public static void main(String[] args) throws Exception {
		HazelcastClusterCreater cc = new HazelcastClusterCreater();
		HazelcastMasterSlaveCluster c = (HazelcastMasterSlaveCluster) cc.getCluster("cluster.json");
		
		System.out.println(c.isMeActive());
		IMap<String, String>  aaa = c.getArributesMap();
//		aaa.put("1", "3");
		aaa.delete("1");
		
		System.out.println("OVer");
	}
}
