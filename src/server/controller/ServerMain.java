package server.controller;

import javax.swing.JOptionPane;

import server.view.ServerHome;

public class ServerMain {
	
	public static Connection connection;
	
	public static ServerHome serverHome = new ServerHome();
	
	public static void main(String[] args) {
				
		boolean flag;
		do {
			flag = init();
		}while(!flag);
		
	}
	
	public static boolean init() {
		
		String address = JOptionPane.showInputDialog(null, "Input port:");
		
		if(address == null) return true;
		
		int port;
		try {
			port = Integer.valueOf(address);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Nhập sai định dạng");
			return false;
		}
		
		//Run server
		connection = new Connection(port);
		connection.Start();
						
		//Show form
		serverHome.setVisible(true);
		
		return true;
	}
	
	public static void showServerIp(String ip, int port) {
		serverHome.getLbSvIp().setText(ip);
		serverHome.getLbSvP().setText(String.valueOf(port));
	}
	

}
