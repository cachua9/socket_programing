package client.controller;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import client.object.Room;

public class RoomController {
	
	public static ArrayList<Room> rooms = new ArrayList<Room>();	
	public static int curRoomIndex = -1;
	
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
		else if(command[0].equals("doneleaveroom")) {
			doneLeaveRoom(command);
		}
		else if(command[0].equals("repjoinroom")) {
			repJoinRoom(command);
		}
	}
	
	private static void refreshRooms(String command[]) {
		int qty = Integer.valueOf(command[1]);
		DefaultListModel<String> dm = new DefaultListModel<String>();		
		if(qty!=0) {
			rooms.clear();
			for(int i = 2; i < 2 + qty; i++) {
				String arr[] = command[i].split(",");
				String str = "Phòng " + arr[0] + " - Chủ phòng: " + arr[1] + " - ";
				int state = Integer.valueOf(arr[3]);
				if(state == 0) str += "Đang chờ";
				else str += "Đang chơi";
				str += "(" + arr[2] + "/100)";
				dm.addElement(str);
				Room room = new Room(Integer.valueOf(arr[0]), state,  arr[1]);
				rooms.add(room);
			}
		}
		ClientMain.selectRoom.getList().setModel(dm);
	}
	
	private static void refreshRoom(String command[]) {		
		ClientMain.roomView.getLbIdRoom().setText("Phòng " + command[1]);
		ClientMain.roomView.getLbQtyPlayer().setText("Số người trong phòng:  " + command[3]);
		DefaultListModel<String> dm = new DefaultListModel<String>();		
		int qty = Integer.valueOf(command[3]);
		if(qty!=0) {
			for (Room room : rooms) {
				if(room.getId() == Integer.valueOf(command[1])) {
					curRoomIndex = rooms.indexOf(room);
					room.clearPlayer();
					for(int i = 4; i < 4 + qty; i++) {
						dm.addElement(command[i]);
						room.addPlayer(command[i]);
					}
					break;
				}
			}			
			//System.out.println(command[4] + " - " + ClientMain.username);
			if(command[4].equals(ClientMain.username)) {
				ClientMain.roomView.getBtnPlay().setVisible(true);
				ClientMain.roomView.getBtnKickPlayer().setVisible(true);
			}
			else {
				ClientMain.roomView.getBtnPlay().setVisible(false);
				ClientMain.roomView.getBtnKickPlayer().setVisible(false);
			}
		}
		ClientMain.roomView.getList().setModel(dm);
	}
	
	private static void repCreateRoom(String command[]) {
		if(Integer.valueOf(command[1]) == 1) {
//			ClientMain.selectRoom.setVisible(false);
//			ClientMain.roomView.setVisible(true);
			ClientMain.generalView.switchToMe(ClientMain.roomView);
		}
		else if(Integer.valueOf(command[1]) == 0) {
			JOptionPane.showMessageDialog(null, "Lỗi khi tạo phòng");
		}
	}
	
	private static void doneLeaveRoom(String command[]) {
		//ClientMain.roomView.setVisible(false);
		ClientMain.showSelectRoom();
		JOptionPane.showMessageDialog(ClientMain.selectRoom, "Bạn đã rời khỏi phòng " + ClientMain.roomView.getLbIdRoom().getText());
		curRoomIndex = -1;
	}
	
	private static void repJoinRoom(String command[]) {
		if(command[1].equals("1")) {
//			ClientMain.selectRoom.setVisible(false);
//			ClientMain.roomView.setVisible(true);
			ClientMain.generalView.switchToMe(ClientMain.roomView);
		}
		else if(command[1].equals("2")){
			JOptionPane.showMessageDialog(ClientMain.selectRoom, "Phòng đã đầy");
		}
		else if(command[1].equals("3")) {
			JOptionPane.showMessageDialog(ClientMain.selectRoom, "Không thể tham gia phòng đang chơi");
		}
		else if(command[1].equals("0")) {
			JOptionPane.showMessageDialog(ClientMain.selectRoom, "Phòng không tồn tại");
		}
	}

	
	public static void createRoom() {
		ClientMain.connection.Send("createroom");		
	}
	
	public static void leaveRoom() {
		ClientMain.connection.Send("leaveroom");
	}
	
	public static void joinRoom(int id) {
		ClientMain.connection.Send("joinroom~" + String.valueOf(rooms.get(id).getId()));
	}
	
	public static void kickPlayer(int index) {
		ClientMain.connection.Send("roomkickplayer~" + rooms.get(curRoomIndex).getPlayerNameByIndex(index));
	}
	
	public static void startGame() {
		ClientMain.connection.Send("roomstart");
	}
	
}
