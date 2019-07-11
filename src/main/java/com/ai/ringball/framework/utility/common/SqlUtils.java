/**
 * 
 */
package com.ai.ringball.framework.utility.common;

import com.ai.ringball.framework.constants.SysConstants;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 * @author HBoy
 *
 */
public class SqlUtils {

	static final String ROW_NUM_NAME = "ROW_ID_NUMBER_";
	
	public static Connection GetConnection() throws Exception {
		Connection conn = null;
		String jdbcType = SysConstants.CONSTANT_NULL_STRING;
		String lowJdbcType = "jdbc.connect.type";
		String upJdbcType = "JDBC.CONNECT.TYPE";
		Properties pro = PropertyUtil.getResourceFile("jdbc/jdbc.properties");
		if (pro.containsKey(lowJdbcType)) {
			jdbcType = pro.getProperty(lowJdbcType);
		} else if (pro.containsKey(upJdbcType)) {
			jdbcType = pro.getProperty(upJdbcType);
		}
		if (SysConstants.DB_CONNECT_TYPE_JDBC.equals(jdbcType)) {
			conn = SqlUtils.GetJdbcConnection();
		} else {
			conn = SqlUtils.GetJndiConnection();
		}
		return conn;
	}

	public static Connection GetJndiConnection() throws Exception {
		Connection conn = null;
		String jndiName = "";
		String lowJndiName = "jndi.jndiname";
		String upJndiName = "JNDI.JNDINAME";
		Properties pro = PropertyUtil.getResourceFile("jdbc/jdbc.properties");
		if (pro.containsKey(lowJndiName)) {
			jndiName = pro.getProperty(lowJndiName);
		} else if (pro.containsKey(upJndiName)) {
			jndiName = pro.getProperty(upJndiName);
		}
		Context ctx = new InitialContext();
		DataSource ds = null;
		ds = (DataSource) ctx.lookup(jndiName);
		conn = ds.getConnection();
		return conn;
	}
	
	public static Connection GetJdbcConnection() throws Exception {
		Connection conn = null;
		String driverName = "";
		String urlName = "";
		String userName = "";
		String password = "";
		String driver = "jdbc.driver";
		String url = "jdbc.url";
		String user = "jdbc.user";
		String pass = "jdbc.password";
		Properties pro = PropertyUtil.getResourceFile("jdbc/jdbc.properties");
		if (pro.containsKey(driver)) {
			driverName = pro.getProperty(driver);
		}
		if (pro.containsKey(url)) {
			urlName = pro.getProperty(url);
		}
		if (pro.containsKey(user)) {
			userName = pro.getProperty(user);
		}
		if (pro.containsKey(pass)) {
			password = pro.getProperty(pass);
		}
		Class.forName(driverName);
		conn = DriverManager.getConnection(urlName, userName, password);
		return conn;
	}
	
	public static boolean closeConnection(Connection conn) {
		if(conn == null){
			return true;
		} else {
			try {
				conn.close();
				return true;
			} catch (SQLException e) {
				return false;
			}
		}
	}
}
