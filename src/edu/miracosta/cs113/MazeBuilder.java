package edu.miracosta.cs113;

import java.util.ArrayList;
import java.util.Random;

public class MazeBuilder 
{
	
	/*** Data Fields ***/ 
	
	public static final int ROW = 0;
	public static final int COLUMN = 1;
	
	public static int northProb = 15;
	public static int eastProb = 50;
	public static int southProb = 85; //westProb is assumed 100 - north + east + south 
	
	public static int size = 9;
	public static int pathLength = 3;
	
	
	/*** Methods ***/
	
	/**
	 * Generates a 2d array filled with int 1 values representing blocked areas of the maze
	 * @return int[][] maze array
	 */
	public static int[][] generateMaze()
	{
		int[][] maze = new int[size][size];

		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				maze[i][j] = 1;
			}
		}
		
		return maze;
	}
	
	/**
	 * Returns a 2d array with paths to an exit
	 * @param endPosition exit location of maze
	 * @return 2d solvable array maze 
	 * @throws Exception index exception 
	 */
	public static int[][] generateSolvableMaze(int[] endPosition) throws Exception
	{
		int[][] maze = generateMaze();
		createPath(maze, endPosition);
		createPath(maze, endPosition);
		createPath(maze, endPosition);

		return maze;
	}
	
	/**
	 * Creates path in 2d maze array
	 * @param maze 2d array
	 * @param endPosition Position to exit maze
	 * @throws Exception if error in finishing path to end
	 */
	public static void createPath(int[][]maze, int[] endPosition) throws Exception
	{
		int[] position = {0, 0};
		setMazePos(maze, position);
		
		while((position[ROW] != endPosition[ROW]) || (position[COLUMN] != endPosition[COLUMN]))
		{
			fillPath(maze, position);
		}
				
		finishPathToEnd(maze, position, endPosition);
	}
	
	/**
	 * Fills in path to end 
	 * @param maze 2d maze array
	 * @param position current position in the maze
	 */
	private static void fillPath(int[][] maze, int[] position)
	{
		int direction = getRandomNum(0, 100);
		if(direction < northProb)
		{	
			addNorth(maze, position);
		}
		else if(direction >= northProb && direction < eastProb)
		{
			addEast(maze, position);
		}
		else if(direction >= eastProb && direction < southProb)
		{
			addSouth(maze, position);
		}
		else
		{
			addWest(maze, position);
		}
	}
	
	/**
	 * Finishes path to exit once it either row or column matches the exit position row or column
	 * @param maze 2d array
	 * @param position current position in maze
	 * @param endPosition exit position in maze
	 * @throws Exception error with finishing path to exit
	 */
	private static void finishPathToEnd(int[][] maze,int[] position, int[] endPosition) throws Exception
	{
		if(position[ROW] == endPosition[ROW])
		{
			while(position[COLUMN] != endPosition[COLUMN])
			{
				position[COLUMN] += 1;
				setMazePos(maze, position);
			}
		}
		else if(position[COLUMN] == endPosition[COLUMN])
		{
			while(position[ROW] != endPosition[ROW])
			{
				position[COLUMN] += 1;
				setMazePos(maze, position);
			}
		}
		else
		{
			throw new Exception("ERROR WITH FINISH PATH TO END");
		}
	}
	
	/**
	 * Creates path in the northern direction by changing values in maze to 0
	 * @param maze 2d array
	 * @param position current position in maze
	 */
	private static void addNorth(int[][] maze, int[] position)
	{
		int temp = pathLength;
		while((temp > 1) && (position[ROW] != 0))
		{
			position[ROW] -= 1;
			temp--;
			setMazePos(maze, position);
		}
	}
	
	/**
	 * Creates path in the southern direction by changing values in maze to 0
	 * @param maze 2d array
	 * @param position current position in maze
	 */
	private static void addSouth(int[][] maze, int[] position)
	{
		int temp = pathLength;
		while((temp > 1) && (position[ROW] != maze.length - 1))
		{
			position[ROW] += 1;
			temp--;
			setMazePos(maze, position);
		}
		
	}
	
	/**
	 * Creates path in the eastern direction by changing values in maze to 0
	 * @param maze 2d array
	 * @param position current position in maze
	 */
	private static void addEast(int[][] maze, int[] position)
	{
		int temp = pathLength;
		while((temp > 1) && (position[COLUMN] != maze.length - 1))
		{
			position[COLUMN] += 1;
			temp--;
			setMazePos(maze, position);
		}
	}
	/**
	 * Creates path in the west direction by changing values in maze to 0
	 * @param maze 2d array
	 * @param position current position in maze
	 */
	private static void addWest(int[][] maze, int[] position)
	{
		int temp = pathLength;
		while((temp > 1) && (position[COLUMN] != 0))
		{
			position[COLUMN] -= 1;
			temp--;
			setMazePos(maze, position);
		}
	}
	
	/**
	 * Helper method sets position to 0 in maze 
	 * @param maze 2d array
	 * @param position current position in maze
	 */
	private static void setMazePos(int maze[][], int[] position)
	{
		setMazePos(maze, position, 0);
	}
	
	/** 
	 * Sets position to value in maze
	 * @param maze 2d array
	 * @param position current position in maze
	 * @param value 
	 */
	private static void setMazePos(int maze[][], int position[], int value)
	{
		maze[position[ROW]][position[COLUMN]] = value;
	}
	
	/** 
	 * Prints maze to console 
	 * @param maze 2d Array
	 */
	public static void printMaze(int[][] maze)
	{
		System.out.println();
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				System.out.print(maze[i][j] + ", ");
			}
			System.out.println();
		}
	}
	
	
	/*** Misc. ***/
	
	/**
	 * Returns a random number between min and max, both inclusive
	 * @param min Smallest possible value.
	 * @param max Largest possible value.
	 * @return Random number n, such that min <= n <= max
	*/
	private static int getRandomNum(int min, int max)
	{
	     Random rand = new Random();     
	     return rand.nextInt(max) + min;	
	}
	
	/**
	 * Fills the shortest path that solves the maze by setting values to 2
	 * @param maze 2d array
	 * @param path contains shortest path in order of Vertex
	 */
	public static void fillShortestPath(int[][] maze, ArrayList<Vertex> path)
	{
		int[] currentPos = {0, 0};
		int[] nextPos = new int[2];
		int direction = 0;
		
		for(int i = 0; i < path.size() - 1; i++)
		{
			if(i != 0)
			{
				currentPos[ROW] = path.get(i).getPoint().getRow();
				currentPos[COLUMN] =  path.get(i).getPoint().getCol();
			}
			
			nextPos[ROW] = path.get(i + 1).getPoint().getRow();
			nextPos[COLUMN] =  path.get(i + 1).getPoint().getCol();
			
			if((currentPos[ROW] == nextPos[ROW]))
			{
				direction = currentPos[COLUMN] - nextPos[COLUMN];
				if(direction > 0)
				{
					while(direction > 0)
					{
						currentPos[COLUMN] -= 1;
						direction--;
						setMazePos(maze, currentPos, 2);
					}
				}
				else
				{
					while(direction < 0)
					{
						currentPos[COLUMN] += 1;
						direction++;
						setMazePos(maze, currentPos, 2);
					}
				}
			}
			
			else if((currentPos[COLUMN] == nextPos[COLUMN]))
			{
				direction = currentPos[ROW] - nextPos[ROW];
				if(direction > 0)
				{
					while(direction > 0)
					{
						currentPos[ROW] -= 1;
						direction--;
						setMazePos(maze, currentPos, 2);
					}
				}
				else
				{
					while(direction < 0)
					{
						currentPos[ROW] += 1;
						direction++;
						setMazePos(maze, currentPos, 2);
					}
				}
			}
		}
			
	}
	
	
	
	
}
