package net.sf.dframe.cluster.hazelcast;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.collection.QueueStore;

import net.sf.dframe.cluster.hazelcast.h2.DataBase;
/**
 * 持久化
 * @author dy02
 *
 */
public class H2QueueStore implements QueueStore<String>{

	
	private static Logger log = LoggerFactory.getLogger(H2QueueStore.class);
	
	private DataBase db;
	
	public H2QueueStore (DataBase db) {
		this.db = db;
	}
	
	@Override
	public void store(Long key, String value) {
		try {
			db.save(key, value);
		} catch (Exception e) {
			log.error("save h2 exception",e);
		}
	}

	@Override
	public void storeAll(Map<Long, String> map) {
		for ( Long key : map.keySet()) {
			try {
				db.save(key, map.get(key));
			} catch (Exception e) {
				log.error("batch save h2 exception",e);
			}
		}
	}

	@Override
	public void delete(Long key) {
		try {
			db.delete(key);
		} catch (Exception e) {
			log.error("delete h2 exception",e);
		}
	}

	@Override
	public void deleteAll(Collection<Long> keys) {
		try {
			db.deleteALL();
		} catch (SQLException e) {
			log.error("delete all h2 exception",e);
		}
//		for (Long key:keys) {
//			try {
//				db.delete(key);
//			} catch (SQLException e) {
//				log.error("delete all h2 exception",e);
//			}
//		}
	}

	@Override
	public String load(Long key) {
		String value = null;
		try {
			value = db.query(key);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return value;
	}

	@Override
	public Map<Long, String> loadAll(Collection<Long> keys) {
		Map<Long, String> map = new HashMap<Long,String>();
		for (Long key:keys) {
			try {
				String value = db.query(key);
				map.put(key, value);
			}catch (Exception ex) {
				log.error("load ALL exception ",ex);
			}
		}
		return map;
	}

	@Override
	public Set<Long> loadAllKeys() {
		Set<Long> set = new HashSet<Long>();
		try {
			set = db.queryAllKey();
		} catch (SQLException e) {
			log.error("load ALL keys exception ",e);
		}
		return set;
	}

}
