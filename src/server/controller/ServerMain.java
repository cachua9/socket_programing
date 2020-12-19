package server.controller;

import server.view.ServerHome;

public class ServerMain {
	
	public static Connection connection;
	
	private static ServerHome serverHome = new ServerHome();
	
	public static void main(String[] args) {
		
		//Run server
		connection = new Connection(3333);
		connection.Start();
				
		//Show form
		serverHome.setVisible(true);
		
	}
	
	public static void showServerIp(String ip, int port) {
		serverHome.getLbSvIp().setText(ip);
		serverHome.getLbSvP().setText(String.valueOf(port));
	}

}
