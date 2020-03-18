package net.df.dframe.cluster.test;

import com.alibaba.fastjson.JSONObject;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import net.sf.dframe.cluster.hazelcast.HazelcastConfig;

public class TestConfig {
	public static void main(String[] args) {
		HazelcastConfig hc = new HazelcastConfig();
		try {
			Config cfg = hc.readConfig("cluster2.json");
			System.out.println(JSONObject.toJSONString(cfg));
			 HazelcastInstance hz = Hazelcast.newHazelcastInstance(cfg);
			  // Get the Distributed Map from Cluster.
		        IMap map = hz.getMap("my-distributed-map");
		        //Standard Put and Get.
		        map.put("key", "value");
		        map.get("key");
		        //Concurrent Map methods, optimistic updating
		        map.putIfAbsent("somekey", "somevalue");
		        map.replace("key", "value", "newvalue");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
