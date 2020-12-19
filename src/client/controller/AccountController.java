package client.controller;

public class AccountController {
	public static void login(String acc, String pass) {
		ClientMain.connection.Send("login|" + acc + "|" + pass);
	}
	
	public static boolean signup(String acc, String pass, String confirmPass) {
		if(pass.equals(confirmPass)) {
			ClientMain.connection.Send("signup|" + acc + "|" + pass);
			return true;
		}
		return false;
	}

}
