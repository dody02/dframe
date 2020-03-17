package net.df.dframe.cluster.test;

import com.hazelcast.collection.IQueue;

import net.sf.dframe.cluster.hazelcast.HazelcastClusterCreater;
import net.sf.dframe.cluster.hazelcast.HazelcastMasterSlaveCluster;

public class TestQueue {
	public static void main(String[] args) throws Exception {
		HazelcastClusterCreater cc = new HazelcastClusterCreater();
		HazelcastMasterSlaveCluster c = (HazelcastMasterSlaveCluster) cc.getCluster("cluster.json");
		
		IQueue<String> a =(IQueue<String>) c.getH2Queue("test");
		
		a.put("asdf");
		String value = a.poll();
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
			a.put("value"+i);
			
			
		}
		System.out.println("OVer");
	}
}
