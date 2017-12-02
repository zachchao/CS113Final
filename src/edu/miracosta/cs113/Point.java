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
}
