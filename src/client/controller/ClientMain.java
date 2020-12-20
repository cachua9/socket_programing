package client.controller;

import javax.swing.JOptionPane;

import client.view.ClientLogin;
import client.view.SelectRoom;

public class ClientMain {
	
	public static Connection connection;
	
	public static String username = "";
	
	public static ClientLogin clientLogin = new ClientLogin();
	public static SelectRoom selectRoom = new SelectRoom();
	
	public static void main(String[] args) {
		
		String address = JOptionPane.showInputDialog(null, "Input ip and port server: (ip:port)");
		String array[] = address.split(":");
		
		//Run client
		connection = new Connection(array[0], Integer.valueOf(array[1]));
		if(!connection.Connect()) {
			return;
		}
		
		//Show form login
		clientLogin.setVisible(true);
	}

}
