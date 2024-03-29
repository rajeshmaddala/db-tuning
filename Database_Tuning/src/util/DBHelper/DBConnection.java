package util.DBHelper;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DBConnection {
	
	
	/**
	 * Helper method to get db connection
	 * @return ConnectionDtls or error message if any
	 */
	public static ConnectionDtls getConnection(){
		ConnectionDtls connDtls = new ConnectionDtls();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String username = "sys as sysdba"; //ps.getProperty("username");
			String password = "MayDay#1"; //ps.getProperty("password");
		String url = "jdbc:oracle:thin:"+username+"/"+password+"@localhost:1521:orcl";//ps.getProperty("url");
			
			Connection conn = DriverManager.getConnection(url,username,password);	
			connDtls.setConn(conn);
			connDtls.setMessage("successful connection");
		}		
		catch (ClassNotFoundException cnfe) {
			connDtls.setMessage("Can't perform the action. Error Message: "+cnfe.getMessage());
			cnfe.printStackTrace();
		}
		catch (SQLException sqle) {
			connDtls.setMessage("Can't perform the action. Error Message: "+sqle.getMessage());
			sqle.printStackTrace();
		}
						
		return connDtls;
	}	

	/**
	 * Helper method to close database resources.
	 * @param resultset , Statement , Connection
	 *@return void 
	 */	
	public static void close(ResultSet rs, Statement stat, Connection conn) {
		close(rs);
		close(stat);
		close(conn);
	}

	/**
	 * Helper method to close database resources.
	 * @param Statement , Connection
	 *@return void 
	 * 
	 */
	public static void close(Statement stat, Connection conn) {
		close(stat);
		close(conn);
	}
	
	/**
	 * Helper method to close database resources.
	 * @param Statement
	 * @return void 
	 *@exception SQLException if a database access error occurs
	 */	
	public static void close(Statement stat) {
		try {
			if( null != stat )
			stat.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	/**
	 * Helper method to close database resources.
	 * @param  resultset
	 *@return void 
	 *@exception SQLException if a database access error occurs
	 * 
	 */	
	public static void close(ResultSet rs) {
		try {
			if( null != rs )
			rs.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	/**
	 * Helper method to close database resources.
	 * @param Connection
	 * @return void 
	 *@exception SQLException if a database access error occurs
	 */	
	public static void close(Connection conn) {
		try {
			if( null != conn ){
				conn.close();
			}
		} catch (SQLException sqle) {
			
		}
	}
}
