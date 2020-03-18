package net.sf.dframe.cluster.hazelcast.h2;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.hazelcast.collection.QueueStore;
/**
 * 持久化
 * @author dy02
 *
 */
public class H2QueueStore implements QueueStore<String>{

	
	private static Logger log = LoggerFactory.getLogger(H2QueueStore.class);
	
	private DataBase db;
	
	private String TableName = "QUEUE";
	
	private List<String> columns ;
	
	private final String VALUE_LABEL = "MESSAGE";
	private final String KEY_LABEL = "LONG_KEY";
	
	/**
	 * set the name
	 * @param db
	 * @param name
	 */
	public H2QueueStore (DataBase db,String name) {
		this.db = db;
		initTable(db, name);
		
	}

	private void initTable(DataBase db, String name) {
		String initSql = null;
		if (name!=null && !name.isEmpty()) {
			this.TableName = this.TableName+"_"+name;
		}
		initSql = "CREATE TABLE IF NOT EXISTS  "+TableName+" ("+KEY_LABEL+" BIGINT PRIMARY KEY ,"+VALUE_LABEL+" VARCHAR(1000))";
		columns = new ArrayList<String>();
		columns.add(KEY_LABEL);
		columns.add(VALUE_LABEL);
		try {
			db.executeSql(initSql);
		} catch (Exception e) {
			log.error("create Queue Store Table Exception");
		}
	}
	
	public H2QueueStore (DataBase db) {
		//
		this (db,null);
	}
	
	@Override
	public void store(Long key, String value) {
		try {
			db.executeSql("MERGE INTO "+this.TableName+" KEY ("+KEY_LABEL+") VALUES ("+key+",'"+value+"');");
		} catch (Exception e) {
			log.error("save h2 exception",e);
		}
	}

	@Override
	public void storeAll(Map<Long, String> map) {
		for ( Long key : map.keySet()) {
			try {
				db.executeSql("MERGE INTO "+this.TableName+" KEY ("+KEY_LABEL+") VALUES ("+key+",'"+map.get(key)+"');");
			} catch (Exception e) {
				log.error("batch save h2 exception",e);
			}
		}
	}

	@Override
	public void delete(Long key) {
		try {
			db.executeSql("DELETE FROM "+this.TableName+" WHERE "+KEY_LABEL+" = "+key+";");
		} catch (Exception e) {
			log.error("delete h2 exception",e);
		}
	}

	@Override
	public void deleteAll(Collection<Long> keys) {
//		try {
//			db.executeSql("TRUNCATE TABLE "+this.TableName);
//		} catch (Exception e) {
//			log.error("delete all h2 exception",e);
//		}
		for (Long key:keys)
			delete(key);
	}

	@Override
	public String load(Long key) {
		String value = null;
		JSONArray result;
		try {
			result = db.query("SELECT * FROM "+this.TableName+" WHERE "+KEY_LABEL+" = "+key+";",columns);
			if (!result.isEmpty()) {
				value = result.getJSONObject(0).getString(VALUE_LABEL);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("load data for "+key +" error ",e);
		}
		return value;
	}

	@Override
	public Map<Long, String> loadAll(Collection<Long> keys) {
		Map<Long, String> map = new HashMap<Long,String>();
		for (Long key:keys) {
			JSONArray result = null;
			String value = null;
//			result = db.query("SELECT * FROM "+this.TableName,columns);
			try {
				result = db.query("SELECT * FROM "+this.TableName+" WHERE "+KEY_LABEL+" = "+key+";",columns);
				if (!result.isEmpty()) {
					value = result.getJSONObject(0).getString(VALUE_LABEL);
				}				
				map.put(key, value);
			}catch (Exception ex) {
				log.error("load ALL exception ",ex);
			}
		}
		
//		try {
//			JSONArray result  = db.query("SELECT * FROM "+this.TableName,columns);
//			for (int i = 0 ; i < result.size() ; i ++) {
//				map.put(result.getJSONObject(i).getLong(this.KEY_LABEL),result.getJSONObject(i).getString(VALUE_LABEL));
//			}
//				
//		}catch (Exception ex) {
//			log.error("load ALL exception ",ex);
//		}
		return map;
	}

	@Override
	public Set<Long> loadAllKeys() {
		Set<Long> set = new HashSet<Long>();
		try {
			JSONArray result = db.query("SELECT * FROM "+this.TableName,columns);
			for (int i = 0 ; i < result.size(); i ++) {
				set.add(result.getJSONObject(i).getLong(this.KEY_LABEL));
			}
		} catch (SQLException e) {
			log.error("load ALL keys exception ",e);
		}
		return set;
	}

}
