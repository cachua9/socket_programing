package client.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GeneralView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GeneralView frame = new GeneralView();
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
	public GeneralView() {
		setTitle("Ai là triệu phú");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
	}
	@Override
	public Container getContentPane() {
		// TODO Auto-generated method stub
		return super.getContentPane();
	}
	
	public void switchToMe(JFrame jFrame) {
		jFrame.setLocation(getLocation());
		getContentPane().removeAll();
		getContentPane().add(jFrame.getContentPane());
		getContentPane().revalidate();
		getContentPane().repaint();
		setBounds(jFrame.getBounds());		
	}

}
