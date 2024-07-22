import java.util.ArrayList;
import java.util.List;

public class Vertex implements Comparable<Vertex> {
	int x;
	int y;
	int ID;
	List<Integer> edges;
	boolean visited;
	double distance;

	public Vertex(int ID, int x, int y) {
		this.x = x;
		this.y = y;
		this.ID = ID;
		edges = new ArrayList<Integer>();
		visited = false;
		distance = Double.POSITIVE_INFINITY;
	}
	
	public String toString() {
		return ID + ": " + edges.toString();
	}
	
	public double distanceTo(Vertex other) {
		return Math.sqrt(Math.pow(other.x - this.x, 2) + Math.pow(other.y - this.y, 2));
	}


	@Override
	public int compareTo(Vertex other) {
		Double a = this.distance;
		Double b = other.distance;
		return a.compareTo(b);
	}

}
