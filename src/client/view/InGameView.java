package client.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.controller.GameController;
import client.controller.RoomController;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InGameView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InGameView frame = new InGameView();
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
	private JTextArea tAQuestion;
	private JLabel lbCau;
	private JButton btn5050;
	private JButton btnHoiKhanGia;
	private JButton btnHoiTruongQuay;
	private JButton btnBtnDoiCau;
	private JLabel lblHelp;
	private JButton btnA;
	private JButton btnB;
	private JButton btnC;
	private JButton btnD;
	private JLabel lbTime;
	private JLabel lbRoom;
	private JLabel lbMainPlayer;
	
	public InGameView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tAQuestion = new JTextArea();
		tAQuestion.setFont(new Font("Monospaced", Font.PLAIN, 16));
		tAQuestion.setBounds(31, 86, 387, 190);
		tAQuestion.setWrapStyleWord(true);
		tAQuestion.setLineWrap(true);
		tAQuestion.setEditable(false);
		contentPane.add(tAQuestion);
		
		lbCau = new JLabel("Câu");
		lbCau.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbCau.setBounds(31, 64, 157, 20);
		contentPane.add(lbCau);
		
		btnA = new JButton("A");
		btnA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GameController.sendAnswer(0);
			}
		});
		btnA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnA.setBounds(31, 311, 200, 40);
		contentPane.add(btnA);
		
		btnB = new JButton("B");
		btnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameController.sendAnswer(1);
			}
		});
		btnB.setBounds(354, 311, 200, 40);
		contentPane.add(btnB);
		
		btnC = new JButton("C");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameController.sendAnswer(2);
			}
		});
		btnC.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnC.setBounds(31, 373, 200, 40);
		contentPane.add(btnC);
		
		btnD = new JButton("D");
		btnD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameController.sendAnswer(3);
			}
		});
		btnD.setBounds(354, 373, 200, 40);
		contentPane.add(btnD);
		
		btn5050 = new JButton("50:50");
		btn5050.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameController.useHelp(0);
			}
		});
		btn5050.setBounds(428, 111, 130, 33);
		contentPane.add(btn5050);
		
		btnHoiKhanGia = new JButton("Hỏi khán giả");
		btnHoiKhanGia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameController.useHelp(1);
			}
		});
		btnHoiKhanGia.setBounds(428, 155, 130, 33);
		contentPane.add(btnHoiKhanGia);
		
		btnHoiTruongQuay = new JButton("Hỏi Trường quay");
		btnHoiTruongQuay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameController.useHelp(2);
			}
		});
		btnHoiTruongQuay.setBounds(428, 199, 130, 33);
		contentPane.add(btnHoiTruongQuay);
		
		btnBtnDoiCau = new JButton("Đổi câu");
		btnBtnDoiCau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameController.useHelp(3);
			}
		});
		btnBtnDoiCau.setBounds(428, 243, 130, 33);
		contentPane.add(btnBtnDoiCau);
		
		lblHelp = new JLabel("Trợ giúp");
		lblHelp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHelp.setBounds(458, 86, 61, 20);
		contentPane.add(lblHelp);
		
		JButton btnEnd = new JButton("Thoát");
		btnEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RoomController.leaveRoom();
			}
		});
		btnEnd.setBounds(485, 427, 89, 23);
		contentPane.add(btnEnd);
		
		lbTime = new JLabel("30");
		lbTime.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbTime.setBounds(485, 32, 22, 20);
		contentPane.add(lbTime);
		
		lbRoom = new JLabel("Phòng");
		lbRoom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbRoom.setBounds(31, 11, 115, 17);
		contentPane.add(lbRoom);
		
		lbMainPlayer = new JLabel("Người chơi chính: ");
		lbMainPlayer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbMainPlayer.setBounds(31, 36, 241, 17);
		contentPane.add(lbMainPlayer);
		lbMainPlayer.setVisible(false);
		
		btn5050.setVisible(false);
		btnHoiKhanGia.setVisible(false);
		btnHoiTruongQuay.setVisible(false);
		btnBtnDoiCau.setVisible(false);
		lblHelp.setVisible(false);
		
	}

	public JTextArea gettAQuestion() {
		return tAQuestion;
	}

	public JLabel getLbCau() {
		return lbCau;
	}

	public JButton getBtn5050() {
		return btn5050;
	}

	public JButton getBtnHoiKhanGia() {
		return btnHoiKhanGia;
	}

	public JButton getBtnHoiTruongQuay() {
		return btnHoiTruongQuay;
	}

	public JButton getBtnBtnDoiCau() {
		return btnBtnDoiCau;
	}

	public JLabel getLblHelp() {
		return lblHelp;
	}

	public JButton getBtnA() {
		return btnA;
	}

	public JButton getBtnB() {
		return btnB;
	}

	public JButton getBtnC() {
		return btnC;
	}

	public JButton getBtnD() {
		return btnD;
	}

	public JLabel getLbTime() {
		return lbTime;
	}

	public JLabel getLbRoom() {
		return lbRoom;
	}

	public JLabel getLbMainPlayer() {
		return lbMainPlayer;
	}
}
