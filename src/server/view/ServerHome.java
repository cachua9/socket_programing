package server.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import server.controller.ServerMain;
import server.model.DBConnection;
import server.object.MyClient;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private JTextArea textArea;
	
	
	public ServerHome() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				for (MyClient myClient : ServerMain.connection.getMyClients()) {
					myClient.Close();
				}				
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
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
		lbSvIp.setBounds(83, 11, 171, 17);
		contentPane.add(lbSvIp);
		
		JLabel lblNewLabel_1 = new JLabel("Port:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 39, 63, 14);
		contentPane.add(lblNewLabel_1);
		
		lbSvP = new JLabel("0000");
		lbSvP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbSvP.setBounds(83, 39, 171, 14);
		contentPane.add(lbSvP);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 64, 414, 386);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		JButton btnNewButton = new JButton("Khởi tạo lại database");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn khởi tạo lại database không?");
				if(confirm == 0) DBConnection.initDatabase();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(264, 10, 160, 23);
		contentPane.add(btnNewButton);
	}

	public JLabel getLbSvIp() {
		return lbSvIp;
	}

	public JLabel getLbSvP() {
		return lbSvP;
	}
	
	public void println(Object object) {
		String text = this.textArea.getText();
		text += object.toString() + "\n";
		this.textArea.setText(text);
	}
}
