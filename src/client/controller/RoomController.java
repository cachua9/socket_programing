package client.controller;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import client.object.Room;

public class RoomController {
	
	public static ArrayList<Room> rooms = new ArrayList<Room>();	
	
	public static void checkMessage(String command[]) {
		if(command[0].equals("inforooms")) {
			refreshRooms(command);
		}
		else if(command[0].equals("inforoom")) {
			refreshRoom(command);
		}
		else if(command[0].equals("repcreateroom")) {
			repCreateRoom(command);
		}
	}
	
	public static void refreshRooms(String command[]) {
		int qty = Integer.valueOf(command[1]);
		DefaultListModel<String> dm = new DefaultListModel<String>();		
		if(qty!=0) {
			for(int i = 2; i < 2 + qty; i++) {
				String arr[] = command[i].split(",");
				String str = "Phòng " + arr[0] + " - Chủ phòng: " + arr[1] + " - ";
				int state = Integer.valueOf(arr[3]);
				if(state == 0) str += "Đang chờ";
				else str += "Đang chơi";
				str += "(" + arr[2] + "/100)";
				dm.addElement(str);
			}
		}
		ClientMain.selectRoom.getList().setModel(dm);
	}
	
	public static void refreshRoom(String command[]) {		
		ClientMain.roomView.getLbIdRoom().setText("Phòng " + command[1]);
		ClientMain.roomView.getLbQtyPlayer().setText("Số người trong phòng:  " + command[3]);
		DefaultListModel<String> dm = new DefaultListModel<String>();		
		int qty = Integer.valueOf(command[3]);
		if(qty!=0) {
			for(int i = 4; i < 4 + qty; i++) {
				dm.addElement(command[i]);
			}
			System.out.println(command[4] + " - " + ClientMain.username);
			if(command[4].equals(ClientMain.username)) {
				ClientMain.roomView.getBtnPlay().setVisible(true);
				ClientMain.roomView.getBtnKickPlayer().setVisible(true);
			}
		}
		ClientMain.roomView.getList().setModel(dm);
	}
	
	public static void repCreateRoom(String command[]) {
		if(Integer.valueOf(command[1]) == 1) {
			ClientMain.selectRoom.setVisible(false);
			ClientMain.roomView.setVisible(true);
		}
		else if(Integer.valueOf(command[1]) == 0) {
			JOptionPane.showMessageDialog(null, "Lỗi khi tạo phòng");
		}
	}
	
	public static void createRoom() {
		ClientMain.connection.Send("createroom");		
	}
	
	public static void leaveRoom() {
		ClientMain.connection.Send("leaveroom");
	}

}
