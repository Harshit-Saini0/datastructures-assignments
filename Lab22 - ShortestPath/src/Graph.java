import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Graph {
	int V;
	int E;
	ArrayList<Vertex> vertices;
	
	public Graph(Scanner s) {
		vertices = new ArrayList<Vertex>();
		int V = s.nextInt();
		int E = s.nextInt();
		for(int i = 0; i < V; i++) {
			int ID = s.nextInt();
			int x = s.nextInt();
			int y = s.nextInt();
			Vertex v = new Vertex(ID, x, y);
			vertices.add(v);
		}
		for(int i = 0; i < E; i++) {
			int p = s.nextInt();
			int q = s.nextInt();
			Vertex u = vertices.get(p);
			Vertex v = vertices.get(q);
			u.edges.add(v.ID);
			v.edges.add(u.ID);
		}
	}
	
	public double distance(int from, int to) {
		Vertex a = vertices.get(from);
		Vertex b = vertices.get(to);
		return a.distanceTo(b);
	}
	
	public String toString() {
		String str = "";
		for(Vertex v: vertices) {
			str += v.toString() + "\n";
		}
		return str;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("input6.txt"));
		Graph g = new Graph(s);
		System.out.println(g);
	}
}
