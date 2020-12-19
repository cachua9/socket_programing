package server.object;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MyClient {

	private Socket socket;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;
	private int id;
	
	public MyClient(Socket socket) throws IOException {
		this.socket = socket;
		this.dataInputStream = new DataInputStream(this.socket.getInputStream());
		this.dataOutputStream = new DataOutputStream(this.socket.getOutputStream());
		
		Receive();
	}
	
	public void Close() {
		try {
			this.dataInputStream.close();
			this.dataOutputStream.close();
			this.socket.close();
			System.out.println("ngat ket noi");
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
