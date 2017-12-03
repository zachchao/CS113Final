package edu.miracosta.cs113;

import java.util.ArrayList;
import java.util.List;

/**
 * Vertex.java
 * 
 * Class Invariant: Not generic
 * 
 * @author Zachary Chao <zach.chao@yahoo.com>
 * @version 1.0
 * 
 * Algorithm - 
 * 	Holds a vertex which is just a point and 
 * 	a list of edges
 */
public class Vertex {
	private Point point;
	private ArrayList<Edge> connections;
	
	/**
	 * Full argument constructor
	 * @param point The point
	 * @param connections List of adjacent edges
	 */
	public Vertex(Point point, ArrayList<Edge> connections){
		setPoint(point);
		setConnections(connections);
	}
	
	/**
	 * Constructor for just the point, allowing edges to be added
	 * @param point The point
	 */
	public Vertex(Point point){
		setPoint(point);
		connections = new ArrayList<Edge>();
	}
	
	/**
	 * For adding connections
	 * @param edge The edge to add ot the list
	 */
	public void addConnection(Edge edge){
		connections.add(edge);
	}
	
	/**
	 * Getter for point
	 * @return The point
	 */
	public Point getPoint() {
		return point;
	}
	
	/**
	 * Setter for the point
	 * @param point The point
	 */
	public void setPoint(Point point) {
		this.point = point;
	}

	/**
	 * Getter for connections
	 * @return The connections to this point
	 */
	public List<Edge> getConnections() {
		return connections;
	}

	/**
	 * Setter for the connections
	 * @param connections The connections to add 
	 */
	public void setConnections(ArrayList<Edge> connections) {
		this.connections = connections;
	}
	
	/**
	 * String representation of a vertex
	 */
	public String toString(){
		return point.toString();
	}
	
	/**
	 * Hashcode for a vertex, does point so two verts with 
	 * same points are equal
	 */
	public int hashCode(){
		return this.point.hashCode();
	}
	
	/**
	 * Equals method, only compares the points
	 */
	public boolean equals(Object other){
		if(other instanceof Vertex){
			Vertex toCompare = (Vertex) other;
			return this.point.equals(toCompare.getPoint());
		}
		return false;
	}
}
