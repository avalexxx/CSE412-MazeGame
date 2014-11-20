package Maze;

import java.awt.Font;
import java.awt.event.*;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class RunMaze {
	static Maze maze;
	static int row;
	static int col;
	static boolean[][] visited;
	static Maze.Direction whichWay;
	static Stack<Maze.Direction> history ;
	
	public static void main(String[] args) {
	
		//maze row and column size
		row=20;
		col=20;
		
		//testing for taking in an input maze
		Maze inMaze = new Maze(row,col);
		inMaze.buildMaze(1);
		inMaze.setSolveAnimationDelay(10);
		
		//actual maze that is ran
		maze = new Maze(inMaze);
	
		//set up maze display
		MazeDisplay display = new MazeDisplay(maze, "Maze Test 101");
		display.setLocationRelativeTo(null);
		
		// run ready set go
		introPrompt();
		
		//set up for getting user input
		display.addKeyListener(new KeyAdapter() 
	    {
	        public void keyPressed(KeyEvent evt)
	        {
	        	//get input
	        	switch (evt.getKeyCode())
	        	{
	        		//if user choses direction
		        	case KeyEvent.VK_UP:
		        	{
		        		if(maze.open(Maze.Direction.UP))
		            		maze.move(Maze.Direction.UP);
		            	 break;
		        	}
		        	
		        	case KeyEvent.VK_DOWN:
		        	{
		        		if(maze.open(Maze.Direction.DOWN))
		            		maze.move(Maze.Direction.DOWN);
		            	break;
		        	}
		        	
		        	case KeyEvent.VK_LEFT:
		        	{
		        		if(maze.open(Maze.Direction.LEFT))            	
		            		maze.move(Maze.Direction.LEFT);
		        		break;
		        	}
		        	case KeyEvent.VK_RIGHT:
		        	{
		        		if(maze.open(Maze.Direction.RIGHT))            		
	            			maze.move(Maze.Direction.RIGHT);
	            		break;
		        	}
		        	
		        	//if user decids to solve
		        	case KeyEvent.VK_S:
		        	{
		        		solveMaze();
		        		
		        		//let the user see the path for a few secs
		        		try { 
							Thread.sleep(500);
						} catch (InterruptedException e) {
							
							e.printStackTrace();
						}
		        		
		        		//display final screen
		            	EndMazeDisplay temp = new EndMazeDisplay("time", "recordTime", "LOSER!!!", maze);
		            	temp.setVisible(true);
		            	display.setVisible(false);
		            	break;
		        	}
		        	
		        	//if user decides to quit
		        	case KeyEvent.VK_Q:
		        	{
		        		
		            	EndMazeDisplay temp = new EndMazeDisplay("time", "recordTime", "LOSER!!!", maze);
		            	temp.setVisible(true);
		            	display.setVisible(false);
		            	break;
		        	}
	        	}
	        	
	        	// fix this section, it is displaying both winner and loser screen because this section is always hit
	        	// prolly take it out of actionListener
	        	if(maze.goalReached())
	            {
	            	try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
	            	EndMazeDisplay temp = new EndMazeDisplay("time", "recordTime", "WINNER!!!", maze);
	            	temp.setLocationRelativeTo(null);
	            	temp.setVisible(true);
	            	display.setVisible(false);
	            	
	            }
	        }
	    });
		//end setting getting user input
	}

	
	public static void solveMaze()
	{
		//This stack will hold the directions which the maze is going.
		history = new Stack<Maze.Direction>();
		
		//Mark the starting cell as visited
		visited = new boolean[row][col];
		
		//This for loop goes through the 2D Array visited and sets all of the values equal to false.
		//Once the maze has been to a location the data will change from false to true.
		for(int i = 0; i < row; i++)
			for(int j = 0; j < col; j++)
				visited[i][j] = false;
						
		//Mark the cell that has been visited
		visited[maze.getCurrentRow()][maze.getCurrentCol()] = true;
		while(maze.goalReached() != true)
		{		
			//Detects if the maze is open upwards.  If it is then the maze moves that direction.
			if(maze.open(Maze.Direction.UP) == true && visited[maze.getCurrentRow()-1][maze.getCurrentCol()] == false)
			{
				maze.move(Maze.Direction.UP);
				visited[maze.getCurrentRow()][maze.getCurrentCol()] = true;
				whichWay = Maze.Direction.UP;
				history.push(whichWay);	
			}
			//Detects if the maze is open downwards.  If it is then the maze moves that direction.
			else if(maze.open(Maze.Direction.DOWN) == true && visited[maze.getCurrentRow()+1][maze.getCurrentCol()] == false)
			{
				maze.move(Maze.Direction.DOWN);
				visited[maze.getCurrentRow()][maze.getCurrentCol()] = true;
				whichWay = Maze.Direction.DOWN;
				history.push(whichWay);
			}
			//Detects if the maze is open to the left.  If it is then the maze moves that direction.
			else if(maze.open(Maze.Direction.LEFT) == true && visited[maze.getCurrentRow()][maze.getCurrentCol()-1] == false)
			{
				maze.move(Maze.Direction.LEFT);
				visited[maze.getCurrentRow()][maze.getCurrentCol()] = true;
				whichWay = Maze.Direction.LEFT;				
				history.push(whichWay);
			}
			//Detects if the maze is open to the right.  If it is then the maze moves that direction.
			else if(maze.open(Maze.Direction.RIGHT) && visited[maze.getCurrentRow()][maze.getCurrentCol()+1] == false)
			{
				maze.move(Maze.Direction.RIGHT);
				visited[maze.getCurrentRow()][maze.getCurrentCol()] = true;
				whichWay = Maze.Direction.RIGHT;
				history.push(whichWay);
			}
			//This part of the code is designed to help the maze "BACKTRACK" by popping the stack.
			//=======================================================================================================
			else if(maze.open(Maze.Direction.UP) == true && history.peek() == Maze.Direction.DOWN)
			{
				maze.move(Maze.Direction.UP);
				history.pop();
			}
			//Detects if the maze is open downwards.  If it is then the maze moves that direction.
			else if(maze.open(Maze.Direction.DOWN) == true && history.peek() == Maze.Direction.UP)
			{
				maze.move(Maze.Direction.DOWN);
				history.pop();			
			}
			//Detects if the maze is open to the left.  If it is then the maze moves that direction.
			else if(maze.open(Maze.Direction.LEFT) == true && history.peek() == Maze.Direction.RIGHT)
			{
				maze.move(Maze.Direction.LEFT);
				history.pop();
			}
			//Detects if the maze is open to the right.  If it is then the maze moves that direction.
			else
			{
				maze.move(Maze.Direction.RIGHT);
				history.pop();
			}
		}
	}
	
	public static void introPrompt()
	{
		JFrame introDis = new JFrame();
		introDis.setBounds(100, 100, 400, 200);
		introDis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		introDis.getContentPane().setLayout(null);
		introDis.setUndecorated(true);
		
		JLabel text = new JLabel("READY!", SwingConstants.CENTER);
		text.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 99));
		text.setBounds(0, 0, 400, 200);
		introDis.getContentPane().add(text);
		introDis.setLocationRelativeTo(null);
		introDis.setVisible(true);
		
		try {
			Thread.sleep(2000);
			
			text.setText("SET!");
			
			Thread.sleep(2000);
			
			text.setText("GO!");
			Thread.sleep(2000);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		introDis.dispose();
	}
}
