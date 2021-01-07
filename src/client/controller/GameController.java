package client.controller;

import javax.swing.JOptionPane;

public class GameController {

	public static void checkMessage(String command[]) {
		if(command[0].equals("startgame")) {
			ClientMain.generalView.switchToMe(ClientMain.inGameView);
		}
		else if(command[0].equals("gamequestion")) {
			setQuestion(command);
		}
		else if(command[0].equals("gametime")) {
			setTime(command);
		}
	}
	
	private static void setQuestion(String[] command) {
		if(command[1].equals("-1")) {
			ClientMain.inGameView.getLbCau().setText("Câu khởi động");
			ClientMain.inGameView.getLbTime().setText("10");
		}
		else {
			ClientMain.inGameView.getLbCau().setText("Câu " + command[1]);
			ClientMain.inGameView.getLbTime().setText("60");
		}
		String text = command[2] + "\n";
		text += "A, " + command[3] + "\n";
		text += "B, " + command[4] + "\n";
		text += "C, " + command[5] + "\n";
		text += "D, " + command[6] + "\n";
		ClientMain.inGameView.gettAQuestion().setText(text);
		if(command[7].equals("1")) {
			ClientMain.inGameView.getBtnA().setEnabled(true);
			ClientMain.inGameView.getBtnB().setEnabled(true);
			ClientMain.inGameView.getBtnC().setEnabled(true);
			ClientMain.inGameView.getBtnD().setEnabled(true);
			ClientMain.inGameView.getBtn5050().setVisible(true);
			ClientMain.inGameView.getBtnHoiKhanGia().setVisible(true);
			ClientMain.inGameView.getBtnGoiDien().setVisible(true);
			ClientMain.inGameView.getBtnBtnDoiCau().setVisible(true);
			ClientMain.inGameView.getLblHelp().setVisible(true);
		}
		else {
			ClientMain.inGameView.getBtnA().setEnabled(false);
			ClientMain.inGameView.getBtnB().setEnabled(false);
			ClientMain.inGameView.getBtnC().setEnabled(false);
			ClientMain.inGameView.getBtnD().setEnabled(false);
		}
	}
	
	private static void setTime(String[] command) {
		ClientMain.inGameView.getLbTime().setText(command[1]);
	}
	
	public static void sendAnswer(int answer) {
		int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn chọn phương án " + (char)(65+answer) + "?");
		if(confirm == 0) {
			ClientMain.connection.Send("gameanswer~" + String.valueOf(answer));
		}
	}
}
