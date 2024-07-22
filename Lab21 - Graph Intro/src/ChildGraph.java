import java.util.ArrayList;
import java.util.HashMap;

public class ChildGraph {
	HashMap<Integer, Domino> vertexes;

	public ChildGraph() {
		vertexes = new HashMap<>();
	}

	public void addEdge(int u, int v) {
		Domino domino1 = vertexes.get(u);
		if (domino1 == null) {
			domino1 = new Domino(u);
			vertexes.put(u, domino1);
		}
		Domino domino2 = vertexes.get(v);
		if (domino2 == null) {
			domino2 = new Domino(v);
			vertexes.put(v, domino2);
		}
		domino1.connections.add(domino2);
	}

	public int howManyFall(int key, ArrayList<Integer> visited) {
		int numFallen = 1;
		Domino dom = vertexes.get(key);
		if(visited.contains(dom.ID)) {
			return 0;
		}
		visited.add(dom.ID);
		for(Domino d: dom.connections) {
			numFallen += howManyFall(d.ID, visited);
		}
		return numFallen;	
	}
}