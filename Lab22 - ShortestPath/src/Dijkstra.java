import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra {
	Graph graph;
	
	public Dijkstra(Graph graph) {
		this.graph = graph;
	}
	
	public double distance(int source, int destination) {
		dijkstra(source, destination);
		return graph.vertices.get(destination).distance;
	}

	private void dijkstra(int source, int destination) {
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[graph.vertices.size()];

		for(Vertex v: graph.vertices) {
			v.distance = (v.ID == source) ? 0 : Double.POSITIVE_INFINITY;
			pq.offer(v);
		}
		
		while(!pq.isEmpty()) {
//			Vertex current = pq.poll();
//sorry for the weird code here, couldn't figure out why my pq wasn't pulling the min value
			Vertex current = pq.poll();
			for(Vertex v: pq) {
				if(current.distance > v.distance) {
					current = v;
				}
			}
			
			System.out.println("Node:" + current.ID + " (dist " + current.distance + ")");
			for(Integer neighbor: current.edges) {
				if(!visited[neighbor])
					relax(current, graph.vertices.get(neighbor));
			}
			visited[current.ID] = true;
		}
	}
	
	private void relax(Vertex current, Vertex neighbor) {
		double dist = current.distanceTo(neighbor);
		if(current.distance + dist < neighbor.distance) {
			neighbor.distance = current.distance + dist;
			System.out.println("      lower " + neighbor.ID + " to " + neighbor.distance);
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("input6.txt"));
		Graph g = new Graph(s);
		Dijkstra d = new Dijkstra(g);
		System.out.println(d.distance(0, 5));
	}
}
