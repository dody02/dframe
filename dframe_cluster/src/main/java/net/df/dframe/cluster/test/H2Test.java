package net.df.dframe.cluster.test;

import net.sf.dframe.cluster.hazelcast.h2.DataBase;

public class H2Test {
	
	public static void main(String[] args) {
		DataBase db = new DataBase();
		try {
//			db.clear();
			db.startdb();
			db.save(123,"asdf");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
