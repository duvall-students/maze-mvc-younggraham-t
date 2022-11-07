package application;

import java.awt.Point;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
import searches.BFS;
import searches.DFS;
import searches.Greedy;
import searches.Magic;
import searches.RandomWalk;
import searches.SearchAlgorithm;
import searches.SearchFactory;

public class MazeController {
	/* 
	 * Logic of the program
	 */
	// The search algorithms
//	private Greedy greedy;				
//	private BFS bfs;
//	private DFS dfs;
//	private RandomWalk rand;
//	private Magic magic;
	private SearchAlgorithm search;	// This string tells which algorithm is currently chosen.  Anything other than 
	// the implemented search class names will result in no search happening.

	// Where to start and stop the search
	private Point start;
	private Point goal;

	// The maze to search
	private Maze maze;
	
	private MazeDisplay mazeDisplay;
	
	private SearchFactory searchFactory = new SearchFactory();
	
	public MazeController(int numRows, int numColumns, MazeDisplay mazeDisplay) {
		start = new Point(1,1);
		goal = new Point(numRows-2, numColumns-2);
		maze = new Maze(numRows, numColumns);
		this.mazeDisplay = mazeDisplay;
	}
	
	/*
	 * Re-create the maze from scratch.
	 * When this happens, we should also stop the search.
	 */
	public void newMaze() {
		maze.createMaze(maze.getNumRows(),maze.getNumCols());
		//search = "";
		mazeDisplay.redraw();
	}
	
	
	public void doOneStep(double elapsedTime){
		if(search!=null) search.step();
		mazeDisplay.redraw();	
	}
	
	public void startSearch(String searchType) {
		maze.reColorMaze();
		//search = searchType;
		
		// Restart the search.  Since I don't know 
		// which one, I'll restart all of them.
		search = searchFactory.makeSearch(searchType, maze, start, goal);

	}

	public int getCellState(Point position) {
		return maze.get(position);
	}

}
