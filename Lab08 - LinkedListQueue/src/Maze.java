import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Maze {
	Square[][] maze;
	Square start;
	Square exit;

	public Maze() {

	}

	public boolean loadMaze(String fileName) {
		try {
			Scanner s = new Scanner(new File(fileName));
			int r = s.nextInt();
			int c = s.nextInt();
			s.nextLine();
			maze = new Square[r][c];
			for(int i = 0; i < r; i++) {
				for(int j = 0; j < c; j++) {
					int type = s.nextInt();
					if(type == Square.START) {
						maze[i][j] = new Square(i,j,type);
						start = maze[i][j];
					}
					if(type == Square.EXIT) {
						maze[i][j] = new Square(i,j,type);
						exit = maze[i][j];
					}
					else {
						maze[i][j] = new Square(i,j,type);
					}
					
				}
				s.nextLine();
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return true;
	}

	public List<Square> getNeighbors(Square s) {
		List<Square> neighbors = new ArrayList<Square>(); 
		//north
		if(s.getRow() - 1 >= 0) {
			neighbors.add(maze[s.getRow()-1][s.getCol()]);
		}
		//east
		if(s.getCol() + 1 < maze[s.getRow()].length) {
			neighbors.add(maze[s.getRow()][s.getCol()+1]);
		}
		//south
		if(s.getRow() + 1 < maze.length) {
			neighbors.add(maze[s.getRow()+1][s.getCol()]);
		}
		//west
		if(s.getCol() - 1 >= 0) {
			neighbors.add(maze[s.getRow()][s.getCol()-1]);
		}

		return neighbors;
	}

	public Square getStart() {
		return start;
	}

	public Square getExit() {
		return exit;
	}

	public void reset() {
		for(Square[] row: maze) {
			for(Square s: row) {
				s.reset();
			}
		}
	}

	public String toString() {
		String mazeStr = "";
		for(Square[] row: maze) {
			for(Square s: row) {
				mazeStr = mazeStr + s.toString() + " ";
			}
			mazeStr += "\n";
		}
		return mazeStr;
	}

}

