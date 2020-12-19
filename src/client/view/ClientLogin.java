package client.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.controller.ClientMain;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;

public class ClientLogin extends JFrame {

	private JPanel contentPane;
	private JTextField tfLoginAcc;
	private JPasswordField tfLoginPass;
	private JTextField tfSignupAcc;
	private JPasswordField tfSigupPass;
	private JPasswordField tfSigupConfirmPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientLogin frame = new ClientLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientLogin() {
		setTitle("Ai là triệu phú");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 275);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 324, 224);
		contentPane.add(tabbedPane);
		
		JPanel panelLogin = new JPanel();
		tabbedPane.addTab("Đăng nhập", null, panelLogin, null);
		panelLogin.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tài khoản:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(37, 36, 64, 17);
		panelLogin.add(lblNewLabel);
		
		JLabel lblMtKhu = new JLabel("Mật khẩu:");
		lblMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMtKhu.setBounds(37, 81, 64, 17);
		panelLogin.add(lblMtKhu);
		
		tfLoginAcc = new JTextField();
		tfLoginAcc.setBounds(127, 36, 150, 20);
		panelLogin.add(tfLoginAcc);
		tfLoginAcc.setColumns(10);
		
		tfLoginPass = new JPasswordField();
		tfLoginPass.setBounds(127, 81, 150, 20);
		panelLogin.add(tfLoginPass);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(188, 129, 89, 23);
		panelLogin.add(btnLogin);
		
		JPanel panelSignuop = new JPanel();
		tabbedPane.addTab("Đăng ký", null, panelSignuop, null);
		panelSignuop.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Tài khoản:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 24, 64, 17);
		panelSignuop.add(lblNewLabel_1);
		
		tfSignupAcc = new JTextField();
		tfSignupAcc.setColumns(10);
		tfSignupAcc.setBounds(144, 24, 130, 20);
		panelSignuop.add(tfSignupAcc);
		
		JLabel lblMtKhu_1 = new JLabel("Mật khẩu:");
		lblMtKhu_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMtKhu_1.setBounds(10, 68, 64, 17);
		panelSignuop.add(lblMtKhu_1);
		
		tfSigupPass = new JPasswordField();
		tfSigupPass.setBounds(144, 68, 130, 20);
		panelSignuop.add(tfSigupPass);
		
		JButton btnSignup = new JButton("Login");
		btnSignup.setBounds(185, 144, 89, 23);
		panelSignuop.add(btnSignup);
		
		tfSigupConfirmPass = new JPasswordField();
		tfSigupConfirmPass.setBounds(144, 111, 130, 20);
		panelSignuop.add(tfSigupConfirmPass);
		
		JLabel lblMtKhu_1_1 = new JLabel("Xác nhận mật khẩu:");
		lblMtKhu_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMtKhu_1_1.setBounds(10, 111, 124, 17);
		panelSignuop.add(lblMtKhu_1_1);
	}
}
