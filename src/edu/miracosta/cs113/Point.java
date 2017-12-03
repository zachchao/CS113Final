package edu.miracosta.cs113;

/**
 * Point.java
 * 
 * Class Invariant: None
 * 
 * @author Zachary Chao <zach.chao@yahoo.com>
 * @version 1.0
 * 
 * Algorithm - 
 * 	Holds a coordinatefor a 2d array
 */
public class Point {
	private int row, col;
	
	/**
	 * Full argument constructor
	 * @param row Row of the coordinate
	 * @param col Col of the coordinate
	 */
	public Point(int row, int col){
		this.setRow(row);
		this.setCol(col);
	}
	
	/**
	 * Getter for column
	 * @return The column
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Setter for the column
	 * @param col The column
	 */
	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * Getter for the row
	 * @return The row
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Setter for the row
	 * @param row The row
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * String representation of the point
	 */
	public String toString(){
		return "(" + row + "," + col + ")";
	}
	
	/**
	 * Basic hashcode method
	 */
	public int hashCode(){
		return Integer.valueOf(row).hashCode();
	}
	
	/**
	 * Equals compares row and column
	 */
	public boolean equals(Object other){
		if(other instanceof Point){
			Point toCompare = (Point) other;
			return this.row == toCompare.getRow() && this.col == toCompare.getCol();
		}
		return false;
	}
}
