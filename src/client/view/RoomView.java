package client.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import client.controller.ClientMain;
import javax.swing.JLabel;

public class RoomView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoomView frame = new RoomView();
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
	private JList<String> list;
	private JButton btnOutRoom;
	private JButton btnPlay;
	private JButton btnKickPlayer;
	private JLabel lbIdRoom;
	private JLabel lbQtyPlayer;
	
	public RoomView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnKickPlayer = new JButton("Đuổi khỏi phòng");
		btnKickPlayer.setVisible(false);
		btnKickPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnKickPlayer.setBounds(410, 113, 130, 23);
		contentPane.add(btnKickPlayer);
		
		btnPlay = new JButton("Bắt đầu");
		btnPlay.setVisible(false);
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPlay.setBounds(410, 63, 130, 23);
		contentPane.add(btnPlay);
		
		btnOutRoom = new JButton("Rời phòng");
		btnOutRoom.setBounds(444, 427, 130, 23);
		contentPane.add(btnOutRoom);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 347, 414);
		contentPane.add(scrollPane);
		
		list = new JList<String>();
		scrollPane.setViewportView(list);
		
		lbQtyPlayer = new JLabel("Số người trong phòng: ");
		lbQtyPlayer.setBounds(10, 11, 183, 14);
		contentPane.add(lbQtyPlayer);
		
		lbIdRoom = new JLabel("Phòng ");
		lbIdRoom.setBounds(444, 11, 77, 14);
		contentPane.add(lbIdRoom);
	}

	public JList<String> getList() {
		return list;
	}

	public JButton getBntJoinRoom() {
		return btnOutRoom;
	}

	public JButton getBtnPlay() {
		return btnPlay;
	}

	public JLabel getLbIdRoom() {
		return lbIdRoom;
	}

	public JLabel getLbQtyPlayer() {
		return lbQtyPlayer;
	}

	public JButton getBtnKickPlayer() {
		return btnKickPlayer;
	}
}
