package edu.miracosta.cs113;

import java.util.HashSet;

/**
 * Assumes all rows are of same length
 * @author w7270821
 *
 */
public class MatrixToGraph {
	//0's are paths, 1's are boundaries
	private int[][] maze;
	//The size of our maze
	private int rows, cols;
	
	public MatrixToGraph(int[][] maze){
		this.maze = maze;
		this.rows = maze.length;
		this.cols = maze[0].length;
		//Testing
		//System.out.println("COLS " + this.cols + " ROWS " + this.rows);
	}
	
	/**
	 * Traverses array and if our isVertecie function
	 * returns true, adds it to a HashSet
	 * @return The HashSet of verticies
	 */
	public HashSet<Point> getAllVertecies(){
		HashSet<Point> verticies = new HashSet<Point>();
		for(int row = 0; row < rows; row++){
			for(int col = 0; col < cols; col++){
				if(isVertex(row, col)){
					verticies.add(new Point(row, col));
				}
			}
		}
		return verticies;
	}
	
	/**
	 * Given a verticie we need to know what the corresponding
	 * edges are with weights 
	 * Basic algorithm - 
	 * 		Given the available paths around the vertex, 
	 * 		traverse in that direction until you reach 
	 * 		the next vertex, the weight will be how far
	 * 		you traversed
	 * Invariant - Assumes what we are fed is already a vertex
	 * @param row The row of our initial vertex
	 * @param col The column of our initial vertex
	 */
	public HashSet<Edge> findEdges(int row, int col){
		HashSet<Edge> edges = new HashSet<Edge>();
		Point source = new Point(row, col);
		//Formatted [N, E, S, W]
		int[] adjacencies = adjacencies(row, col);
		
		//Testing
		/*
		for(int i : adjacencies){
			System.out.print(i + " ");
		}
		System.out.println("");
		*/
		
		//If an adjacencie is 0 then it is a path and there will 
		//Be a corresponding vertex
		
		//North path
		if(adjacencies[0] == 0){
			//We go until we hit another vertex
			for(int i = row - 1; i >= 0; i--){
				if(isVertex(i, col)){
					Point dest = new Point(i, col);
					edges.add(new Edge(source, dest, row - i));
					break;
				}
			}
		}
		//East Path
		if(adjacencies[1] == 0){
			//We go until we hit another vertex
			for(int i = col + 1; i < cols; i++){
				if(isVertex(row, i)){
					Point dest = new Point(row, i);
					edges.add(new Edge(source, dest, i - col));
					break;
				}
			}
		}
		//South path
		if(adjacencies[2] == 0){
			//We go until we hit another vertex
			for(int i = row + 1; i < rows; i++){
				if(isVertex(i, col)){
					Point dest = new Point(i, col);
					edges.add(new Edge(source, dest, i - row));
					break;
				}
			}
		}
		//West Path
		if(adjacencies[3] == 0){
			//We go until we hit another vertex
			for(int i = col - 1; i >= 0; i--){
				if(isVertex(row, i)){
					Point dest = new Point(row, i);
					edges.add(new Edge(source, dest, col - i));
					break;
				}
			}
		}
		
		//Testing
		for(Edge e : edges){
			System.out.println(e);
		}
		
		return edges;
	}
	
	/**
	 * Given a coordinate, give back what the values are of its
	 * adjacent points are
	 * For each coordinate we have to first test
	 * If we are out of bounds, if we are then automatically
     * We are considered a bound, aka 1, else, check if it is a bound
	 * @param row Row of the coordinate
	 * @param col Column of the coordinate
	 * @return Returns an array of format [N, E, S, W]
	 */
	public int[] adjacencies(int row, int col){
		//For readability, am well aware this can be anonymous and more succinct
		int N,E,S,W;
		N = E = S = W = 0;
		
		//North
		if(row-1 < 0 || maze[row-1][col] == 1){
			N = 1;
		}
		//East
		if(col + 1 >= cols  || maze[row][col+1] == 1){
			E = 1;
		}
		//South
		if(row + 1 >= rows || maze[row+1][col] == 1){
			S = 1;
		}
		//West
		if(col - 1 < 0 || maze[row][col-1] == 1){
			W = 1;
		}
		return new int[] {N, E, S, W};
	}
	
	/**
	 * Takes in a row and column of the matrix and returns
	 * whether or not it is a vertex in the graph
	 * a vertex is defined as being a decision point, aka
	 * having a change in direction
	 * @param row The row of the matrix
	 * @param col The column of the matrix
	 * @return Whether or not it is a vertex
	 */
	public boolean isVertex(int row, int col){
		//System.out.println(row + "," + col);
		//If it is a wall then obviously not a vertex
		if(maze[row][col] == 1){
			//System.out.println("Wall");
			return false;
		}
	
		//The four adjacent nodes, formatted as [N, E, S, W]
		int[] adjacencies = adjacencies(row, col);
		
		//Done for readability
		int N, E, S, W;
		N = adjacencies[0];
		E = adjacencies[1];
		S = adjacencies[2];
		W = adjacencies[3];
		
		//Testing
		/*
		System.out.println("N: " + N + " E: " + E + " S: " + S + " W: " + W);
		if(N != S || E != W){
			System.out.println("Corner");
		}
		if(N + E + S + W != 2){
			System.out.println("Split");
		}
		*/
		
		//Check if it is a vertex
			//Corners				//Splits
		if((N != S || E != W) || (N + E + S + W != 2)){
			return true;
		}
		return false;
		
	}
	
}
