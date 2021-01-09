package client.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.controller.GameController;

import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewerHelp extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewerHelp frame = new ViewerHelp();
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
	private JLabel lbTime;
	private JTextArea tAQuestion;
	
	public ViewerHelp() {
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
		
		tAQuestion = new JTextArea();
		tAQuestion.setWrapStyleWord(true);
		tAQuestion.setLineWrap(true);
		tAQuestion.setFont(new Font("Monospaced", Font.PLAIN, 16));
		tAQuestion.setEditable(false);
		tAQuestion.setBounds(10, 70, 387, 190);
		contentPane.add(tAQuestion);
		
		JButton btnA = new JButton("A");
		btnA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameController.sendAnswerHelp(0);
			}
		});
		btnA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnA.setBounds(10, 280, 170, 40);
		contentPane.add(btnA);
		
		JButton btnB = new JButton("B");
		btnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameController.sendAnswerHelp(1);
			}
		});
		btnB.setBounds(227, 280, 170, 40);
		contentPane.add(btnB);
		
		JButton btnC = new JButton("C");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameController.sendAnswerHelp(2);
			}
		});
		btnC.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnC.setBounds(10, 331, 170, 40);
		contentPane.add(btnC);
		
		JButton btnD = new JButton("D");
		btnD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameController.sendAnswerHelp(3);
			}
		});
		btnD.setBounds(227, 331, 170, 40);
		contentPane.add(btnD);
		
		JLabel lblNewLabel = new JLabel("Hãy chọn câu trả lời bạn cho là đúng để trợ giúp người chơi.");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 42, 387, 17);
		contentPane.add(lblNewLabel);
		
		lbTime = new JLabel("15");
		lbTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbTime.setBounds(359, 11, 40, 20);
		contentPane.add(lbTime);
	}

	public JLabel getLbTime() {
		return lbTime;
	}

	public JTextArea gettAQuestion() {
		return tAQuestion;
	}

}
