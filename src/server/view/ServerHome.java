package server.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ServerHome extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ServerHome frame = new ServerHome();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	
	private JLabel lbSvIp;
	private JLabel lbSvP;
	
	
	public ServerHome() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Server ip: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 63, 17);
		contentPane.add(lblNewLabel);
		
		lbSvIp = new JLabel("127.0.0.1");
		lbSvIp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbSvIp.setBounds(83, 11, 341, 17);
		contentPane.add(lbSvIp);
		
		JLabel lblNewLabel_1 = new JLabel("Port:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 39, 63, 14);
		contentPane.add(lblNewLabel_1);
		
		lbSvP = new JLabel("0000");
		lbSvP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbSvP.setBounds(83, 39, 341, 14);
		contentPane.add(lbSvP);
	}

	public JLabel getLbSvIp() {
		return lbSvIp;
	}

	public JLabel getLbSvP() {
		return lbSvP;
	}
}
