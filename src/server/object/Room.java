package server.object;

import java.util.ArrayList;
import java.util.Random;

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
	private long lastTimeRequestHelp = 0;
	private Question curQuestion;
	private MyClient mainPlayer;
	private int next;
	private int cau = -1;
	private int A;
	private int B;
	private int C;
	private int D;
	private MyClient helper;
	
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
						for (MyClient myClient : players) {
							myClient.Send("maingamestart");
						}
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
								if(next == 5) {
									A = 0; B = 0; C = 0; D = 0;
									lastTimeSendQuestion += 15000;
									lastTimeRequestHelp = System.currentTimeMillis();
									while (System.currentTimeMillis() - lastTimeRequestHelp < 15000) {
										if(next == 2) break;
										sleep(1000);
										for (MyClient myClient : players) {
											GameController.sendTime(myClient, 15 - (int)((System.currentTimeMillis() - lastTimeRequestHelp)/1000));
										}
									}
									if(next == 5) setNext(0);	
									for (MyClient myClient : players) {
										myClient.Send("gameendhelp~0");
									}
									int ap = 0;
									int bp = 0;
									int cp = 0;
									int dp = 0;
									if(A+B+C+D != 0) {
										ap = (int)(A*100/(A+B+C+D));
										bp = (int)(B*100/(A+B+C+D));
										cp = (int)(C*100/(A+B+C+D));
										dp = (int)(D*100/(A+B+C+D));
									}
									else {
										Random r = new Random();
										if(curQuestion.getTrueAnswer() == 0) {
											ap = r.nextInt(49) + 51;
											bp = r.nextInt(100 - ap);
											cp = r.nextInt(100 - ap - bp);
											dp = 100 - ap - bp - cp;
										}
										else if(curQuestion.getTrueAnswer() == 1) {
											bp = r.nextInt(49) + 51;
											ap = r.nextInt(100 - bp);
											cp = r.nextInt(100 - bp - ap);
											dp = 100 - bp - ap - cp;
										}
										else if(curQuestion.getTrueAnswer() == 2) {
											cp = r.nextInt(49) + 51;
											bp = r.nextInt(100 - cp);
											ap = r.nextInt(100 - cp - bp);
											dp = 100 - cp - bp - ap;
										}
										else if(curQuestion.getTrueAnswer() == 3) {
											dp = r.nextInt(49) + 51;
											bp = r.nextInt(100 - dp);
											cp = r.nextInt(100 - dp - bp);
											ap = 100 - dp - bp - cp;
										}
									}
									for (MyClient myClient : players) {
										myClient.Send("gamehelpresult~0~" + ap + "~" + bp + "~" + cp + "~" + dp);
									}
								}
								else if(next == 6) {
									lastTimeSendQuestion += 10000;
									helper = null;
									lastTimeRequestHelp = System.currentTimeMillis();
									while (System.currentTimeMillis() - lastTimeRequestHelp < 10000) {
										if(next == 7) break;
										sleep(1000);
										for (MyClient myClient : players) {
											GameController.sendTime(myClient, 10 - (int)((System.currentTimeMillis() - lastTimeRequestHelp)/1000));
										}
									}
									if(next != 7) {
										for (MyClient myClient : players) {
											myClient.Send("gamecancelhelp~1");
										}
										setNext(0);
									}
								}
								else if(next == 7) {									
									lastTimeSendQuestion += 30000;
									lastTimeRequestHelp = System.currentTimeMillis();
									while (System.currentTimeMillis() - lastTimeRequestHelp < 30000) {
										if(next == 2) break;
										sleep(1000);
										for (MyClient myClient : players) {
											GameController.sendTime(myClient, 30 - (int)((System.currentTimeMillis() - lastTimeRequestHelp)/1000));
										}
									}
									if(next == 7) setNext(0);
									for (MyClient myClient : players) {
										myClient.Send("gameendhelp~1");
										myClient.Send("gamehelpresult~1");
									}
								}
								else if(next != 0) {
									break;
								}
							}	
							if(next == 4) {
								continue;
							}
							else if(next!=1) {
								endGame();
								break;
							}
							cau++;
						}
						winGame();
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
	
	public void useHelp(int index) {
		if(index == 0) {
			for (MyClient myClient : players) {
				myClient.Send("gamerephelp~0~" + getCurQuestion().getTrueAnswer());
			}	
			
		}
		else if(index == 1) {
			setNext(6);
			for (MyClient myClient : players) {
				if(myClient != mainPlayer) {
					myClient.Send("gamerephelp~1");
				}
			}
		}
		else if(index == 2) {
			setNext(5);
			for (MyClient myClient : players) {
				if(myClient != mainPlayer) {
					myClient.Send("gamerephelp~2");
				}
			}
		}
		else if(index == 3) {
			for (MyClient myClient : players) {
				myClient.Send("gamerephelp~3");
			}
			setNext(4);
		}
			
	}
	
	private void winGame() {
		for (MyClient player : players) {
			if(player != mainPlayer) {
				player.Send("wingame~" + "1");
			}
			else {
				player.Send("wingame~" + "0");
			}
		}
		mainPlayer = null;
		this.state = 0;
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
	
	public void incA() {
		A++;
	}
	
	public void incB() {
		B++;
	}
	
	public void incC() {
		C++;
	}
	
	public void incD() {
		D++;
	}

	public MyClient getHelper() {
		return helper;
	}

	public void setHelper(MyClient helper) {
		this.helper = helper;
	}

	public int getNext() {
		return next;
	}

	
}
