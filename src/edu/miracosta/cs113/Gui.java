package edu.miracosta.cs113;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gui extends JFrame
{
	/*** Window Settings ***/
	public static final int WINDOW_WIDTH = 500;
	public static final int WINDOW_HEIGHT = 500;
	
	/*** JPanels ***/
	
	private JPanel wholePanel; 
	private JPanel mazePanel; 
	
	/*** Data Fields ***/
	
	protected int count = 0;
	
	protected Vertex start = new Vertex(new Point(0, 0));
	protected Vertex end = new Vertex(new Point(8, 8));
	protected MazeBuilder builder;
	protected MatrixToGraph converter;
	protected Graph graph;
	protected int[][] maze; 
	protected char continueMaze = 49;
	
	
	/**
	 * Constructor for gui, initializes panels 
	 * @throws Exception 
	 */
	public Gui() throws Exception
	{
		super("Maze Solver");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		buildWholePanel(buildMazePanel(), "Press 1 to build maze");
		
		add(wholePanel);
		setVisible(true);
	}
	
	/**
	 * builds the main panel
	 * @param mazepanel contains maze 
	 * @param title Heading displayed at top of sreen
	 * @throws Exception 
	 */
	public void buildWholePanel(JPanel mazepanel, String title) throws Exception
	{
		wholePanel = new JPanel(new BorderLayout());
		JLabel label = new JLabel(title);
		wholePanel.add(label, BorderLayout.PAGE_START);
		wholePanel.add(mazePanel, BorderLayout.CENTER);
		
		wholePanel.setFocusable(true);
		wholePanel.requestFocusInWindow();
		wholePanel.addKeyListener(new EnterKeyListener());
	}
	
	/**
	 * Builds and returns maze panel
	 * @return Maze Jpanel
	 */
	public JPanel buildMazePanel()
	{
		JButton temp;
		mazePanel = new JPanel(new GridLayout(9, 9));
		
		for(int i = 0; i < 81; i++) // 81 is 9*9 which is the dimmensiosn of the layounad
		{
			temp = new JButton("  ");
			temp.setBackground(Color.BLACK);
			temp.setOpaque(true);
			temp.setFocusable(false);
			temp.setEnabled(false);
			mazePanel.add(temp);
		}
		
		mazePanel.setFocusable(false);
		return mazePanel;
	}

	/**
	 * Builds all paths into maze panel
	 * @return Maze JPanel
	 * @throws Exception
	 */
	public JPanel buildAllPathsMazePanel() throws Exception
	{
		this.mazePanel = new JPanel(new GridLayout(9, 9));
		this.end = new Vertex(new Point(8, 8));
		int[] endPos = {end.getPoint().getRow(), end.getPoint().getCol()};
		
		this.maze = MazeBuilder.generateSolvableMaze(endPos);
		
		converter = new MatrixToGraph(maze, false);
		graph = converter.getGraph(); //makes path that is used later
		//graph.shortestPath(start, end, false);
		
		JButton temp; 
		for(int i = 0; i < this.maze.length; i++)
		{
			for(int j = 0; j < this.maze.length; j++)
			{
				if(maze[i][j] == 0)
				{
					temp = new JButton("  ");
					temp.setBackground(Color.WHITE);
					temp.setOpaque(true);
					temp.setFocusable(false);
					temp.setEnabled(false);
					mazePanel.add(temp);
				}
				else
				{
					temp = new JButton("  ");
					temp.setBackground(Color.BLACK);
					temp.setOpaque(true);
					temp.setFocusable(false);
					temp.setEnabled(false);
					mazePanel.add(temp);
				}
			}
		}
		
		return mazePanel;
	}
	
	/**
	 * Builds shortest path solution into maze Panel
	 * @return Maze JPanel
	 */
	public JPanel buildShortestPath()
	{
		graph.shortestPath(start, end, false);
		MazeBuilder.fillShortestPath(maze, graph.path);
		
		this.mazePanel = new JPanel(new GridLayout(9, 9));
		
 		JButton temp;
 		for(int i = 0; i < this.maze.length; i++)
		{
			for(int j = 0; j < this.maze.length; j++)
			{
				temp = new JButton("  ");
				if(maze[i][j] == 0)
				{
					temp.setBackground(Color.WHITE);
				}
				else if(maze[i][j] == 2)
				{
					temp.setBackground(new Color(128,0,128));
				}
				else
				{
					temp.setBackground(Color.BLACK);
				}
				
				temp.setOpaque(true);
				temp.setEnabled(false);
				mazePanel.add(temp);
			}
		}
 		 		
 		return mazePanel;
	}

	 
	/**
	 * 
	 * @author Josie
	 *
	 */
	private class EnterKeyListener implements KeyListener
	{		
		@Override
		public void keyPressed(KeyEvent event) 
		{
			if(event.getKeyChar() == '1')
			{
				try
				{
					if(count == 0)
					{
						count++;
						buildWholePanel(buildAllPathsMazePanel(), "Press 1 to solve maze");
						setContentPane(wholePanel);
			
						revalidate();
						wholePanel.setFocusable(true);
						wholePanel.requestFocusInWindow();
					}
					else if(count == 1)
					{

						count++;
						buildWholePanel(buildShortestPath(), "Press 1 to restart");
						setContentPane(wholePanel);

						revalidate();
						wholePanel.setFocusable(true);
						wholePanel.requestFocusInWindow();
					}
					else
					{
						count = 0;
						buildWholePanel(buildMazePanel(), "Press 1 to build maze");
						setContentPane(wholePanel);
						
						revalidate();
						wholePanel.setFocusable(true);
						wholePanel.requestFocusInWindow();
					}
				}
				catch(Exception e)
				{
					e.printStackTrace(System.out);
				}
			}
			else
			{
				return;
			}
		}
			

		@Override
		public void keyReleased(KeyEvent arg0) 
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent event) 
		{			
						
		}
	}
	
}
