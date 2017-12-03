package edu.miracosta.cs113;

public class Point {
	private int row, col;
	
	public Point(int row, int col){
		this.setRow(row);
		this.setCol(col);
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public String toString(){
		return "(" + row + "," + col + ")";
	}
	
	public int hashCode(){
		return Integer.valueOf(row).hashCode();
	}
	
	public boolean equals(Object other){
		if(other instanceof Point){
			Point toCompare = (Point) other;
			return this.row == toCompare.getRow() && this.col == toCompare.getCol();
		}
		return false;
	}
}
