package server.model;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Statement;

public class DBConnection {
	private static Connection conn;
	private static String url="jdbc:mysql://localhost:3306/socket_db";
	private static String port = "3306";
	private static String database = "";
	private static String username="root";
	private static String password="";
	private static Scanner myReader;
    
	public static Connection getConn() {
		if(conn != null) {
			return conn;
		}
		else {
			File inputFile = new File("properties.txt");
			if (inputFile.exists()) {
				try {
					myReader = new Scanner(inputFile);
					String data = myReader.nextLine();
					if(data.split("=").length > 1) {
						port = data.split("=")[1];
					}
					else {
						port = "";
					}
					data = myReader.nextLine();
					if(data.split("=").length > 1) {
						database = data.split("=")[1];
					}
					else {
						database = "";
					}
					data = myReader.nextLine();
					if(data.split("=").length > 1) {
						username = data.split("=")[1];
					}
					else {
						username = "";
					}
					data = myReader.nextLine();
					if(data.split("=").length > 1) {
						password = data.split("=")[1];
					}
					else {
						password = "";
					}
					url = "jdbc:mysql://localhost:" + port + "/" +database;
					try {
						
						conn = DriverManager.getConnection(url, username, password);
					}
					catch (SQLException e) {
						conn = null;
					}
					return conn;
				}
				catch (Exception e) {
					// TODO: handle exception
				}				
			}
			else {
				JOptionPane.showMessageDialog(null, "Không tìm thấy file properties.txt");
			}
			return null;
		}
	}
	
	public static void initDatabase() {  
		File inputFile = new File("database.sql");
		if (inputFile.exists()) {
			try {
				myReader = new Scanner(inputFile);
				String data = "";
				 while (myReader.hasNextLine()) {
				        data += myReader.nextLine();
				      }
				//System.out.println(data);
				String[] sql = data.split(";");
				Connection conn = DBConnection.getConn();
				Statement st = (Statement) conn.createStatement();
				for (String string : sql) {
					st.executeUpdate(string);
				}
				System.out.println("Initialized database!");
				Question.inputQuestions();
				JOptionPane.showMessageDialog(null, "Khởi tạo database thành công!");				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Khởi tạo database bị lỗi!");
			}
		} else {
			System.out.println("File database.sql không tồn tại!");
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