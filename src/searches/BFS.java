package searches;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import application.Maze;

public class BFS extends SearchAlgorithm {	

	// Keeps up with the child-parent trail so we can recreate the chosen path
	Map<Point,Point> childParent;



	public BFS(Maze mazeBlocks, Point startPoint, Point goalPoint){
		super(mazeBlocks, startPoint, goalPoint);
		
		data = new LinkedList<>();
		data.add(startPoint);
		childParent = new HashMap<>();
	}

//	/*
//	 * Algorithm for Breadth-First Search
//	 */
//	public boolean step(){
//		// Don't keep computing after goal is reached or determined impossible.
//		if(searchOver){
//			
//		}
//		// Find possible next steps
//		Collection<Point> neighbors = getNeighbors();
//		// Choose one to be a part of the path
//		Point next = chooseNeighbor(neighbors);
//		// mark the next step
//		if(next!=null){
//			maze.markPath(next);
//			recordLink(next);
//		}
//		// if no next step is found, mark current 
//		// state "visited" and take off queue.
//		else{	
//			maze.markVisited(current);
//			Queue<Point> queue = (Queue<Point>) data;
//			queue.remove();
//		}
//		resetCurrent();
//		checkSearchOver();
//		return searchResult;	
//	}


	protected boolean searchOver() {
		colorPath();
		return searchResult;
	}
	/*
	 * In addition to putting the new node on the data structure, 
	 * we need to remember who the parent is.
	 */
	protected void recordLink(Point next){	
		Queue<Point> queue = (Queue<Point>) data;
		queue.add(next);
		childParent.put(next,current);
	}
	
	protected void resetCurrent(){
		Queue<Point> queue = (Queue<Point>) data;
		current = queue.peek();
	}
	
	protected void nextIsNull() {
		maze.markVisited(current);
		Queue<Point> queue = (Queue<Point>) data;
		queue.remove();
	}


	/*
	 * Use the trail from child to parent to color the actual chosen path
	 */
	private void colorPath(){
		Point step = goal;
		maze.markPath(step);
		while(step!=null){
			maze.markPath(step);
			step = childParent.get(step);
		}
	}






}
