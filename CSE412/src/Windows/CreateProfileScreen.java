package Windows;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.util.Random;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;


public class CreateProfileScreen {

	private static JFrame frame;
	private JTable table;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateProfileScreen window = new CreateProfileScreen();
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
	public CreateProfileScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Create Profile");
		frame.setBounds(100, 100, 652, 298);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		ImageIcon smiley = new ImageIcon("evil-smiley-face.png");
		ImageIcon messed = new ImageIcon("Messed-up-smiley.jpg");
		Object[][] data = 
		{
				{smiley, messed},{smiley, smiley},{smiley, smiley},{smiley, smiley},{smiley, messed},{smiley, messed},{smiley, messed}
		};
		String[] columnThing = {"",""};
		DefaultTableModel model = new DefaultTableModel(data,columnThing);
		
		
		table = new JTable(model){
			 public Class getColumnClass(int column)
	            {
	                return getValueAt(0, column).getClass();
	            }
		};
		JScrollPane test = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		test.setBounds(24, 11, 294, 228);
		table.setRowHeight(100);
		

		table.setBounds(20, 34, 310, 292);
		frame.getContentPane().add(test);
		
		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setBounds(340, 23, 73, 20);
		frame.getContentPane().add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(340, 160, 104, 14);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblReenterPassword = new JLabel("Re-Enter \r\nPassword:");
		lblReenterPassword.setBounds(340, 185, 122, 20);
		frame.getContentPane().add(lblReenterPassword);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(472, 54, 104, 79);
		frame.getContentPane().add(panel);
		
		JLabel lblIcon = new JLabel("Select Icon:");
		lblIcon.setBounds(340, 89, 104, 14);
		frame.getContentPane().add(lblIcon);
		
		textField_2 = new JTextField();
		textField_2.setBounds(472, 23, 132, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(472, 157, 132, 20);
		frame.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(472, 185, 132, 20);
		frame.getContentPane().add(passwordField_1);
		
		JButton btnCreateProfile = new JButton("Create Profile");
		btnCreateProfile.setBounds(340, 216, 122, 23);
		frame.getContentPane().add(btnCreateProfile);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(472, 216, 122, 23);
		frame.getContentPane().add(btnCancel);

		
	}
}
