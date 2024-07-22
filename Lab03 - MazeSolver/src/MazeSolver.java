public abstract class MazeSolver {
	
	protected Maze maze;
	private boolean solved = false;
	private boolean solvable = true;

	public MazeSolver(Maze maze) {
		this.maze = maze;
		makeEmpty();
		add(maze.getStart());
	}

	public abstract void makeEmpty();

	public abstract boolean isEmpty();

	public abstract void add(Square s);

	public abstract Square next();

	public boolean isSolved() {
		if(solved) {
			return solved;
		}
		
		else if(isEmpty()){
			solvable = false;
			return solved;
		}
		
		else {
			return solved;
		}
	}

	public void step() { 
		if(isEmpty()) { 
			solved = true; 
			return; 
		} 
		
		Square s = next(); 
		s.setStatus(Square.EXPLORED);
		
		if(s.getType() == Square.EXIT) { 
			solved = true; 
			solvable = true; 
			return; 
		} 
		
		for(Square sq: maze.getNeighbors(s)){ 
			
			if(sq.getType() == Square.EXIT) { 
				solved = true; 
				solvable = true; 
				return; 
			} 
			
			if(sq.getType() != Square.WALL && sq.getStatus() != Square.EXPLORED) 
			{ 
				add(sq); 
				sq.setStatus(Square.WORKING); 
			} 
		} 
	}


	public String getPath() {
		if (solved) {
			if(solvable) {
				return "Maze Solved";
			}
			return "No Solution";
		}
		return "Solving...";
	}

	public void solve() {
		while (!solved) {
			step();
		}
	}
}