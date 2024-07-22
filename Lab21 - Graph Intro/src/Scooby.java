import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Scooby {
	public class Room {
		char ID;
		ArrayList<Room> connections;

		public Room(char ID) {
			this.ID = ID;
			connections = new ArrayList<>();
		}

		public boolean contains(char target) {
			for (Room r : connections) {
				if (r.ID == target) {
					return true;
				}
			}
			return false;
		}

		@Override
		public boolean equals(Object obj) {
			Room other = (Room) obj;
			return ID == other.ID;

		}

		public int hashCode() {
			return ID;
		}

		public String toString() {
			return ""+ID;
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("scooby.dat"));
		int numPassages = Integer.parseInt(s.nextLine());

		for (int i = 0; i < numPassages; i++) {
			String[] connections = s.nextLine().split(" ");
			ScoobyGraph graph = new ScoobyGraph();
			for (String connection : connections) {
				graph.addEdge(connection.charAt(0), connection.charAt(1));
			}
			String targets = s.nextLine();
			char origin = targets.charAt(0);
			char destination = targets.charAt(1);
			String answer = graph.hasConnection(origin, destination) ? "yes" : "no";
			System.out.println(answer);
		}
		s.close();
	}
}
