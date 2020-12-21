package client.object;

import java.util.ArrayList;

public class Room {
	private int id;
	private int state;
	private final int MaxQtyPlayer = 100;
	private ArrayList<String> players = new ArrayList<String>();
	
	public Room(int id, int state, String hostPlayer) {
		super();
		this.id = id;
		this.state = state;
		this.players.add(hostPlayer);
	}
	
	public void clearPlayer() {
		this.players.clear();
	}
	
	public void addPlayer(String player) {
		this.players.add(player);
		//System.out.println(player);
	}
	
	public void removePlayerByIndex(int index) {
		this.players.remove(index);
	}
	
	public String getPlayerNameByIndex(int index) {
//		System.out.println(index);
//		System.out.println(this.players.get(index));
		return this.players.get(index);
	}

	public int getId() {
		return id;
	}

	public int getState() {
		return state;
	}

	public int getCurQtyPlayer() {
		return players.size();
	}

	public String getHostPlayer() {
		return players.get(0);
	}

	public int getMaxQtyPlayer() {
		return MaxQtyPlayer;
	}

	
}
