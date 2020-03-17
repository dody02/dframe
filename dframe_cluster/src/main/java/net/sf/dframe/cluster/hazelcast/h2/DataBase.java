package net.sf.dframe.cluster.hazelcast.h2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.h2.jdbcx.JdbcConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataBase {

	private static Logger log = LoggerFactory.getLogger(DataBase.class);
	
	private final String createTable = "CREATE TABLE IF NOT EXISTS QUEUE(LONG_KEY BIGINT PRIMARY KEY ,MESSAGE VARCHAR(1000))";
	private final String clearTable = "DROP TABLE QUEUE";
	private String basedir = "./";


	private String clusterData = "cluster";
	private String dbUser = "root";
	private String dbPasswd = "root";
	
	
	
	private JdbcConnectionPool pool;

	
	public void startdb() throws Exception {
		startdb(null);
	}
	
	public void startdb(String dbdir) throws Exception {
		
		if (dbdir == null) {
			dbdir = basedir;
		}
		if (pool == null) {
			pool = JdbcConnectionPool.create("jdbc:h2:" + dbdir + clusterData, dbUser, dbPasswd);
		}
		Connection conn = null;
		Statement createStatement = null;
		try {
			conn = pool.getConnection();
			createStatement = conn.createStatement();
			createStatement.execute(createTable);
		}catch (Exception ex) {
			log.error("create Table exception",ex);
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
	
	
	public void stop() {
		pool.dispose();
	}
	
	public void save(long key,String data) throws Exception {
		
		String sql = "MERGE INTO QUEUE KEY (LONG_KEY) VALUES ("+key+",'"+data+"');";
		Connection conn = null;
		Statement st = null;
		try {
			conn = pool.getConnection();
			st = conn.createStatement();
			st.execute(sql);
		}catch (Exception e ) {
			log.error("Database save error , SQL: "+ sql ,e);
			throw e;
		}finally {
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
		
	}
	
	
	public void delete(long key) throws Exception {
		String sql = "DELETE FROM QUEUE WHERE LONG_KEY = "+key+";";
		Connection conn = null;
		Statement st = null;
		try {
			conn = pool.getConnection();
			st = conn.createStatement();
			st.execute(sql);
		}catch (Exception e ) {
			log.error("Database delete error , SQL: "+ sql ,e);
			throw e;
		}finally {
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
	}
	
	
	public void deleteALL() throws SQLException {
		String sql = "TRUNCATE TABLE QUEUE;";
		Connection conn = null;
		Statement st = null;
		try {
			conn = pool.getConnection();
			st = conn.createStatement();
			st.execute(sql);
		}catch (Exception e ) {
			log.error("Database delete error , SQL: "+ sql ,e);
			throw e;
		}finally {
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
	}


	public String query(long key) throws SQLException {
		String sql = "SELECT * FROM QUEUE WHERE LONG_KEY = "+key+";";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		String resultString = null;
		try {
			conn = pool.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			rs.next();
			resultString = rs.getString("MESSAGE");
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
		return resultString;
	}
	
	
	public Map<Long,String> queryALL() throws SQLException {
		String sql = "SELECT * FROM QUEUE ;";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		Map<Long,String> resultMap = new HashMap<Long,String>();
		try {
			conn = pool.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next())
				resultMap.put(rs.getLong("LONG_KEY"), rs.getString("MESSAGE")) ;
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
		return resultMap;
	}
	
	public Set<Long> queryAllKey() throws SQLException {
		String sql = "SELECT * FROM QUEUE ;";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		Set<Long> resultSet = new HashSet<Long>();
		try {
			conn = pool.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next())
			resultSet.add(rs.getLong("LONG_KEY")) ;
		}catch (Exception e ) {
			log.error("Database query error , SQL: "+ sql ,e);
			throw e;
		}finally {
			if (rs != null) {
				try {
					rs.close();
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
		return resultSet;
	}
	

	
	public void clear() throws SQLException {
		if (pool == null) {
			pool = JdbcConnectionPool.create("jdbc:h2:" + basedir + clusterData, dbUser, dbPasswd);
		}
		Connection conn = null;
		Statement createStatement = null;
		try {
			conn = pool.getConnection();
			createStatement = conn.createStatement();
			createStatement.execute(clearTable);
		}catch (Exception ex) {
			log.error("clear Table exception",ex);
		}finally {
			try {
				createStatement.close();
				createStatement = null;
			}catch (Exception e) {
				createStatement = null;
			}
			try {
				conn.close();
				conn = null;
			}catch (Exception e) {
				conn = null;
			}
		}
	}
}
