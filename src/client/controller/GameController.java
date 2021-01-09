package client.controller;

import javax.swing.JOptionPane;

import client.view.ViewerHelp;
import client.view.ViewerHelpChat;

public class GameController {
	
	public static ViewerHelp viewerHelp = new ViewerHelp();
	public static ViewerHelpChat viewerHelpChat = new ViewerHelpChat();

	public static void checkMessage(String command[]) {
		if(command[0].equals("startgame")) {
			startGame();
		}
		else if(command[0].equals("gamequestion")) {
			setQuestion(command);
		}
		else if(command[0].equals("gametime")) {
			setTime(command);
		}
		else if(command[0].equals("gamesetmainplayer")) {
			setMainPlayer(command);
		}
		else if(command[0].equals("endgame")) {
			endGame(command);
		}
		else if(command[0].equals("gamerephelp")) {
			showHelp(command);
		}
		else if(command[0].equals("maingamestart")) {
			setVisibleHelp();
		}
		else if(command[0].equals("gameendhelp")) {
			endHelp(command);
		}
		else if(command[0].equals("gamehelpresult")) {
			showResultHelp(command);
		}
		else if(command[0].equals("gamerefusechat")) {
			JOptionPane.showMessageDialog(null, "Đã có người khác hỗ trợ người chơi");
		}
		else if(command[0].equals("gamestartchat")) {
			viewerHelpChat.setLocation(ClientMain.generalView.getLocation());
			viewerHelpChat.setVisible(true);
		}
		else if(command[0].equals("gamechat")) {
			setMessage(command);
		}
	}
	
	private static void startGame() {
		ClientMain.inGameView.getLbRoom().setText("Phòng " + RoomController.rooms.get(RoomController.curRoomIndex).getId());
		ClientMain.inGameView.getBtn5050().setVisible(false);
		ClientMain.inGameView.getBtnHoiKhanGia().setVisible(false);
		ClientMain.inGameView.getBtnHoiTruongQuay().setVisible(false);
		ClientMain.inGameView.getBtnBtnDoiCau().setVisible(false);
		ClientMain.inGameView.getLblHelp().setVisible(false);
		ClientMain.inGameView.getLbMainPlayer().setVisible(false);		
		ClientMain.generalView.switchToMe(ClientMain.inGameView);			
	}
	
	private static void setQuestion(String[] command) {
		ClientMain.inGameView.getBtnA().setVisible(true);
		ClientMain.inGameView.getBtnB().setVisible(true);
		ClientMain.inGameView.getBtnC().setVisible(true);
		ClientMain.inGameView.getBtnD().setVisible(true);
		if(command[1].equals("0")) {
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
			ClientMain.inGameView.getBtn5050().setEnabled(true);
			ClientMain.inGameView.getBtnHoiKhanGia().setEnabled(true);
			ClientMain.inGameView.getBtnHoiTruongQuay().setEnabled(true);
			ClientMain.inGameView.getBtnBtnDoiCau().setEnabled(true);
		}
		else {
			ClientMain.inGameView.getBtnA().setEnabled(false);
			ClientMain.inGameView.getBtnB().setEnabled(false);
			ClientMain.inGameView.getBtnC().setEnabled(false);
			ClientMain.inGameView.getBtnD().setEnabled(false);
			ClientMain.inGameView.getBtn5050().setEnabled(false);
			ClientMain.inGameView.getBtnHoiKhanGia().setEnabled(false);
			ClientMain.inGameView.getBtnHoiTruongQuay().setEnabled(false);
			ClientMain.inGameView.getBtnBtnDoiCau().setEnabled(false);
		}
	}
	
	private static void setVisibleHelp() {
		ClientMain.inGameView.getBtn5050().setVisible(true);
		ClientMain.inGameView.getBtnHoiKhanGia().setVisible(true);
		ClientMain.inGameView.getBtnHoiTruongQuay().setVisible(true);
		ClientMain.inGameView.getBtnBtnDoiCau().setVisible(true);
		ClientMain.inGameView.getLblHelp().setVisible(true);
	}
	
	private static void setTime(String[] command) {
		ClientMain.inGameView.getLbTime().setText(command[1]);
		viewerHelp.getLbTime().setText(command[1]);
		viewerHelpChat.getLbTime().setText(command[1]);
	}
	
	private static void setMainPlayer(String[] command) {
		ClientMain.inGameView.getLbMainPlayer().setVisible(true);
		ClientMain.inGameView.getLbMainPlayer().setText("Người chơi chính: " + command[1]);
		if(ClientMain.username.equals(command[1])) {
			JOptionPane.showMessageDialog(null, "Bạn là người trả lời nhanh nhất, ấn ok để bắt đầu!");
			ClientMain.connection.Send("gamecontinue");
		}
		else {
			JOptionPane.showMessageDialog(null, "Người chơi trả lời nhanh nhất là: " + command[1]);
		}
	}
	
	private static void endGame(String[] command) {		
		if(command[1].equals("0")) {
			JOptionPane.showMessageDialog(null, "Game kết thúc, không có ai trả lời đúng câu hỏi nhanh!");
		}else {
			if(command.length == 2) {
				JOptionPane.showMessageDialog(null, "Người chơi đã dừng cuộc chơi ở câu số " + command[1]);
			}
			else {
				JOptionPane.showMessageDialog(null, "Bạn đã dừng cuộc chơi ở câu số " + command[1]);
			}
		}	
		ClientMain.generalView.switchToMe(ClientMain.roomView);
	}
	
	private static void showHelp(String[] command) {
		if(command[1].equals("0")) {
			if(command[2].equals("0")) {
				ClientMain.inGameView.getBtnB().setVisible(false);
				ClientMain.inGameView.getBtnC().setVisible(false);
			}
			else if(command[2].equals("1")) {
				ClientMain.inGameView.getBtnA().setVisible(false);
				ClientMain.inGameView.getBtnD().setVisible(false);
			}
			else if(command[2].equals("2")) {
				ClientMain.inGameView.getBtnA().setVisible(false);
				ClientMain.inGameView.getBtnD().setVisible(false);
			}
			else if(command[2].equals("3")) {
				ClientMain.inGameView.getBtnB().setVisible(false);
				ClientMain.inGameView.getBtnC().setVisible(false);
			}
			ClientMain.inGameView.getBtn5050().setVisible(false);
		}
		else if(command[1].equals("1")) {			
			ClientMain.inGameView.getBtnHoiKhanGia().setVisible(false);
			int confirm = JOptionPane.showConfirmDialog(null, "Người chơi cần trợ giúp, bạn có muốn nói chuyện với họ không?");
			if(confirm == 0) {
				ClientMain.connection.Send("gameaccepthelp");
			}
		}
		else if(command[1].equals("2")) {			
			ClientMain.inGameView.getBtnHoiTruongQuay().setVisible(false);
			viewerHelp.setLocation(ClientMain.generalView.getLocation());
			viewerHelp.setVisible(true);
		}
		else if(command[1].equals("3")) {
			ClientMain.inGameView.getBtnBtnDoiCau().setVisible(false);
		}
	}
	
	private static void endHelp(String[] command) {
		if(command[1].equals("0")) {
			viewerHelp.setVisible(false);
		}
		else if(command[1].equals("1")) {
			viewerHelpChat.setVisible(false);
		}
	}
	
	private static void showResultHelp(String[] command) {
		ClientMain.inGameView.getBtnA().setEnabled(true);
		ClientMain.inGameView.getBtnB().setEnabled(true);
		ClientMain.inGameView.getBtnC().setEnabled(true);
		ClientMain.inGameView.getBtnD().setEnabled(true);
		ClientMain.inGameView.getBtn5050().setEnabled(true);
		ClientMain.inGameView.getBtnHoiKhanGia().setEnabled(true);
		ClientMain.inGameView.getBtnHoiTruongQuay().setEnabled(true);
		ClientMain.inGameView.getBtnBtnDoiCau().setEnabled(true);
		if(command[1].equals("0")) {
			JOptionPane.showMessageDialog(null, "Phần trăm bình chọn của khán giả:\n" + "A: " + command[2] + "%\n" + "B: " + command[3] + "%\n" + "C: " + command[4] + "%\n" + "D: " + command[5] + "%\n");
		}
		
	}
	
	private static void setMessage(String[] command) {
		viewerHelpChat.addMessage(command[1], command[2]);
	}
	
	public static void sendAnswer(int answer) {
		int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn chọn phương án " + (char)(65+answer) + "?");
		if(confirm == 0) {
			ClientMain.connection.Send("gameanswer~" + String.valueOf(answer));
			ClientMain.inGameView.getBtnA().setEnabled(false);
			ClientMain.inGameView.getBtnB().setEnabled(false);
			ClientMain.inGameView.getBtnC().setEnabled(false);
			ClientMain.inGameView.getBtnD().setEnabled(false);
		}
	}
	
	
	public static void sendAnswerHelp(int answer) {
		int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn chọn phương án " + (char)(65+answer) + "?");
		if(confirm == 0) {
			ClientMain.connection.Send("gameanswerhelp~" + String.valueOf(answer));
			viewerHelp.setVisible(false);
		}
	}
	
	public static void useHelp(int index) {
		String help = "50:50";
		if(index == 1) {
			help = "Hỏi khán giả";
		}
		else if(index == 2) {
			help = "Hỏi trường quay";
		}
		else if(index == 3) {
			help = "Đổi câu";
		}		
		int confirm =JOptionPane.showConfirmDialog(null, "Bạn có muốn dùng sự trợ giúp " + help + " không?");
		if(confirm == 0) {
			if(index == 0) {
				ClientMain.inGameView.getBtn5050().setVisible(false);
				ClientMain.connection.Send("gamehelp~0");
			}
			else if(index == 1) {
				ClientMain.inGameView.getBtn5050().setEnabled(false);
				ClientMain.inGameView.getBtnHoiKhanGia().setEnabled(false);
				ClientMain.inGameView.getBtnHoiTruongQuay().setEnabled(false);
				ClientMain.inGameView.getBtnBtnDoiCau().setEnabled(false);
				ClientMain.inGameView.getBtnA().setEnabled(false);
				ClientMain.inGameView.getBtnB().setEnabled(false);
				ClientMain.inGameView.getBtnC().setEnabled(false);
				ClientMain.inGameView.getBtnD().setEnabled(false);
				ClientMain.inGameView.getBtnHoiKhanGia().setVisible(false);
				ClientMain.connection.Send("gamehelp~1");
			}
			else if(index == 2) {
				ClientMain.inGameView.getBtn5050().setEnabled(false);
				ClientMain.inGameView.getBtnHoiKhanGia().setEnabled(false);
				ClientMain.inGameView.getBtnHoiTruongQuay().setEnabled(false);
				ClientMain.inGameView.getBtnBtnDoiCau().setEnabled(false);
				ClientMain.inGameView.getBtnA().setEnabled(false);
				ClientMain.inGameView.getBtnB().setEnabled(false);
				ClientMain.inGameView.getBtnC().setEnabled(false);
				ClientMain.inGameView.getBtnD().setEnabled(false);
				ClientMain.inGameView.getBtnHoiTruongQuay().setVisible(false);
				ClientMain.connection.Send("gamehelp~2");				
			}
			else if(index == 3) {
				ClientMain.inGameView.getBtnBtnDoiCau().setVisible(false);
				ClientMain.connection.Send("gamehelp~3");
			}	
		}
	}
	
	public static void sendChat(String message) {
		ClientMain.connection.Send("gamechat~" + message);
	}
}
