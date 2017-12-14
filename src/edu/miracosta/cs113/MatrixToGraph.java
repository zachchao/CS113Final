package edu.miracosta.cs113;

import java.util.HashSet;

/**
 * MatrixToGraph.java
 * 
 * Class Invariant: Uses 1s and 0s only, assumes rows are all the same length
 * 
 * @author Zachary Chao <zach.chao@yahoo.com>
 * @version 1.0
 * 
 * Algorithm - 
 * 	Isolates vertecies by finding splits, deadends and corners
 * 	Then from there constructs the edges and creates a graph
 */
public class MatrixToGraph {
	//0's are paths, 1's are boundaries
	private int[][] maze;
	//The size of our maze
	private int rows, cols;
	private Graph graph;
	
	/**
	 * Full argument constructor, constructs graph
	 * @param maze The 2d matrix maze
	 * @param testing Whether we are are testing and want test output or not
	 */
	public MatrixToGraph(int[][] maze, boolean testing){
		this.maze = maze;
		this.rows = maze.length;
		this.cols = maze[0].length;
		
		HashSet<Vertex> verticies = getVertecies();
		for(Vertex v : verticies){
			HashSet<Edge> edges = getEdges(v.getPoint().getRow(), v.getPoint().getCol(), testing);
			for(Edge e : edges){
				v.addConnection(e);
			}
		}
		this.graph = new Graph(verticies);
	}
	
	
	/**
	 * Getter for the graph
	 * @return A graph representation of the 2d matrix
	 */
	public Graph getGraph(){
		return this.graph;
	}
	
	/**
	 * Traverses array and if our isVertecie function
	 * returns true, adds it to a HashSet
	 * @return The HashSet of verticies
	 */
	public HashSet<Vertex> getVertecies(){
		HashSet<Vertex> verticies = new HashSet<Vertex>();
		for(int row = 0; row < rows; row++){
			for(int col = 0; col < cols; col++){
				if(isVertex(row, col)){
					verticies.add(new Vertex(new Point(row, col)));
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
	public HashSet<Edge> getEdges(int row, int col, boolean testing){
		HashSet<Edge> edges = new HashSet<Edge>();
		Vertex source = new Vertex(new Point(row, col));
		//Formatted [N, E, S, W]
		int[] adjacencies = adjacencies(row, col);
		
		//If an adjacencie is 0 then it is a path and there will 
		//Be a corresponding vertex
		
		//North path
		if(adjacencies[0] == 0){
			//We go until we hit another vertex
			for(int i = row - 1; i >= 0; i--){
				if(isVertex(i, col)){
					Vertex dest = new Vertex(new Point(i, col));
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
					Vertex dest = new Vertex(new Point(row, i));
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
					Vertex dest = new Vertex(new Point(i, col));
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
					Vertex dest = new Vertex(new Point(row, i));
					edges.add(new Edge(source, dest, col - i));
					break;
				}
			}
		}
		
		//Test output
		if(testing){
			//Testing
			System.out.println("Vert - " + source);
			for(Edge e : edges){
			System.out.println(e);
			}
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
		//If it is a wall then obviously not a vertex
		if(maze[row][col] == 1){
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
		
		//Check if it is a vertex
			//Corners				//Splits
		if((N != S || E != W) || (N + E + S + W != 2)){
			return true;
		}
		return false;
		
	}
	
	
}
