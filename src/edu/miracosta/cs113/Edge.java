package edu.miracosta.cs113;

/**
 * Basic edge class
 * @author w7270821
 *
 */
public class Edge {
	private Point source, destination;
	private int weight;
	
	public Edge(Point source, Point destination, int weight){
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}
	
	public String toString(){
		return "Edge - Source: " + source + " Destination: " + destination + " Weight: " + weight;
	}
}
