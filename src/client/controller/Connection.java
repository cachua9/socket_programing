package client.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Connection {
	private Socket socket;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;
	private String ip;
	private int port;
	
	public Connection(String ip, int port) {		
		this.ip = ip;
		 this.port = port;
	}
	
	public boolean Connect() {
		try {
			this.socket = new Socket(ip, port);
			this.dataInputStream = new DataInputStream(this.socket.getInputStream());
			this.dataOutputStream = new DataOutputStream(this.socket.getOutputStream());
			System.out.println("Ket toi server thanh cong");
			Receive();
			return true;
		} 
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Không thể kết nối tới server, hãy kiểm tra lại!");
			return false;
		}
	}
	
	public void Close() {
		try {
			this.dataInputStream.close();
			this.dataOutputStream.close();
			this.socket.close();
			System.out.println("Ket noi den server bi ngat");
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
	
	public boolean isConnected() {
		return socket.isConnected();
	}
	
	public void Send(String message) {
		try {
			dataOutputStream.writeUTF(message);
			dataOutputStream.flush();
		}
		catch (IOException e) {
			// TODO: handle exception
		}
	}
	
	private void Receive() {
		Thread thread = new Thread() {
			public void run() {
				while(true) {
					try {
						String message = dataInputStream.readUTF();
						System.out.println(message);
						//do something
					} catch (IOException e) { 
						break;
					}							
				}
				Close();
			}
		};
		thread.setDaemon(true);
		thread.start();		
	}

}
