package edu.miracosta.cs113;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

/**
 * Class invariant : Not generic
 * @author w7270821
 *
 */
public class Graph{
	private HashSet<Vertex> verticies;
	private HashSet<Edge> edges;
	
	public Graph(HashSet<Vertex> verticies){
		setVerticies(verticies);
	}
	
	/**
	 * Uses Dijkstra's algorithm
	 * @param start
	 * @param end
	 */
	public void shortestPath(Vertex start){
		//Testing 
		System.out.println("PRINTING VERTICIES");
		for(Vertex v : verticies){
			System.out.print(v + " ");
		}
		System.out.println("\n");
		
		HashSet<Vertex> processed = new HashSet<Vertex>();
		HashSet<Vertex> needToProcess = new HashSet<Vertex>();
		
		//Parallel arrays
		double[] shortestDistance = new double[verticies.size()];
		Vertex[] predecessor = new Vertex[verticies.size()];
		Vertex[] vertexArray = new Vertex[verticies.size()];
		//Add all vertecies
		int index = 0;
		for(Vertex v : verticies){
			vertexArray[index] = v;
		}
		
		//Corresponds a vertex to an integer for the parallel arrays
		HashMap<Vertex, Integer> map = new HashMap<Vertex, Integer>();
				
		//Set our V-S
		needToProcess.addAll(verticies);
				
		//Construct the mapping
		index = 0;
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
		
		//Testing
		System.out.println("PRINTING NEEDTOPROCESS");
		for(Vertex v : needToProcess){
			System.out.print(v + " ");
		}
		System.out.println("\n\n");
		
		//While we still have Vertecies to process
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
				
				//Testing
				System.out.println("Source: " + source +  " Destination: " + destination + " " + needToProcess.contains(destination));
				System.out.println("Source index : " + sourceIndex + " destination index : " + destinationIndex);
				
				for(Vertex v : needToProcess){
					System.out.print(v + " ");
				}
				System.out.println("");
				
				
				//Only process edges that lead to a vertex that has yet to be processed
				if(needToProcess.contains(destination)){
					
					
					//This distance
					double thisDistance = shortestDistance[sourceIndex] + edge.getWeight();
					//The current shortest distance to the destination vertex
					double currentShortestDistance = shortestDistance[destinationIndex];
					
					if(thisDistance < currentShortestDistance){
						shortestDistance[destinationIndex] = thisDistance;
						predecessor[destinationIndex] = edge.getSource();
					}else{
						System.out.println("This distance is: " + thisDistance + " the shortest is: " + currentShortestDistance);
					}
				}
			}
			//Remove it
			needToProcess.remove(vertexToProcess);
			//Add it to the processed ones
			processed.add(vertexToProcess);
			System.out.println("");
		}	
		
		int i = 0;
		for(Vertex v : verticies){
			System.out.println(v + " " + shortestDistance[i] + " " + predecessor[i]);
			i += 1;
		}
		
		Vertex endPoint = new Vertex(new Point(8,8));
		
		ArrayList<Vertex> path = new ArrayList<Vertex>();
		path.add(endPoint);
		int position = map.get(endPoint);
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
		System.out.println(path);
	}

	public HashSet<Vertex> getVerticies() {
		return verticies;
	}

	public void setVerticies(HashSet<Vertex> verticies) {
		this.verticies = verticies;
	}

	public HashSet<Edge> getEdges() {
		return edges;
	}

	public void setEdges(HashSet<Edge> edges) {
		this.edges = edges;
	}
	
	
}
