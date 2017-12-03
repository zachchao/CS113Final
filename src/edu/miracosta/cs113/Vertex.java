package edu.miracosta.cs113;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
	private Point point;
	private ArrayList<Edge> connections;
	
	public Vertex(Point point, ArrayList<Edge> connections){
		setPoint(point);
		setConnections(connections);
	}
	
	public Vertex(Point point){
		setPoint(point);
		connections = new ArrayList<Edge>();
	}
	
	public void addConnection(Edge edge){
		connections.add(edge);
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public List<Edge> getConnections() {
		return connections;
	}

	public void setConnections(ArrayList<Edge> connections) {
		this.connections = connections;
	}
	
	public String toString(){
		return point.toString();
	}
	
	public int hashCode(){
		return this.point.hashCode();
	}
	
	public boolean equals(Object other){
		if(other instanceof Vertex){
			Vertex toCompare = (Vertex) other;
			return this.point.equals(toCompare.getPoint());
		}
		return false;
	}
}
