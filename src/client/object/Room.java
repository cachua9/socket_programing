package client.object;

public class Room {
	private int id;
	private int state;
	private int CurQtyPlayer;
	private final int MaxQtyPlayer = 10;
	private String hostPlayer;
	
	public Room(int id, int state, int curQtyPlayer, String hostPlayer) {
		super();
		this.id = id;
		this.state = state;
		CurQtyPlayer = curQtyPlayer;
		this.hostPlayer = hostPlayer;
	}

	public int getId() {
		return id;
	}

	public int getState() {
		return state;
	}

	public int getCurQtyPlayer() {
		return CurQtyPlayer;
	}

	public String getHostPlayer() {
		return hostPlayer;
	}

	public int getMaxQtyPlayer() {
		return MaxQtyPlayer;
	}

	
}
