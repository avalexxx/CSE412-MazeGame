//Programmer <James McConkey>
//This program is designed to restore a maze from a saved file.

package Maze;
import java.util.*;
import java.io.*;

public class RestoreMaze 
{
	public static void main(String[] args) throws Throwable
	{
		//Create a new instance of a scanner class to get user input.
		Scanner KB = new Scanner(System.in);	
		
		//Create variable to hold the saved maze.
		Maze aMaze;
		
		//Ask the user what file the maze needs to be restored from.
		System.out.println("What file does the maze need to be restored from?");

		//Catch the user input and save that as the ObjectInputStream.
		ObjectInputStream oisVar = new ObjectInputStream(new FileInputStream(KB.nextLine()));
		
		//Reads the file as an object.  We have to typecast it to a maze.
		aMaze = (Maze)oisVar.readObject();
		
		//Create a new maze display passing in the maze that was saved.
		MazeDisplay display = new MazeDisplay(aMaze, "Test");
	}
}
