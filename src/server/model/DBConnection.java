package server.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static Connection conn;
	final static String url="jdbc:mysql://localhost:3306/socket_db";
    final static String username="root";
    final static String password="";
    
	public static Connection getConn() {
		if(conn != null) {
			return conn;
		}
		else {
			try {
				conn = DriverManager.getConnection(url, username, password);
			}
			catch (SQLException e) {
				conn = null;
			}
			return conn;
		}
	}
	
	public static void CloseConn() {
		try {
			conn.close();
		}
		catch (SQLException e) {
			// TODO: handle exception
		}
	}

}