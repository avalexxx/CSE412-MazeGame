package Windows;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Canvas;


public class MazeSelectionScreen {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MazeSelectionScreen window = new MazeSelectionScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MazeSelectionScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 490, 232);
		frame.getContentPane().setLayout(null);
		
		JList list = new JList();
		list.setBounds(20, 21, 264, 128);
		frame.getContentPane().add(list);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.setBounds(20, 160, 127, 23);
		frame.getContentPane().add(btnPlay);
		
		JButton btnPreview = new JButton("Preview");
		btnPreview.setBounds(157, 160, 127, 23);
		frame.getContentPane().add(btnPreview);
		
		JButton btnNewButton = new JButton("Sign Out");
		btnNewButton.setBounds(294, 160, 165, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnViewAchievements = new JButton("View Achievements");
		btnViewAchievements.setBounds(294, 126, 165, 23);
		frame.getContentPane().add(btnViewAchievements);
		
		JLabel lblDifficulty = new JLabel("Difficulty:");
		lblDifficulty.setBounds(294, 22, 61, 14);
		frame.getContentPane().add(lblDifficulty);
		
		textField = new JTextField();
		textField.setBounds(387, 19, 45, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		Canvas canvas = new Canvas();
		canvas.setBounds(510, 10, 97, 84);
		frame.getContentPane().add(canvas);
		
		JLabel lblAverage = new JLabel("Avg. Rating:");
		lblAverage.setBounds(295, 47, 81, 14);
		frame.getContentPane().add(lblAverage);
		
		textField_1 = new JTextField();
		textField_1.setBounds(387, 44, 45, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblRecordTime = new JLabel("Record Time:");
		lblRecordTime.setBounds(294, 72, 82, 14);
		frame.getContentPane().add(lblRecordTime);
		
		textField_2 = new JTextField();
		textField_2.setBounds(387, 69, 72, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(387, 95, 72, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblYourTime = new JLabel("Your Time:");
		lblYourTime.setBounds(294, 98, 82, 14);
		frame.getContentPane().add(lblYourTime);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
