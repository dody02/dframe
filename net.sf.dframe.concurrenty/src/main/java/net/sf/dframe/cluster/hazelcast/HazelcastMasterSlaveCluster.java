package net.sf.dframe.cluster.hazelcast;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.cluster.MembershipListener;
import com.hazelcast.collection.IList;
import com.hazelcast.collection.IQueue;
import com.hazelcast.collection.QueueStore;
import com.hazelcast.config.Config;
import com.hazelcast.config.MapStoreConfig;
import com.hazelcast.config.QueueConfig;
import com.hazelcast.config.QueueStoreConfig;
import com.hazelcast.cp.lock.FencedLock;
import com.hazelcast.map.IMap;
import com.hazelcast.map.MapStore;
import com.hazelcast.map.listener.MapListener;

import net.sf.dframe.cluster.hazelcast.h2.DataBase;
import net.sf.dframe.cluster.hazelcast.h2.H2MapStore;
import net.sf.dframe.cluster.hazelcast.h2.H2QueueStore;

/**
 * HazelcastMaster 
 * @author dy02
 *
 */
public class HazelcastMasterSlaveCluster extends HazelcastShardingCluster {

	private static Logger log = LoggerFactory.getLogger(HazelcastMasterSlaveCluster.class);
	
	public static final  String ACTIVE_MEMBER = "ACTIVE";
	
	private static final  String ARRIBUTIES  = "ARRIBUTIES";
	
	private DataBase db = null;
	
	
	public HazelcastMasterSlaveCluster(Config cfg,DataBase db) {
		super(cfg);
		this.db = db;
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
			}else { //must be first one
				active = hz.getCluster().getLocalMember().getAddress().getHost();
				map.put(HazelcastMasterSlaveCluster.ACTIVE_MEMBER, active);
			}
		} catch (Exception e) {
			log.error("Exception when get current active member",e);
		} finally {
			
		}
		log.info("cureent active member is : "+active);
		return active;
	}
	
	/**
	 * get queue
	 * @param name
	 * @return
	 */
	public IQueue<?> getQueue(String name){
		return hz.getQueue(name);
	}
	
	/**
	 *  get map
	 * @param name
	 * @return
	 */
	public IMap<?,?> getH2Map(String name){
		MapStoreConfig mapStoreConfig = new MapStoreConfig();
		mapStoreConfig.setEnabled(true);
		mapStoreConfig.setWriteDelaySeconds(0);
		MapStore<String, String>  store = new H2MapStore(db);
		mapStoreConfig.setImplementation(store);
		hz.getConfig().getMapConfig(name).setMapStoreConfig(mapStoreConfig);
		return hz.getMap(name);
	}
	
	/**
	 *  get map
	 * @param name
	 * @return
	 */
	public IMap<String,String> getArributesMap(){
		IMap<String,String> map = hz.getMap(ARRIBUTIES);
		MapListener listener = new HazelcastArributesMapListener(db);
		map.addEntryListener(listener, true);
		return map;
	}
	
	/**
	 * get list
	 * @param name
	 * @return
	 */
	public IList<?> getList(String name){
		return hz.getList(name);
	}
	
	/**
	 * get lock
	 * @param name
	 * @return
	 */
	public FencedLock getLock (String name) {
		return hz.getCPSubsystem().getLock(name);
	}
	
	
	/**
	 *  get map
	 * @param name
	 * @return
	 */
	public IQueue<String> getH2Queue(String name){
		QueueConfig queueConfig = hz.getConfig().getQueueConfig(name);
		
//		queueConfig.setMaxSize(size);

		QueueStore<?> storeImplementation = new H2QueueStore(db);
//		QueueStoreConfig qsc = new QueueStoreConfig();
		QueueStoreConfig queueStoreConfig = new QueueStoreConfig();
		queueStoreConfig.setEnabled(true);
		queueStoreConfig.setProperty("binary", "false");
		queueStoreConfig.setProperty("memory-limit", "0");
		queueStoreConfig.setProperty("bulk-load", "4");
		queueStoreConfig.setStoreImplementation(storeImplementation);
		queueConfig.setQueueStoreConfig(queueStoreConfig);
		
//		qsc.setStoreImplementation(storeImplementation  );
//		queueConfig.setQueueStoreConfig(qsc);
//		if (isPersistence) {
//			queueConfig.getQueueStoreConfig()
//	        .setEnabled(true)
//	        .setClassName("com.hazelcast.QueueStoreImpl")
//	        .setProperty("binary", "false");	
//		}
		
		return hz.getQueue(name);
	}
	
	
	@Override
	public void shutdown() {
		super.shutdown();
		db.close();
	}
}
