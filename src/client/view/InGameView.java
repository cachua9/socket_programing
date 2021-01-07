package client.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.controller.GameController;

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
	private JButton btnGoiDien;
	private JButton btnBtnDoiCau;
	private JLabel lblHelp;
	private JButton btnA;
	private JButton btnB;
	private JButton btnC;
	private JButton btnD;
	private JLabel lbTime;
	
	public InGameView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tAQuestion = new JTextArea();
		tAQuestion.setFont(new Font("Monospaced", Font.PLAIN, 16));
		tAQuestion.setBounds(31, 66, 387, 210);
		tAQuestion.setWrapStyleWord(true);
		tAQuestion.setLineWrap(true);
		tAQuestion.setEditable(false);
		contentPane.add(tAQuestion);
		
		lbCau = new JLabel("Câu");
		lbCau.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbCau.setBounds(31, 35, 157, 20);
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
		btn5050.setBounds(449, 111, 105, 33);
		contentPane.add(btn5050);
		
		btnHoiKhanGia = new JButton("Hỏi khán giả");
		btnHoiKhanGia.setBounds(449, 155, 105, 33);
		contentPane.add(btnHoiKhanGia);
		
		btnGoiDien = new JButton("Gọi điện");
		btnGoiDien.setBounds(449, 199, 105, 33);
		contentPane.add(btnGoiDien);
		
		btnBtnDoiCau = new JButton("Đổi câu");
		btnBtnDoiCau.setBounds(449, 243, 105, 33);
		contentPane.add(btnBtnDoiCau);
		
		lblHelp = new JLabel("Trợ giúp");
		lblHelp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHelp.setBounds(468, 69, 61, 20);
		contentPane.add(lblHelp);
		
		JButton btnEnd = new JButton("Kết thúc");
		btnEnd.setBounds(485, 427, 89, 23);
		contentPane.add(btnEnd);
		
		lbTime = new JLabel("30");
		lbTime.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbTime.setBounds(485, 22, 22, 20);
		contentPane.add(lbTime);
		
		btn5050.setVisible(false);
		btnHoiKhanGia.setVisible(false);
		btnGoiDien.setVisible(false);
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

	public JButton getBtnGoiDien() {
		return btnGoiDien;
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
}
