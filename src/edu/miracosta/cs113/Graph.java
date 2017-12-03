package edu.miracosta.cs113;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * Graph.java
 * 
 * Class Invariant: Not generic
 * 
 * @author Zachary Chao <zach.chao@yahoo.com>
 * @version 1.0
 * 
 * Algorithm - 
 * 	Isolates vertecies by finding splits, deadends and corners
 * 	Then from there constructs the edges and creates a graph
 */
public class Graph{
	private HashSet<Vertex> verticies;
	private HashSet<Edge> edges;
	
	/**
	 * Constructor takes HashSet of verticies
	 * @param verticies The vertecies of the graph
	 */
	public Graph(HashSet<Vertex> verticies){
		setVerticies(verticies);
	}
	
	/**
	 * Uses Dijkstra's algorithm
	 * @param start Coordinate
	 * @param end Coordinate
	 * @param testing Whether or not we want test output
	 */
	public void shortestPath(Vertex start, Vertex end, boolean testing){
		HashSet<Vertex> processed = new HashSet<Vertex>();
		HashSet<Vertex> needToProcess = new HashSet<Vertex>();
		
		//Parallel arrays
		double[] shortestDistance = new double[verticies.size()];
		Vertex[] predecessor = new Vertex[verticies.size()];
		
		//Corresponds a vertex to an integer for the parallel arrays
		HashMap<Vertex, Integer> map = new HashMap<Vertex, Integer>();
		//Set our V-S
		needToProcess.addAll(verticies);	
		//Construct the mapping
		int index = 0;
		for(Vertex v : needToProcess){
			map.put(v, index);
			index += 1;
		}
		
		//All distances to infinity
		for(int i = 0; i < shortestDistance.length; i++){
			shortestDistance[i] = Double.POSITIVE_INFINITY;
		}
		
		//Distance to the start is 0
		shortestDistance[0] = 0;
		
		//While we still have verticies to process
		while(!needToProcess.isEmpty()){
			//Get the next vertex to process which will be the minimum distance  
			//within need to process
			Vertex vertexToProcess = null;
			double min = Double.POSITIVE_INFINITY;
			for(Vertex v : needToProcess){
				double weight = shortestDistance[map.get(v)];
				if(weight < min){
					vertexToProcess = v;
					min = weight;
				}
			}
			
			//If we are given nothing then all remaining options are infinity
			//In which case we are done
			if(vertexToProcess == null){
				break;
			}
		
			//Process it
			for(Edge edge : vertexToProcess.getConnections()){
				Vertex destination = edge.getDestination();
				Vertex source = edge.getSource();
				//Get the index of the destination and source in the parallel arrays
				int destinationIndex = map.get(destination);
				int sourceIndex = map.get(source);
				
				//Only process edges that lead to a vertex that has yet to be processed
				if(needToProcess.contains(destination)){
					//This distance
					double thisDistance = shortestDistance[sourceIndex] + edge.getWeight();
					//The current shortest distance to the destination vertex
					double currentShortestDistance = shortestDistance[destinationIndex];
					
					if(thisDistance < currentShortestDistance){
						shortestDistance[destinationIndex] = thisDistance;
						predecessor[destinationIndex] = edge.getSource();
					}
				}
				
				if(testing){
					//Testing
					System.out.println("Source: " + source +  " Destination: " + destination + " in need to process: " + needToProcess.contains(destination));
					System.out.printf("%-8s - %-50s\n", "S", processed.toString());
					System.out.printf("%-8s - %-50s\n", "V-S", needToProcess.toString());
					System.out.printf("%-34s", "**********************************\n");
					System.out.printf("* %-8s %-10s %-10s *\n", "v", "d[v]", "p[v]");
					int vertexIndex = 0;
					for(Vertex v : verticies){
						System.out.printf("* %-8s %-10s %-10s *\n", v, shortestDistance[vertexIndex], predecessor[vertexIndex]);
						vertexIndex += 1;
					}
					System.out.printf("%-34s", "**********************************\n\n");
				}
			}
			//Remove it
			needToProcess.remove(vertexToProcess);
			//Add it to the processed ones
			processed.add(vertexToProcess);
		}
		
		//Formatting the path to the end
		ArrayList<Vertex> path = new ArrayList<Vertex>();
		path.add(end);
		int position = map.get(end);
		while(position != 0){
			path.add(predecessor[position]);
			position = map.get(predecessor[position]);
		}
		//Reverse
		Stack<Vertex> stack = new Stack<Vertex>();
		for(Vertex v : path){
			stack.push(v);
		}
		//Empty path
		path = new ArrayList<Vertex>();
		while(!stack.isEmpty()){
			path.add(stack.pop());
		}
		
		System.out.print("Shortest path is " + path);
	}
	
	/**
	 * Getter for vertecies
	 * @return The vertecies
	 */
	public HashSet<Vertex> getVerticies() {
		return verticies;
	}

	/**
	 * Setter for vertecies
	 * @param verticies The vertecies
	 */
	public void setVerticies(HashSet<Vertex> verticies) {
		this.verticies = verticies;
	}

	/**
	 * Getter for edges
	 * @return The edges
	 */
	public HashSet<Edge> getEdges() {
		return edges;
	}

	/**
	 * Setter for edges
	 * @param edges The edges
	 */
	public void setEdges(HashSet<Edge> edges) {
		this.edges = edges;
	}
	
	
}
