public class MazeSolverWithStack extends MazeSolver {
	
	private MyStack worklist;
	
	public MazeSolverWithStack(Maze maze) {
		super(maze);
	}

	public void makeEmpty() {
		worklist = new MyStack();
	}

	public boolean isEmpty() {
		return worklist.isEmpty();
	}

	public void add(Square s) {
		worklist.push(s);
	}
	
	public Square next() {
		return worklist.pop();
	}


}
