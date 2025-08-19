public class Location {
	int x;
	int y;
	int numCols;

	public Location(int y, int x) {
		this.x = x;
		this.y = y;
	}
	
	public int hashcode() {
		return numCols * y + x;
	}
	
	public boolean equals(Object obj) {
		Location other = (Location) obj;
		return x == other.x && y == other.y;
	}
}

