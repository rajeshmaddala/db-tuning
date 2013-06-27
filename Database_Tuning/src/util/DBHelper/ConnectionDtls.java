package util.DBHelper;

import java.sql.Connection;

public class ConnectionDtls {
	
	Connection conn;
	String message;
	
	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
