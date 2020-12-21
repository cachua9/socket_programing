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
		else if(command[0].equals("leaveroom")) {
			leaveRoom(myClient);
		}
		else if(command[0].equals("joinroom")) {
			joinRoom(myClient, command);
		}
		else if(command[0].equals("roomkickplayer")) {
			kickPlayer(myClient, command);
		}
	}
	
	private static void createRoom(MyClient myClient) {
		for(int i =0; i < 3000; i++) {
			if (!isUse[i]){
				isUse[i] = true;
				Room room = new Room(i, 0, myClient);
				rooms.add(room);
				refreshRooms(myClient);
				myClient.Send("repcreateroom~1");
				refreshRoom(myClient);
				return;
			}
		}
		myClient.Send("repcreateroom~0");
	}
	
	public static void delRoom(Room room) {
		isUse[room.getId()] = false;
		room.cancel();
		rooms.remove(room);
	}
	
	private static void refreshRooms(MyClient myClient) {
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
	
	private static void kickPlayer(MyClient myClient, String command[]) {
		myClient.getRoom().removePlayerByName(command[1]);
	}
	
	public static void refreshRoom(MyClient myClient) {
		String message = myClient.getRoom().getInfoRoom();
		myClient.Send(message);
	}

	private static void leaveRoom(MyClient myClient) {
		myClient.getRoom().removePlayer(myClient);
	}
	
	private static void joinRoom(MyClient myClient, String command[]) {
		int idRoom = Integer.valueOf(command[1]);
		for (Room room : rooms) {
			if(room.getId() == idRoom) {
				if(room.getCurQtyPlayer() >= room.getMaxQtyPlayer()) {
					myClient.Send("repjoinroom~2");
					refreshRooms(myClient);					
				}
				else if(room.getState() == 1) {
					myClient.Send("repjoinroom~3");
					refreshRooms(myClient);	
				}
				else {
					room.addPlayer(myClient);
					myClient.Send("repjoinroom~1");
				}
				
				return;
			}
		}
		myClient.Send("repjoinroom~0");
		refreshRooms(myClient);
	}

}
