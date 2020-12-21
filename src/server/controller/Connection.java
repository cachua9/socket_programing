package server.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import server.object.MyClient;

public class Connection {

	private ServerSocket serverSocket;
	private int port;
	
	private ArrayList<MyClient> myClients;
	
	public Connection(int port) {
		this.port = port;
		this.myClients = new ArrayList<MyClient>();
	}
	
	public boolean Start() {
		try {
			this.serverSocket = new ServerSocket(this.port);			
			ServerMain.showServerIp(InetAddress.getLocalHost().getHostAddress(), serverSocket.getLocalPort());
			Listen();
			ServerMain.serverHome.println("Khởi động server thành công.");
			return true;
		}
		catch (IOException e) {
			ServerMain.showServerIp("Error", -1);
			ServerMain.serverHome.println("Khởi động server thất bại.");
			return false;
		}
	}
	
	public void Close() {
		try {
			this.serverSocket.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
	
	public boolean isConnected() {
		return !serverSocket.isClosed();
	}
	
	private void Listen() {
		Thread thread = new Thread() {
			public void run() {
				while(true) {
					try {
						Socket socket = serverSocket.accept();
						MyClient myClient = new MyClient(socket);
						myClients.add(myClient);
						ServerMain.serverHome.println("Nhận được kết nối từ 1 client mới(" + socket.getInetAddress().getHostAddress() + ":" + String.valueOf(socket.getPort()) + ")");
					} catch (IOException e) {
						ServerMain.serverHome.println("Lỗi server listen");
						break;
					}							
				}
				Close();
			}
		};
		thread.setDaemon(true);
		thread.start();		
	}

	public ArrayList<MyClient> getMyClients() {
		return myClients;
	}
}
