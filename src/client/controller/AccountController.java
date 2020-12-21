package client.controller;

import javax.swing.JOptionPane;

public class AccountController {
	public static void login(String acc, String pass) {
		if (acc.isEmpty() || pass.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Thiếu thông tin!");
		} else {
			ClientMain.username = acc;
			ClientMain.connection.Send("login~" + acc + "~" + pass);
		}
	}

	public static boolean signup(String acc, String pass, String confirmPass) {
		if (acc.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Thiếu thông tin!");
			return false;
		} else {
			if (pass.equals(confirmPass)) {
				ClientMain.connection.Send("signup~" + acc + "~" + pass);
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Mật khẩu xác nhận không chính xác!");
			}
			return false;
		}
	}
	
	public static void checkMessage(String command[]) {
		if(command[0].equals("replogin")){
			if(command[1].equals("0")) {
				JOptionPane.showMessageDialog(null, "Sai tài khoản hoặc mật khẩu!");
			}
			else if(command[1].equals("1")) {
				ClientMain.clientLogin.setVisible(false);
				ClientMain.showSelectRoom();
			}
			else if(command[1].equals("2")) {
				JOptionPane.showMessageDialog(null, "Tài khoản đang đăng nhập ở nơi khác!");
			}
		}		
		else if(command[0].equals("repsignup")){
			ClientMain.clientLogin.getBtnSignup().setEnabled(true);
			if(command[1].equals("0")) {
				JOptionPane.showMessageDialog(null, "Tài khoản đã tồn tại!");
			}
			else if(command[1].equals("1")) {
				JOptionPane.showMessageDialog(null, "Đăng ký tài khoản thành công!");
			}
			else if(command[1].equals("2")) {
				JOptionPane.showMessageDialog(null, "Lỗi không xác định!");
			}
		}
	}

}
