package edu.miracosta.cs113;

/**
 * Edge.java
 * 
 * Class Invariant: Not generic
 * 
 * @author Zachary Chao <zach.chao@yahoo.com>
 * @version 1.0
 * 
 * Algorithm - 
 * 	An edge is source, destination, weight
 */
public class Edge {
	private Vertex source;
	private Vertex destination;
	private int weight;
	
	/**
	 * Full argument constructor
	 * @param source The source of the edge
	 * @param destination The destination of the edge
	 * @param weight Weight of the edge
	 */
	public Edge(Vertex source, Vertex destination, int weight){
		setSource(source);
		setDestination(destination);
		setWeight(weight);
	}
	
	/**
	 * Setter for the source vertex
	 * @param source The vertex
	 */
	public void setSource(Vertex source) {
		this.source = source;
	}

	/**
	 * Getter for the source vertex
	 * @return source The vertex
	 */
	public Vertex getSource() {
		return source;
	}
	
	/**
	 * Setter for the destination vertex
	 * @return destination The vertex
	 */
	public Vertex getDestination() {
		return destination;
	}

	/**
	 * Setter for the destination vertex
	 * @param destination The vertex
	 */
	public void setDestination(Vertex destination) {
		this.destination = destination;
	}

	/**
	 * Getter for the weight
	 * @return weight The weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * Setter for the weight
	 * @param weight The weight
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	/**
	 * String representation of the Edge
	 */
	public String toString(){
		return "Edge - Source: " + source + " Destination: " + destination + " Weight: " + weight;
	}
}
