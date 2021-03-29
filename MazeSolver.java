/*
 * @author JasonLi
 * Email: Jal042@ucsd.edu
 * This file implement dijikstra algorithm to find the path that cost the least.
 */
import java.util.ArrayList;

public abstract class MazeSolver {
	public static Square solve(Maze maze, PriorityQueue<Integer,Square> pq) {
		pq.add(maze.start.getCost(), maze.start);
		while(!pq.isEmpty()) {
			Entry<Integer, Square> current = pq.poll();
			Square currentSquare = current.value;
			currentSquare.visit();
			if(currentSquare == maze.finish) {  //May be mistake
				return currentSquare;
			}
			else {
				ArrayList<Square> validSquare = validSquare(maze, currentSquare.getRow(), currentSquare.getCol());
				for(Square s: validSquare) {
					int currentCost = current.key + s.getCost();
					if(currentCost < s.getRunningCost()) {
						s.setPrevious(currentSquare);
						s.setRunningCost(currentCost);
						pq.add(currentCost, s);
					}
				}
			}
			
		}
		
		return null;
	}
	/*
	 * This method determined if the neighbor squares are available
	 * @return  a list of neighbor square that is not wall, out of bound, or have been visited
	 * @param  maze   the maze wish to put
	 * @param  row   the row of the current square
	 * @param  col   the colum of the current square
	 */
	public static ArrayList<Square> validSquare(Maze maze, int row, int col){
		ArrayList<Square> result = new ArrayList<Square>();
		//east
		if(col + 1 < maze.cols && !maze.contents[row][col + 1].getIsWall() 
				&& !maze.contents[row][col + 1].isVisited()) {
			result.add(maze.contents[row][col + 1]);
		}
		//west
		if(col - 1 >= 0 && !maze.contents[row][col - 1].getIsWall() 
				&& !maze.contents[row][col - 1].isVisited()) {
			result.add(maze.contents[row][col - 1]);
		}
		//north
		if(row - 1 >= 0 && !maze.contents[row - 1][col].getIsWall() 
				&& !maze.contents[row - 1][col].isVisited()) {
			result.add(maze.contents[row - 1][col]);
		}
		//south
	    if(row + 1 < maze.rows && !maze.contents[row + 1][col].getIsWall() 
	    		&& !maze.contents[row + 1][col].isVisited()) {
			result.add(maze.contents[row + 1][col]);
		}
		return result;
	}
}
	
