package server.object;

import java.util.ArrayList;

import server.controller.GameController;
import server.controller.RoomController;
import server.model.Question;

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
		if(myClient == mainPlayer) {
			next = 2;
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
	
	private long lastTimeSendQuestion = 0;
	private Question curQuestion;
	private MyClient mainPlayer;
	private int next;
	private int cau = -1;
	
	public void StartGame() {
		this.state = 1;
		for (MyClient myClient : players) {
			myClient.Send("startgame");
		}
		Thread thread = new Thread() {
			public void run() {
				//
				try {
					cau = -1;
					mainPlayer = null;
					curQuestion = Question.getRandomQuestionByLevel(1);
					next = 0;
					for (MyClient myClient : players) {
						GameController.sendQuestion(myClient, curQuestion, 1);
					}
					lastTimeSendQuestion = System.currentTimeMillis();
					while (System.currentTimeMillis() - lastTimeSendQuestion < 10000) {
						sleep(1000);
						for (MyClient myClient : players) {
							GameController.sendTime(myClient, 10 - (int)((System.currentTimeMillis() - lastTimeSendQuestion)/1000));
						}
					}
					if(next == 3) {
						for (MyClient myClient : players) {
							myClient.Send("gamesetmainplayer~" + mainPlayer.getUsername());
						}
						while (next == 3) {
							sleep(100);
						}
					}					
					if(next == 1) {
						cau = 0;
						while (cau < 15) {
							if(cau < 5) {
								curQuestion = Question.getRandomQuestionByLevel(1);
							}
							else if(cau < 10) {
								curQuestion = Question.getRandomQuestionByLevel(2);
							}
							else {
								curQuestion = Question.getRandomQuestionByLevel(3);
							}
							next = 0;
							for (MyClient myClient : players) {
								if(myClient == mainPlayer) {
									GameController.sendQuestion(myClient, curQuestion, 1);
								}
								else {
									GameController.sendQuestion(myClient, curQuestion, 0);
								}
							}
							lastTimeSendQuestion = System.currentTimeMillis();
							while (System.currentTimeMillis() - lastTimeSendQuestion < 60000) {
								sleep(1000);
								for (MyClient myClient : players) {
									GameController.sendTime(myClient, 60 - (int)((System.currentTimeMillis() - lastTimeSendQuestion)/1000));
								}
								if(next != 0) break;
							}
							if(next == 3) {
								lastTimeSendQuestion += 1000;
							}
							else if(next!=1) {
								endGame();
								break;
							}
							cau++;
						}
					}
					else endGame();
					System.out.println("end");
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		};
		thread.setDaemon(true);
		thread.start();		
	}
	
	private void endGame() {
		for (MyClient player : players) {
			if(player != mainPlayer) {
				player.Send("endgame~" + (cau + 1));
			}
			else {
				player.Send("endgame~" + (cau +1) + "~0");
			}
		}
		mainPlayer = null;
		this.state = 0;
	}

	public void setMainPlayer(MyClient mainPlayer) {
		this.mainPlayer = mainPlayer;		
	}

	public Question getCurQuestion() {
		return curQuestion;
	}

	public MyClient getMainPlayer() {
		return mainPlayer;
	}

	public int getCau() {
		return cau;
	}

	public void setNext(int next) {
		this.next = next;
	}

	
}
