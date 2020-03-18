package net.sf.dframe.cluster.hazelcast;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.hazelcast.cluster.MembershipEvent;
import com.hazelcast.cluster.MembershipListener;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.cp.lock.FencedLock;
import com.hazelcast.map.IMap;

/**
 * 主从模式监听
 * @author dy02
 *
 */
public class HazelcastClusterMemberLisenter implements MembershipListener{

	private HazelcastInstance hz;
	
	private static Logger log = LoggerFactory.getLogger(HazelcastClusterMemberLisenter.class);

	public HazelcastClusterMemberLisenter(HazelcastInstance hz) {
		this.hz = hz;
		log.info("create HazelcastClusterMemberLisenter instance.");
	}

	@Override
	public void memberAdded(MembershipEvent membershipEvent) {
		
		log.debug("MembershipEvent Event!"+JSONObject.toJSONString(membershipEvent));
		FencedLock  lock = hz.getCPSubsystem().getLock(HazelcastMasterSlaveCluster.ACTIVE_MEMBER);
		try{
			log.debug("try to get lock……………………");
			lock.tryLock(5, TimeUnit.SECONDS); //等待5秒获得锁
			log.debug("get ");
			IMap<String, String> map = hz.getMap(HazelcastMasterSlaveCluster.ACTIVE_MEMBER);
			if (map.isEmpty()) { //当前没有活动主节点
				log.info("current Cluster is no active member, active current member ");
				map.put(HazelcastMasterSlaveCluster.ACTIVE_MEMBER, hz.getCluster().getLocalMember().getAddress().getHost()); //让当前节点作为主服务
			} 
		} catch (Exception e) {
			log.error("Exception when set current active member",e);
		} finally {
			log.debug("try to free lock……………………");
			if (lock.isLockedByCurrentThread())
				lock.unlock();
		}		
	
	}

	@Override
	public void memberRemoved(MembershipEvent membershipEvent) {
		try{
			hz.getCPSubsystem().getLock(HazelcastMasterSlaveCluster.ACTIVE_MEMBER).tryLock(5, TimeUnit.SECONDS); //等待5秒获得锁
			IMap<String, String> map = hz.getMap(HazelcastMasterSlaveCluster.ACTIVE_MEMBER);
			if (membershipEvent.getMember().getAddress().getHost().equals(map.get(HazelcastMasterSlaveCluster.ACTIVE_MEMBER))) { //如果离线者为当前主节点，则需要另选一个。
				map.put(HazelcastMasterSlaveCluster.ACTIVE_MEMBER, hz.getCluster().getLocalMember().getAddress().getHost()); //让当前节点作为主服务
			}
		} catch (Exception e) {
			
		} finally {
			if (hz.getCPSubsystem().getLock(HazelcastMasterSlaveCluster.ACTIVE_MEMBER).isLockedByCurrentThread())
				hz.getCPSubsystem().getLock(HazelcastMasterSlaveCluster.ACTIVE_MEMBER).unlock();
		}		// TODO Auto-generated method stub
		
	}

}
