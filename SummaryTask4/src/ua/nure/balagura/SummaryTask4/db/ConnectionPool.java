package ua.nure.balagura.SummaryTask4.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class ConnectionPool {
	private static final Logger LOG = Logger.getLogger(ConnectionPool.class);

	private ConnectionPool() {
	}

	private static ConnectionPool instance = null;

	public static ConnectionPool getInstance() {
		LOG.trace("ConnectionPool#getInstance");
		if (instance == null)
			instance = new ConnectionPool();
		return instance;
	}

	public Connection getConnection() {
		LOG.trace("Getting connection");
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
		//	Context envContext = (Context) initContext.lookup("java:/comp/env");

			DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/summarytaskdb");
			conn = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}