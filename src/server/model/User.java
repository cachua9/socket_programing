package server.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import com.mysql.jdbc.Statement;

public class User {
	private int user_ID;
	private String username;
	private String password;
	
	public User() {
		
	}
	public User(int user_ID, String username, String password) {
		super();
		this.user_ID = user_ID;
		this.username = username;
		this.password = password;
	}	
	
	public boolean checkLoginInfo(String username, String password) {
		return this.username.equals(username) && this.password.equals(password);
	}
	
	public static ArrayList<User> select() {
		return select(new Hashtable<String, String>());
	}
	
	public static ArrayList<User> select(Hashtable<String, String> hm) {
		Connection conn = DBConnection.getConn();
		String sql = "select * from users";
		boolean flag = false;
		for(Map.Entry<String, String> e : hm.entrySet()) {
			if(!flag) {
				flag = true;
				sql += " where " + e.getKey() + "='" + e.getValue() + "'";
			}
			else {
				sql += " and " + e.getKey() + "='" + e.getValue() + "'";
			}
		}
		System.out.println(sql);
		ArrayList<User> result = new ArrayList<User>();
		try {
			Statement st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);			
			while(rs.next()) {
				User u = new User(rs.getInt("user_ID"), rs.getString("username"), rs.getString("password"));
				result.add(u);
			}
		}
		catch (SQLException e) {
			System.out.println("Select users error!");
		}	
		return result;
	}
	
	public static boolean insert(User user) {
		try {            
			Connection conn = DBConnection.getConn();
            String sql = "insert into users(username,password) values('"+user.getUsername()+"','"+user.getPassword()+"')";      
            Statement st = (Statement) conn.createStatement();
            int rs =  st.executeUpdate(sql);
            return rs>0;            
        } 
        catch (SQLException ex) 
        {
        	System.out.println("Insert users error!");
        }
		return false;
	}
	
	public int getUser_ID() {
		return user_ID;
	}
	public void setUser_ID(int user_ID) {
		this.user_ID = user_ID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
