package net.sf.dframe.cluster.hazelcast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.core.EntryAdapter;
import com.hazelcast.core.EntryEvent;

import net.sf.dframe.cluster.hazelcast.h2.ArributestMapH2Store;
import net.sf.dframe.cluster.hazelcast.h2.DataBase;

/**
 * 
 * @author dy02
 *
 */
public class HazelcastArributesMapListener extends EntryAdapter<String, String> {

	private static Logger log = LoggerFactory.getLogger(HazelcastArributesMapListener.class);

	

	private DataBase db = null;

	public HazelcastArributesMapListener() {
		
	}
	
	public HazelcastArributesMapListener(DataBase db) {
		this.db = db;
		initTable();
	}


	public void initTable() {
		String initSql = null;
		initSql = "CREATE TABLE IF NOT EXISTS  " + ArributestMapH2Store.TableName + " (" + ArributestMapH2Store.KEY_LABEL + " VARCHAR(1000) PRIMARY KEY ,"
				+ ArributestMapH2Store.VALUE_LABEL + " VARCHAR(1000))";
//		columns = new ArrayList<String>();
//		columns.add(ArributestMapH2Store.KEY_LABEL);
//		columns.add(ArributestMapH2Store.VALUE_LABEL);
		try {
			db.executeSql(initSql);
		} catch (Exception e) {
			log.error("create Queue Store Table Exception");
		}
	}

	@Override
	public void entryAdded(EntryEvent<String, String> event) {
		log.info("entryAdded {" + event.getKey() +":"+event.getValue()+"}");
		try {
			db.executeSql("MERGE INTO " + ArributestMapH2Store.TableName + " KEY (" + ArributestMapH2Store.KEY_LABEL + ") VALUES (" + event.getKey() + ",'"
					+ event.getValue() + "');");
		} catch (Exception e) {
			log.error("save h2 exception", e);
		}
	}
	
	@Override
	public void entryRemoved(EntryEvent<String, String> event) {
		log.info("entryRemoved {" + event.getKey() +":"+event.getValue()+"}");
		try {
			db.executeSql("DELETE FROM "+ArributestMapH2Store.TableName+" WHERE "+ArributestMapH2Store.KEY_LABEL+" = "+event.getKey()+";");
		} catch (Exception e) {
			log.error("delete h2 exception",e);
		}
	}

	@Override
	public void entryUpdated(EntryEvent<String, String> event) {
		log.info("entryUpdated {" + event.getKey() +":"+event.getValue()+"}");
		try {
			db.executeSql("MERGE INTO " + ArributestMapH2Store.TableName + " KEY (" + ArributestMapH2Store.KEY_LABEL + ") VALUES (" + event.getKey() + ",'"
					+ event.getValue() + "');");
		} catch (Exception e) {
			log.error("save h2 exception", e);
		}
	}

}
