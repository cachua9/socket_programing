package server.controller;

import java.util.ArrayList;

import server.object.MyClient;
import server.object.Room;

public class RoomController {
	private static boolean[] isUse = new boolean[3000];
	
	public static ArrayList<Room> rooms = new ArrayList<Room>();
	
	public static void checkMessage(MyClient myClient, String command[]) {
		if(command[0].equals("createroom")) {
			createRoom(myClient);
		}
		else if(command[0].equals("refreshrooms")) {
			refreshRooms(myClient);
		}
	}
	
	public static void createRoom(MyClient myClient) {
		for(int i =0; i < 3000; i++) {
			if (!isUse[i]){
				isUse[i] = true;
				Room room = new Room(i, 0, myClient);
				rooms.add(room);
				break;
			}
		}
	}
	
	public static void delRoom(Room room) {
		isUse[room.getId()] = false;
		room.cancel();
		rooms.remove(room);
	}
	
	public static void refreshRooms(MyClient myClient) {
		String message = "inforooms~";
		if(rooms.size()!=0) {
			message += String.valueOf(rooms.size());
			for (Room room : rooms) {
				message += "~" + String.valueOf(room.getId()) + "," + room.getHostPlayerName() + "," + String.valueOf(room.getCurQtyPlayer()) + "," + String.valueOf(room.getState());
			}
		}
		else {
			message += "0";
		}
		myClient.Send(message);
	}

}
