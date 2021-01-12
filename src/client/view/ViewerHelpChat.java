package client.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.controller.GameController;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class ViewerHelpChat extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewerHelpChat frame = new ViewerHelpChat();
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
	private JTextArea textArea;
	private JTextArea tAInput;
	private JLabel lbTime;
	
	public ViewerHelpChat() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				setVisible(false);
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 425, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 389, 288);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);	
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 310, 290, 61);
		contentPane.add(scrollPane_1);
		
		tAInput = new JTextArea();
		scrollPane_1.setViewportView(tAInput);
		tAInput.setWrapStyleWord(true);
		tAInput.setLineWrap(true);
		
		JButton btnSend = new JButton("Gửi");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!tAInput.getText().isEmpty()) {
					String text = textArea.getText();
					text += "You: " + tAInput.getText() + "\n";
					textArea.setText(text);
					GameController.sendChat(tAInput.getText());
					tAInput.setText("");
				}
			}
		});
		btnSend.setBounds(310, 331, 89, 40);
		contentPane.add(btnSend);
		
		lbTime = new JLabel("30");
		lbTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbTime.setBounds(343, 310, 24, 17);
		contentPane.add(lbTime);
	}
	
	public void clearChat() {
		textArea.setText("");
	}
	
	public void addMessage(String user, String message) {
		String text = textArea.getText();
		text += user + ": " + message + "\n";
		textArea.setText(text);
	}

	public JLabel getLbTime() {
		return lbTime;
	}
	
}
