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
		else if(command[0].equals("gamehelp")) {
			useGameHelp(myClient, command);
		}
		else if(command[0].equals("gameanswerhelp")) {
			checkAnswerHelp(myClient, command);
		}
		else if(command[0].equals("gameaccepthelp")) {
			acceptHepl(myClient);
		}
		else if(command[0].equals("gamechat")) {
			checkGameChat(myClient, command);
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
	
	private static void useGameHelp(MyClient myClient, String[] command) {
		int index = Integer.valueOf(command[1]);
		myClient.getRoom().useHelp(index);		
	}
	
	private static void checkAnswerHelp(MyClient myClient, String[] command) {
		if(command[1].equals("0")) {
			myClient.getRoom().incA();
		}
		else if(command[1].equals("1")) {
			myClient.getRoom().incB();
		}
		else if(command[1].equals("2")) {
			myClient.getRoom().incC();
		}
		else if(command[1].equals("3")) {
			myClient.getRoom().incD();
		}
	}
	
	private static void acceptHepl(MyClient myClient) {
		if(myClient.getRoom().getHelper() == null) {
			myClient.getRoom().setNext(7);
			myClient.getRoom().setHelper(myClient);
			myClient.getRoom().getMainPlayer().Send("gamestartchat");
			myClient.Send("gamestartchat");			
		}else {
			myClient.Send("gamerefusechat");
		}
	}
	
	private static void checkGameChat(MyClient myClient, String[] command) {
		if(myClient == myClient.getRoom().getMainPlayer()) {
			myClient.getRoom().getHelper().Send("gamechat~" + myClient.getUsername() + "~" + command[1]);
		}
		else {
			myClient.getRoom().getMainPlayer().Send("gamechat~" + myClient.getUsername() + "~" + command[1]);
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
