package client.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.controller.ClientMain;
import client.controller.RoomController;
import client.object.Room;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class SelectRoom extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectRoom frame = new SelectRoom();
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
	
	public SelectRoom() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRefreshRooms = new JButton("Làm mới");
		btnRefreshRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClientMain.connection.Send("refreshrooms");
			}
		});
		btnRefreshRooms.setBounds(418, 119, 110, 23);
		contentPane.add(btnRefreshRooms);
		
		JButton btnCreateRoom = new JButton("Tạo phòng");
		btnCreateRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RoomController.createRoom();
			}
		});
		btnCreateRoom.setBounds(418, 65, 110, 23);
		contentPane.add(btnCreateRoom);
		
		JButton bntJoinRoom = new JButton("Vào phòng");
		bntJoinRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedIndex() < 0) {
					JOptionPane.showMessageDialog(null, "Hãy chọn phòng để tham gia");
				}
				else RoomController.joinRoom(list.getSelectedIndex());
			}
		});
		bntJoinRoom.setBounds(418, 175, 110, 23);
		contentPane.add(bntJoinRoom);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 347, 439);
		contentPane.add(scrollPane);
		
		list = new JList<String>();
		scrollPane.setViewportView(list);
	}

	public JList<String> getList() {
		return list;
	}
}
