package server.controller;

import server.model.Question;
import server.object.MyClient;

public class GameController {
	
	public static void checkMessage(MyClient myClient, String command[]) {
		if(command[0].equals("gameanswer")) {
			checkAnswer(myClient, command);
		}
		else if(command[0].equals("gamecontinue")) {
			myClient.getRoom().setNext(1);
		}
	}
	
	private static void checkAnswer(MyClient myClient, String[] command) {
		int answer = Integer.valueOf(command[1]);
		//ServerMain.serverHome.println(command[1] + " - " + myClient.getRoom().getCurQuestion().getTrueAnswer());
		if(myClient.getRoom().getMainPlayer() == null) {
			if (answer == myClient.getRoom().getCurQuestion().getTrueAnswer()) {
				myClient.getRoom().setNext(3);
				myClient.getRoom().setMainPlayer(myClient);
			}
		}
		else {
			if(myClient == myClient.getRoom().getMainPlayer()) {
				if(answer == myClient.getRoom().getCurQuestion().getTrueAnswer()) {
					myClient.getRoom().setNext(1);
				}
				else {
					myClient.getRoom().setNext(2);
				}
			}
		}
	}
	
	public static void sendQuestion(MyClient myClient, Question question, int role) {
		String message = "gamequestion~" + (myClient.getRoom().getCau() + 1) + "~" + question.getQuestion();
		for (String answer : question.getAnswer()) {
			message += "~" + answer;
		}
		message += "~" + String.valueOf(role);
		myClient.Send(message);
	}
	
	public static void sendTime(MyClient myClient, int time) {
		String message = "gametime~" + String.valueOf(time);
		myClient.Send(message);
	}

}
