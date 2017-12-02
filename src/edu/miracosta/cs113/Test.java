package edu.miracosta.cs113;

import java.util.HashSet;

public class Test {
	private static int size = 9;
	private static int[][] maze;
	
	public static void main(String[] args){
		maze = new int[size][size];
						  // 0 1 2 3 4 5 6 7 8 9
		maze[0] = new int[] {0,1,1,0,1,1,1,1,1,1};
		maze[1] = new int[] {0,0,0,0,0,0,0,1,1,1};
		maze[2] = new int[] {1,1,1,0,1,1,0,0,1,1};
		maze[3] = new int[] {1,0,1,0,1,1,1,1,1,1};
		maze[4] = new int[] {1,0,0,0,1,1,1,1,1,1};
		maze[5] = new int[] {1,1,1,0,0,0,0,0,1,1};
		maze[6] = new int[] {1,1,1,1,0,1,1,1,1,1};
		maze[7] = new int[] {1,1,1,1,0,0,1,1,1,1};
		maze[8] = new int[] {1,1,1,1,1,0,0,0,0,1};
		
		
		MatrixToGraph converter = new MatrixToGraph(maze);
		//print(converter.isVertex(8,7));
		HashSet<Point> verticies = converter.getAllVertecies();
		for(Point v : verticies){
			print(v);
			converter.findEdges(v.getRow(), v.getCol());
		}
		//converter.findEdges(1,0);
	}
	
	public static void print(Object o){
		System.out.println(o);
	}
}
