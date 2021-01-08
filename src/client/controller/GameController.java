package client.controller;

import javax.swing.JOptionPane;

public class GameController {

	public static void checkMessage(String command[]) {
		if(command[0].equals("startgame")) {
			ClientMain.inGameView.getLbRoom().setText("Phòng " + RoomController.rooms.get(RoomController.curRoomIndex).getId());
			ClientMain.inGameView.getBtn5050().setVisible(false);
			ClientMain.inGameView.getBtnHoiKhanGia().setVisible(false);
			ClientMain.inGameView.getBtnGoiDien().setVisible(false);
			ClientMain.inGameView.getBtnBtnDoiCau().setVisible(false);
			ClientMain.inGameView.getLblHelp().setVisible(false);
			ClientMain.inGameView.getLbMainPlayer().setVisible(false);
			ClientMain.generalView.switchToMe(ClientMain.inGameView);			
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
	}
	
	private static void setQuestion(String[] command) {
		if(command[1].equals("0")) {
			ClientMain.inGameView.getLbCau().setText("Câu khởi động");
			ClientMain.inGameView.getLbTime().setText("10");
		}
		else {
			ClientMain.inGameView.getLbCau().setText("Câu " + command[1]);
			ClientMain.inGameView.getLbTime().setText("60");
		}
		if(command[1].equals("1")) {
			ClientMain.inGameView.getBtn5050().setVisible(true);
			ClientMain.inGameView.getBtnHoiKhanGia().setVisible(true);
			ClientMain.inGameView.getBtnGoiDien().setVisible(true);
			ClientMain.inGameView.getBtnBtnDoiCau().setVisible(true);
			ClientMain.inGameView.getLblHelp().setVisible(true);
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
			ClientMain.inGameView.getBtnGoiDien().setEnabled(true);
			ClientMain.inGameView.getBtnBtnDoiCau().setEnabled(true);
		}
		else {
			ClientMain.inGameView.getBtnA().setEnabled(false);
			ClientMain.inGameView.getBtnB().setEnabled(false);
			ClientMain.inGameView.getBtnC().setEnabled(false);
			ClientMain.inGameView.getBtnD().setEnabled(false);
			ClientMain.inGameView.getBtn5050().setEnabled(false);
			ClientMain.inGameView.getBtnHoiKhanGia().setEnabled(false);
			ClientMain.inGameView.getBtnGoiDien().setEnabled(false);
			ClientMain.inGameView.getBtnBtnDoiCau().setEnabled(false);
		}
	}
	
	private static void setTime(String[] command) {
		ClientMain.inGameView.getLbTime().setText(command[1]);
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
		ClientMain.generalView.switchToMe(ClientMain.roomView);
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
	
	public static void useHelp(int index) {
		String help = "50:50";
		if(index == 1) {
			help = "Hỏi khán giả";
		}
		else if(index == 2) {
			help = "Gọi điện";
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
				ClientMain.inGameView.getBtnHoiKhanGia().setVisible(false);
				ClientMain.connection.Send("gamehelp~1");
			}
			else if(index == 2) {
				ClientMain.inGameView.getBtnGoiDien().setVisible(false);
				ClientMain.connection.Send("gamehelp~2");
			}
			else if(index == 3) {
				ClientMain.inGameView.getBtnBtnDoiCau().setVisible(false);
				ClientMain.connection.Send("gamehelp~3");
			}	
		}
	}
}
