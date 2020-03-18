package net.df.dframe.cluster.test;

import java.util.Set;

import com.hazelcast.collection.IQueue;
import com.hazelcast.map.IMap;

import net.sf.dframe.cluster.hazelcast.HazelcastClusterCreater;
import net.sf.dframe.cluster.hazelcast.HazelcastMasterSlaveCluster;

public class TestMap2 {
	public static void main(String[] args) throws Exception {
		HazelcastClusterCreater cc = new HazelcastClusterCreater();
//		HazelcastMasterSlaveCluster c = (HazelcastMasterSlaveCluster) cc.getCluster("cluster3.json");
		HazelcastMasterSlaveCluster c = (HazelcastMasterSlaveCluster) cc.getCluster("cluster.json");
		
		IMap<String,String> a =(IMap<String,String>) c.getH2Map("test");
		
//		a.put("asdf");
//		String value = a.poll();
//		System.out.println(a.size());
//		System.out.println(value);
		
		new Thread() {
			public void run() {
				a.loadAll(true);
				Set<String> keys = a.keySet();
				for (String key:keys) {
					System.out.println(a.get(key));
				}
				
				System.out.println("map size is :"+a.size());
				a.clear();
				System.out.println("map size is :"+a.size());
			}	
		}.start();
//		
//		for (int i = 0 ;  i<  102 ; i++) {
//			System.out.println("queue size :"+a.size());
//			System.out.println("put value"+i);
//			a.put("value"+i);
//			
//			
//		}
//		System.out.println("OVer");
	}
}
