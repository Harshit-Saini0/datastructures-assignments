public class MazeSolverWithQueue extends MazeSolver {
	
	private MyQueue<Square> worklist;
	
	public MazeSolverWithQueue(Maze maze) {
		super(maze);
	}

	public void makeEmpty() {
		worklist = new MyQueue<Square>();
	}

	public boolean isEmpty() {
		return worklist.isEmpty();
	}

	public void add(Square s) {
		worklist.offer(s);
	}
	
	public Square next() {
		return worklist.poll();
	}


}
