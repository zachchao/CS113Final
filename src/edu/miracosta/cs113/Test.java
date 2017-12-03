package edu.miracosta.cs113;

import java.util.HashSet;

public class Test {
	private static int size = 9;
	private static int[][] maze;
	
	public static void main(String[] args){
		maze = new int[size][size];
		
		//Test maze
		/*
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
		*/
		/*
				          // 0 1 2 3 4 5 6 7 8 9
		maze[0] = new int[] {0,0,0,0,0,0,0,0,0,0};
		maze[1] = new int[] {1,1,1,1,1,1,1,1,1,0};
		maze[2] = new int[] {1,1,1,1,1,1,0,0,1,0};
		maze[3] = new int[] {1,0,1,0,1,1,1,1,1,0};
		maze[4] = new int[] {1,0,0,0,1,1,1,1,1,0};
		maze[5] = new int[] {1,1,1,0,0,0,0,0,1,0};
		maze[6] = new int[] {1,1,1,1,0,1,1,1,1,0};
		maze[7] = new int[] {1,1,1,1,0,1,1,1,0,0};
		maze[8] = new int[] {1,1,1,1,1,1,0,0,0,1};
		*/
		
		        		  // 0 1 2 3 4 5 6 7 8 9
		maze[0] = new int[] {0,0,0,0,0,0,0,0,0,0};
		maze[1] = new int[] {1,1,1,1,1,1,1,1,1,0};
		maze[2] = new int[] {1,1,1,1,1,1,1,1,1,0};
		maze[3] = new int[] {1,1,1,1,1,1,1,1,1,0};
		maze[4] = new int[] {1,1,1,1,1,1,1,1,1,0};
		maze[5] = new int[] {1,1,1,1,0,0,0,1,1,0};
		maze[6] = new int[] {1,1,1,1,1,1,1,1,1,0};
		maze[7] = new int[] {1,1,1,1,1,1,1,1,0,0};
		maze[8] = new int[] {1,1,1,1,1,1,1,1,0,1};
		
		Vertex start = new Vertex(new Point(0,0));
		
		
		MatrixToGraph converter = new MatrixToGraph(maze);
		Graph graph = converter.getGraph();
		graph.shortestPath(start);
		
		
		Vertex test = new Vertex(new Point(0,0));
		
		
	}
	
	public static void print(Object o){
		System.out.println(o);
	}
}
