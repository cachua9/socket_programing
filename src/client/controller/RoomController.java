package client.controller;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import client.object.Room;

public class RoomController {
	
	public static ArrayList<Room> rooms = new ArrayList<Room>();	
	
	public static void checkMessage(String command[]) {
		if(command[0].equals("inforooms")) {
			refreshRooms(command);
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

}
