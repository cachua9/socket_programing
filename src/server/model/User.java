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
	private int state;
	
	public User() {
		
	}
	public User(int user_ID, String username, String password, int state) {
		super();
		this.user_ID = user_ID;
		this.username = username;
		this.password = password;
		this.state = state;
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
		//System.out.println(sql);
		ArrayList<User> result = new ArrayList<User>();
		try {
			Statement st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);			
			while(rs.next()) {
				User u = new User(rs.getInt("user_ID"), rs.getString("username"), rs.getString("password"), rs.getInt("state"));
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
	
	public static void logout(String username) {
		try {            
			Connection conn = DBConnection.getConn();
            String sql = "update users set state = '0' where username = '" + username +"'";      
            Statement st = (Statement) conn.createStatement();
            st.executeUpdate(sql);      
        } 
        catch (SQLException ex) 
        {
        	System.out.println("Update users error!");
        }
	}
	
	public void setState(int state) {
		try {            
			Connection conn = DBConnection.getConn();
            String sql = "update users set state = '" + String.valueOf(state) + "' where username = '" + this.username +"'";     
            //System.out.println(sql);
            Statement st = (Statement) conn.createStatement();
            st.executeUpdate(sql);      
        } 
        catch (SQLException ex) 
        {
        	System.out.println("Update users error!");
        }
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
	public int getState() {
		return state;
	}
	
}
