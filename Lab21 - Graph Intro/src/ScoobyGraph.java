import java.util.ArrayList;
import java.util.HashMap;

public class ScoobyGraph {
	HashMap<Character, Scooby.Room> vertexes;

	public ScoobyGraph() {
		vertexes = new HashMap<>();
	}

	public void addEdge(char u, char v) {
		Scooby scoob = new Scooby();
		Scooby.Room room1 = vertexes.get(u);
		if (room1 == null) {
			room1 = scoob.new Room(u);
			vertexes.put(u, room1);
		}
		Scooby.Room room2 = vertexes.get(v);
		if (room2 == null) {
			room2 = scoob.new Room(v);
			vertexes.put(v, room2);
		}
		room1.connections.add(room2);
		room2.connections.add(room1);
	}

	public boolean hasConnection(char origin, char destination) {
		if (!vertexes.containsKey(origin) || !vertexes.containsKey(destination)) {
			return false;
		}
		return hasConnectionHelper(vertexes.get(origin), destination, new ArrayList<>());
	}

	private boolean hasConnectionHelper(Scooby.Room origin, char destination, ArrayList<Character> visitedRooms) {
		if (origin.ID == destination) {
			return true;
		}
		visitedRooms.add(origin.ID);
		for (Scooby.Room r : origin.connections) {
			if (!visitedRooms.contains(r.ID) && hasConnectionHelper(r, destination, visitedRooms)) {
				return true;
			}
		}
		return false;
	}
}