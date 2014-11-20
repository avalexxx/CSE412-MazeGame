// This class implements a JFrame which will display a Maze (written to combine walls and data)
package Maze;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;    //for JOptionPane

public class MazeDisplay extends JFrame implements Runnable
{
	//------------ constants
	private final int START_WIDTH = 700;
	private final int START_HEIGHT = 500;
	private final int ANIMATIONDELAY = 0;	//Animation display rate (in milliseconds), so 20fps

	//------------ data
	private int cellDim;
	private int[ ][ ] mazeArray;
	private int numArrayRows;
	private int numArrayCols;

	private Graphics g;
	private Thread animationThread;
	private Insets insets;
	static int count=0;

	//-----------  constructor(s)
	// Parameterized constructor which receives an int that is the landscapeID
	public MazeDisplay(Maze aMaze, String mazeTitle)
	{
		//if aMaze is null, throw an exception
		if (aMaze == null)
			throw new IllegalArgumentException("trying to create a MazeDisplay with a null Maze");

		//store the reference to aMaze's mazeArray to be used in the display
		mazeArray = aMaze.getMazeArray();

		//isolate the number of rows and the number of columns in the mazeArray
		numArrayRows = mazeArray.length;
		numArrayCols = mazeArray[0].length;

		//get the number of "real" rows and cols tht the Maze has
		int numRealRows = numArrayRows/2;
		int numRealCols= numArrayCols/2;

		//calculate the optimum size of one cell
		int calcWidth = START_WIDTH/numRealCols;
		int calcHeight = START_HEIGHT/numRealRows;
		cellDim = Math.min(calcWidth, calcHeight);

		//but if the cellDim is < 2 pixels, then it is too small to draw
		if (cellDim < 2)
			throw new IllegalArgumentException("maze has too many rows/cols to draw");

		//set the JFrame attributes
		setTitle(mazeTitle);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(numRealCols*cellDim+7,numRealRows*cellDim+1+28); //add one for last border, subtract 38 for title bar, borders
		center();
		setResizable(false);
		setVisible(true);

		// use those values to determine the greeny and the orangey
		insets = getInsets();

		// add a WindowListener
		addWindowListener(new WindowAdapter()
						  {public void windowClosing(WindowEvent e)
						  { System.exit(0);}});

		// get the Graphics object that will be used to write to this JFrame;
		g = getGraphics();

		// Anonymous inner class window listener to terminate the program.
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		}
		);

		// Create and start animation thread
		animationThread = new Thread(this);
		animationThread.start();

	}

	//-----------  methods(s)

	// run will actually run this Frame
	public void run()
	{
		//Loop, sleep, and update sprite positions once each ANIMATIONDELAY milliseconds
		long time = System.currentTimeMillis();
		while (true) //infinite loop
		{
			paint(g);
			try
			{
				time += ANIMATIONDELAY;
				Thread.sleep(Math.max(0,time - System.currentTimeMillis()));
			}
			catch (InterruptedException e)
			{
				System.out.println(e);
			}//end catch
		}//end while loop
	}//end run method

	// center - will set the x and y of this Frame to the center of the screen
	private void center()
	{
		Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension FrameSize = this.getSize();
		int x = (ScreenSize.width - FrameSize.width)/2;
		int y = (ScreenSize.height - FrameSize.height)/2;
		this.setLocation(x, y);
	}

	//------------------------------------------------
	public void windowGainedFocus(WindowEvent e)
	{
		repaint();
	}

	//------------------------------------------------
	public void windowLostFocus(WindowEvent e)
	{
		repaint();
	}

	//------------------------------------------------
	public void componentResized(ComponentEvent e)
	{
		repaint();
	}

	//------------------------------------------------
	public void componentMoved(ComponentEvent e)
	{
		repaint();
	}

	//------------------------------------------------
	public void componentShown(ComponentEvent e)
	{
		repaint();
	}

	//------------------------------------------------
	public void componentHidden(ComponentEvent e)
	{
		//repaint();
	}

	//------------------------------------------------
	public void update(Graphics g)
	{
		repaint();
	}



	//------------------------------------------------
	public void actionPerformed(ActionEvent e)
	{
	}

	// paint - repaints the whole Maze.  Uses "double-buffering" to eliminate flickering.
	public void paint(Graphics g)
	{
		// create an image - we will "double buffer" (draw to that image first and then
		// draw the image) to eliminate flickering
		Image image = createImage(getWidth(), getHeight());
    	Graphics graphicsBuffer = image.getGraphics();

    	// fill the image with the background color
    	//graphicsBuffer.setColor(Color.WHITE);
       	//graphicsBuffer.fillRect(12, 12, getWidth()-24, getHeight()-24);


		graphicsBuffer.setColor(Color.GREEN);

		for (int row=0; row<numArrayRows; row++)
			for (int col=0; col<numArrayCols;col++)
			{
				if (row%2==1 && col%2==1 && mazeArray[row][col] == 3)    //odd rows, odd cols are the actual cells
				{
					graphicsBuffer.setColor(Color.BLUE);   //goal
					int startx = insets.left + col/2 * cellDim;
					int starty = insets.top + row/2 * cellDim;
					graphicsBuffer.fillRect(startx, starty, cellDim, cellDim);
				}
				if (row%2==1 && col%2==1 && mazeArray[row][col] == 2)    //odd rows, odd cols are the actual cells
				{
					graphicsBuffer.setColor(Color.RED);   //current
					int startx = insets.left + col/2 * cellDim;
					int starty = insets.top + row/2 * cellDim;
					graphicsBuffer.fillRect(startx, starty, cellDim, cellDim);
				}
				else if (row%2==1 && col%2==1 && mazeArray[row][col] == 0)    //odd rows, odd cols are the actual cells
				{
					graphicsBuffer.setColor(randomColor());   //current
					int startx = insets.left + col/2 * cellDim;
					int starty = insets.top + row/2 * cellDim;
					graphicsBuffer.fillRect(startx, starty, cellDim, cellDim);
				}
			}

		graphicsBuffer.setColor(Color.MAGENTA);

		for (int row=0; row<numArrayRows; row++)
			for (int col=0; col<numArrayCols;col++)
			{
				if (row%2==0 && col%2==1 && mazeArray[row][col] == 1)    //even rows, odd cols are the horizontal walls
				{
					int startx = insets.left + col/2 * cellDim;
					int endx = insets.left + col/2 * cellDim + cellDim;
					int starty = insets.top + row/2 * cellDim;
					int endy = insets.top + row/2 * cellDim;
					graphicsBuffer.drawLine(startx, starty, endx, endy);
				}

				else if (row%2==1 && col%2==0 && mazeArray[row][col] == 1)    //odd rows, even cols are the vertical walls
				{
					int startx = insets.left + col/2 * cellDim;
					int endx = insets.left + col/2 * cellDim;
					int starty = insets.top + row/2 * cellDim;
					int endy = insets.top + row/2 * cellDim + cellDim;
					graphicsBuffer.drawLine(startx, starty, endx, endy);
				}
			}

		// copy the image to the actual Frame
		g.drawImage(image, 0, 0, Color.WHITE, null);

		repaint();


	}
	static int red=1;
	static int blue =1;
	static int green = 1;
	static boolean goBackFlag = false;
	static int[] colorArray = {red,blue,green};
	static int colorI = 0;
	
	public static Color randomColor()
	{
		/**
		int R = (int)(Math.random()*256);
		int G = (int)(Math.random()*256);
		int B= (int)(Math.random()*256);
		Color color = new Color(R, G, B);
		*/
		
	
		count++;
		
		if(colorArray[colorI]==255&&!goBackFlag)
		{
			goBackFlag=true;
		}
		else if(colorArray[colorI]==0&&goBackFlag)
		{
			goBackFlag=false;
			colorArray[colorI]=1;
			
			if(colorI==colorArray.length-1)
				colorI=0;
			else
				colorI++;
		}
		if(goBackFlag&&count%100==0)
		{
			colorArray[colorI]--;
		}
		else if(!goBackFlag&&count%100==0)
		{
			colorArray[colorI]++;
		}
		if(colorI==0)
			return new Color(colorArray[colorI],0,0);
		else if(colorI==1)
			return new Color(0,colorArray[colorI],0);
		else 
			return new Color(0,0,colorArray[colorI]);
	}
 
}


