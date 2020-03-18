package net.sf.dframe.cluster.hazelcast.h2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.h2.jdbcx.JdbcConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public final class DataBase {

	private static Logger log = LoggerFactory.getLogger(DataBase.class);
	
	
	private String basedir = "./";

	private String clusterData = "cluster";
	private String dbUser = "root";
	private String dbPasswd = "root";

	
	private static DataBase instance = null;
	
	public static synchronized DataBase getInstance(String dir,String user,String password) {
			if ( instance == null)
				instance = new DataBase(dir,user,password);
		return instance;
	}

	/**
	 * 建立数据库
	 * @param dir
	 * @param port
	 * @param user
	 * @param password
	 */
	private DataBase(String dir,String user,String password) {
		if (dir !=null) {
			this.basedir = dir;
		}
		if (user !=null ) {
			this.dbUser = user;
		}
		if (password != null) {
			this.dbPasswd = password;
		}
		pool = JdbcConnectionPool.create("jdbc:h2:" + this.basedir + clusterData, dbUser, dbPasswd);
	}
	
	
	private JdbcConnectionPool pool;

	/**
	 * excuteSql
	 * @throws Exception 
	 * 
	 */
	public void executeSql (String sql) throws Exception {
		Connection conn = null;
		Statement createStatement = null;
		try {
			conn = pool.getConnection();
			createStatement = conn.createStatement();
			createStatement.execute(sql);
		}catch (Exception ex) {
			log.error("create Table exception",ex);
			throw ex;
		}finally {
			try {
				if (createStatement != null)
					createStatement.close();
				createStatement = null;
			}catch (Exception e) {
				createStatement = null;
			}
			try {
				if (conn != null)
					conn.close();
				conn = null;
			}catch (Exception e) {
				conn = null;
			}
		}
	}

	/**
	 *   get query result in json array
	 * @param sql
	 * @param getColumns
	 * @return
	 * @throws SQLException
	 */
	public JSONArray query(String sql,List<String> getColumns) throws SQLException {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		JSONArray result = null;
		try {
			conn = pool.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			result = getResult(rs,getColumns);
		}catch (Exception e ) {
			log.error("Database query error , SQL: "+ sql ,e);
			throw e;
		}finally {
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				}catch (Exception e) {
					rs = null;
				}
			}
			if (st != null ) {
				try {
					st.close();
					st = null;
				} catch (SQLException e) {
					st = null;
				}
			}
			if (conn !=null) {
				try {
					conn.close();
					conn = null;
				}catch (Exception e) {
					conn = null;
				}
			}	
		}
		return result;
	}
	
	private JSONArray getResult(ResultSet rs,List<String> columns) throws SQLException {
		JSONArray result = new JSONArray();
		if (rs != null) {
			while ( rs.next()) {
				JSONObject item = new JSONObject();
				for (String column:columns) {
					item.put(column, rs.getObject(column));
				}
				result.add(item);
			}
		}
		
		return result;
	}
	
	
	public void close() {
		pool.dispose();
	}
	
	
	/**
	 * excuteSql
	 * @throws Exception 
	 * 
	 */
	public void dropTable (String tableName) throws Exception {
		Connection conn = null;
		Statement createStatement = null;
		try {
			conn = pool.getConnection();
			createStatement = conn.createStatement();
			createStatement.execute("DROP TABLE "+tableName);
		}catch (Exception ex) {
			log.error("create Table exception",ex);
			throw ex;
		}finally {
			try {
				if (createStatement != null)
					createStatement.close();
				createStatement = null;
			}catch (Exception e) {
				createStatement = null;
			}
			try {
				if (conn != null)
					conn.close();
				conn = null;
			}catch (Exception e) {
				conn = null;
			}
		}
	}

	
}
