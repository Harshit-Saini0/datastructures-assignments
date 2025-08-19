import java.util.ArrayList;

public class Disk implements Comparable<Disk> {
	private int storageUsed;
	private ArrayList<Integer> files;
	
	public Disk(int file) {
		storageUsed = 0;
		files = new ArrayList<Integer>();
		this.add(file);
	}
	
	public int getStorageUsed() {
		return storageUsed;
	}
	
	public ArrayList<Integer> getFiles() {
		return files;
	}
	
	public void add(int file) {
		storageUsed += file;
		files.add(file);
	}
	
	public int compareTo(Disk other) {
		return storageUsed - other.storageUsed;
	}
}
