package server.object;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;

import server.controller.RoomController;
import server.model.User;

public class MyClient {

	private Socket socket;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;
	private String username = new String();
	private Room room;
	
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
			System.out.println(this.username + " ngat ket noi");
			User.logout(this.username);
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
						System.out.println("Server received from " + ((username.isEmpty()||username == null)?"new connect":username) + ": " + message);
						checkMessage(message);
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
	
	private void checkMessage(String message) {
		String command[] = message.split("~");
		if(command[0].equals("login")) {
			ArrayList<User> us = User.select(new Hashtable<String, String>(){{
				put("username", command[1]);
				put("password", command[2]);
			}});
			if(us.size() == 0) {
				Send("replogin~0");				
			}
			else {				
				if(us.get(0).getState() == 0) {
					this.username = command[1];
					Send("replogin~1");
					us.get(0).setState(1);
				}
				else if(us.get(0).getState() == 1) {
					Send("replogin~2");
				}				
			}
		}
		else if(command[0].equals("signup")) {
			ArrayList<User> us1 = User.select(new Hashtable<String, String>(){{
				put("username", command[1]);
			}});
			if(us1.size() == 0) {
				if(User.insert(new User(0, command[1], command[2], 0))) {
				Send("repsignup~1");
				}
				else {
					Send("repsignup~2");
				}
			}
			else {
				Send("repsignup~0");
			}
		}
		else if(command[0].contains("room")) {
			RoomController.checkMessage(this, command);
		}
	}

	public String getUsername() {
		return username;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
		if(this.room == null) {
			Send("hahahahahaha");
		}
	}
}
