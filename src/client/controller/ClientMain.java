package client.controller;

import javax.swing.JOptionPane;

import client.view.ClientLogin;
import client.view.GeneralView;
import client.view.InGameView;
import client.view.RoomView;
import client.view.SelectRoom;

public class ClientMain {
	
	public static Connection connection;
	
	public static String username = "";
	
	public static GeneralView generalView = new GeneralView();
	public static ClientLogin clientLogin = new ClientLogin();
	public static SelectRoom selectRoom = new SelectRoom();
	public static RoomView roomView = new RoomView();
	public static InGameView inGameView = new InGameView();
	
	public static void main(String[] args) {
		boolean flag;
		do {
			flag = init();
		}while(!flag);
	}
	
	public static boolean init() {
		String address = JOptionPane.showInputDialog(null, "Input ip and port server: (ip:port)");
		if(address == null) return true;
		if(!address.contains(":")) {
			JOptionPane.showMessageDialog(null, "Nhập sai định dạng");
			return false;
		}
		String array[] = address.split(":");
		
		//Run client
		int port = 0;
		try {
			port = Integer.valueOf(array[1]);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Nhập sai định dạng");
			return false;
		}
		connection = new Connection(array[0], port);
		if(!connection.Connect()) {
			return false;
		}
		
		//Show form login
		generalView.setVisible(true);
		generalView.switchToMe(clientLogin);
		return true;
	}
	
	public static void showSelectRoom() {
		generalView.switchToMe(selectRoom);
		connection.Send("refreshrooms");
	}

}

// do data cau hoi len view tro giup //done
// xoa doan chat cu //done
// sua loi hoi khan gia khi khong co ai tra loi //done
// them su kien chien thang //done
