package server.object;

import java.util.ArrayList;

import server.controller.RoomController;

public class Room {
	private int id;
	private int state;
	private final int maxQtyPlayer = 100;
	private ArrayList<MyClient> players = new ArrayList<MyClient>();

	
	public Room(int id, int state, MyClient hostPlayer) {
		super();
		this.id = id;
		this.state = state;
		this.players.add(hostPlayer);
		hostPlayer.setRoom(this);
	}
	
	public void addPlayer(MyClient myClient) {
		this.players.add(myClient);
		myClient.setRoom(this);
		for (MyClient player : players) {
			RoomController.refreshRoom(player);
		}
	}
	
	public void removePlayer(MyClient myClient) {
		this.players.remove(myClient);
		myClient.setRoom(null);
		if(players.size()==0) {
			RoomController.delRoom(this);
		}
		else {
			for (MyClient player : players) {
				RoomController.refreshRoom(player);
			}
		}
	}
	
	public void removePlayerByName(String username) {
		for (MyClient myClient : players) {
			if(myClient.getUsername().equals(username)) {
				removePlayer(myClient);
				return;
			}
		}
	}
	
	public void cancel() {
		for (MyClient myClient : players) {
			myClient.setRoom(null);
		}
		players.clear();
	}
	
	public String getInfoRoom() {
		String str = "inforoom~" + String.valueOf(this.id) + "~" + String.valueOf(this.state) + "~" + String.valueOf(getCurQtyPlayer());
		for (MyClient myClient : players) {
			str += "~" + myClient.getUsername();
		}
		return str;
	}

	public int getId() {
		return id;
	}

	public int getState() {
		return state;
	}

	public String getHostPlayerName() {
		return players.get(0).getUsername();
	}
	
	public int getCurQtyPlayer() {
		return players.size();
	}
	
	public int getMaxQtyPlayer() {
		return maxQtyPlayer;
	}

	
}
