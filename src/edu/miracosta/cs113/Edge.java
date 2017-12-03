package edu.miracosta.cs113;

/**
 * Basic edge class
 * @author w7270821
 *
 */
public class Edge {
	private Vertex source;
	private Vertex destination;
	private int weight;
	
	public Edge(Vertex source, Vertex destination, int weight){
		setSource(source);
		setDestination(destination);
		setWeight(weight);
	}
	
	public void setSource(Vertex source) {
		this.source = source;
	}

	public Vertex getSource() {
		return source;
	}
	
	public Vertex getDestination() {
		return destination;
	}

	public void setDestination(Vertex destination) {
		this.destination = destination;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public String toString(){
		return "Edge - Source: " + source + " Destination: " + destination + " Weight: " + weight;
	}
}
