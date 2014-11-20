package Maze;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JToggleButton;
import javax.swing.JSlider;

public class EndMazeDisplay extends JFrame {

	private JPanel contentPane;
	public EndMazeDisplay(String mazeResult, String courseTime, String recordTime,  Maze maze) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 315, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Retry?");
		btnNewButton.setBounds(20, 237, 119, 31);
		contentPane.add(btnNewButton);
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.setBounds(164, 237, 119, 31);
		contentPane.add(btnMainMenu);
		
		JLabel lblYourTime = new JLabel("Your Time:");
		lblYourTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblYourTime.setBounds(35, 114, 119, 22);
		contentPane.add(lblYourTime);
		
		JLabel lblRecordTime = new JLabel("Record Time:");
		lblRecordTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRecordTime.setBounds(35, 147, 104, 22);
		contentPane.add(lblRecordTime);
		
		JLabel lblNewLabel = new JLabel(courseTime);
		lblNewLabel.setBounds(164, 117, 119, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(recordTime);
		lblNewLabel_1.setBounds(164, 147, 119, 22);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(mazeResult, SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 46));
		lblNewLabel_2.setBounds(10, 0, 293, 133);
		contentPane.add(lblNewLabel_2);
		
		JSlider slider = new JSlider(0,5,JSlider.HORIZONTAL);
		slider.setBounds(164, 180, 109, 45);
		contentPane.add(slider);
		slider.setMajorTickSpacing(1);
		slider.setMinorTickSpacing(0);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		
		JLabel lblRateThisMaze = new JLabel("Rate This Maze:");
		lblRateThisMaze.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRateThisMaze.setBounds(35, 180, 129, 31);
		contentPane.add(lblRateThisMaze);
		

	}
}
