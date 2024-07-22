import java.util.ArrayList;

public class ScoobyRoom {
	char ID;
	ArrayList<ScoobyRoom> connections;

	public ScoobyRoom(char ID) {
		this.ID = ID;
		connections = new ArrayList<>();
	}

	public boolean contains(char target) {
		for (ScoobyRoom r : connections) {
			if (r.ID == target) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		ScoobyRoom other = (ScoobyRoom) obj;
		return ID == other.ID;

	}

	public int hashCode() {
		return ID;
	}

	public String toString() {
		return ""+ID;
	}
}
