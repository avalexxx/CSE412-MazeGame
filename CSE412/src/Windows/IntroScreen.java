package Windows;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;


public class IntroScreen {

	private static JFrame frame;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IntroScreen window = new IntroScreen();
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
	public IntroScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 389, 382);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Sign In");
		
		initLabels();
		initButtons();
		
		JList userList = new JList();
		userList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		userList.setBounds(26, 52, 318, 192);
		frame.getContentPane().add(userList);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(93, 291, 132, 21);
		frame.getContentPane().add(passwordField);
		
	
		
	}
	
	private static void initLabels()
	{
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword.setBounds(26, 289, 68, 22);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblWelcome = new JLabel("Welcome To The Maze Game!");
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblWelcome.setBounds(64, 11, 262, 22);
		frame.getContentPane().add(lblWelcome);
	}
	
	private static void initButtons()
	{
		JButton btnCreateProfile = new JButton("Create New Profile");
		btnCreateProfile.setBounds(26, 255, 199, 23);
		frame.getContentPane().add(btnCreateProfile);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(235, 290, 109, 23);
		frame.getContentPane().add(btnLogin);
		
		JButton btnHelp = new JButton("Help");
		btnHelp.setBounds(235, 255, 109, 23);
		frame.getContentPane().add(btnHelp);
		
		
	}
}
