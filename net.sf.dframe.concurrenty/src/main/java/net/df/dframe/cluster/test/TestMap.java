package net.df.dframe.cluster.test;

import com.hazelcast.collection.IQueue;
import com.hazelcast.map.IMap;

import net.sf.dframe.cluster.hazelcast.HazelcastClusterCreater;
import net.sf.dframe.cluster.hazelcast.HazelcastMasterSlaveCluster;

public class TestMap {
	public static void main(String[] args) throws Exception {
		HazelcastClusterCreater cc = new HazelcastClusterCreater();
		HazelcastMasterSlaveCluster c = (HazelcastMasterSlaveCluster) cc.getCluster("cluster.json");
		
		IMap<String,String> a =(IMap<String,String>) c.getH2Map("test");
		
		a.put("asdfasdf","asdf");
		String value = a.get("asdfasdf");
		System.out.println(a.size());
		System.out.println(value);
		a.remove("asdfasdf");
		value = a.get("asdfasdf");
		System.out.println(a.size());
		System.out.println(value);
		
//		new Thread() {
//			public void run() {
//				try {
//					Thread.sleep(30000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				System.out.println(a.size());
//				while(a.size() > 1)
//				System.out.println("get from queue:"+a.poll());			}
//		}.start();
		
		for (int i = 0 ;  i<  102 ; i++) {
			System.out.println("queue size :"+a.size());
			System.out.println("put value"+i);
			a.put(String.valueOf(i),"value"+i);
			
			
		}
		System.out.println("OVer");
	}
}
