import java.util.ArrayList;

public class Domino {
		int ID;
		ArrayList<Domino> connections;

		public Domino(int ID) {
			this.ID = ID;
			connections = new ArrayList<>();
		}

		public int numConnections() {
			return connections.size();
		}
	}