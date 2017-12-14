package edu.miracosta.cs113;

public class Test {	
	public static void main(String[] args) throws Exception
	{
		Gui gui = new Gui();
		
		
	}
}
		
/*
maze = new int[size][size];
int[] endPos = {size - 1 , size - 1};	
int[][] maze1 = MazeBuilder.generateSolvableMaze(endPos);
*/


/*
//Test maze

				  // 0 1 2 3 4 5 6 7 8 9
maze[0] = new int[] {0,1,1,0,1,1,1,1,1,1};
maze[1] = new int[] {0,0,0,0,0,0,0,1,1,1};
maze[2] = new int[] {1,1,1,0,1,1,0,0,1,1};
maze[3] = new int[] {1,0,1,0,1,1,1,1,1,1};
maze[4] = new int[] {1,0,0,0,1,1,1,1,1,1};
maze[5] = new int[] {1,1,1,0,0,0,0,0,1,1};
maze[6] = new int[] {1,1,1,1,0,1,1,1,1,1};
maze[7] = new int[] {1,1,1,1,0,0,1,1,1,1};
maze[8] = new int[] {1,1,1,1,1,0,0,0,0,1};

        		  // 0 1 2 3 4 5 6 7 8 9
maze[0] = new int[] {0,0,0,0,0,0,0,0,0,0};
maze[1] = new int[] {1,1,1,1,1,1,1,1,1,0};
maze[2] = new int[] {1,1,1,1,1,1,1,1,1,0};
maze[3] = new int[] {1,1,1,1,1,1,1,1,1,0};
maze[4] = new int[] {1,1,1,1,1,1,1,1,1,0};
maze[5] = new int[] {1,1,1,1,0,0,0,1,1,0};
maze[6] = new int[] {1,1,1,1,1,1,1,1,1,0};
maze[7] = new int[] {1,1,1,1,1,1,1,1,0,0};
maze[8] = new int[] {1,1,1,1,1,1,1,1,0,1};

*/

/*
Vertex start = new Vertex(new Point(0,0));
Vertex end = new Vertex(new Point(8,8));

//Set the last argument to true for testing purposes
MatrixToGraph converter = new MatrixToGraph(maze1, true);
Graph graph = converter.getGraph();

graph.shortestPath(start, end, true);
MazeBuilder.fillShortestPath(maze1, graph.path);
MazeBuilder.printMaze(maze1);

*/