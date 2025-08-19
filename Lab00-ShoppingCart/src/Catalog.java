import java.util.ArrayList;

public class Catalog {
	ArrayList<Item> catalog  = new ArrayList<Item>();
	String name;
	public Catalog(String name) {
		this.name = name;
	}
	
	public void add(Item item) {
		catalog.add(item);
	}
	
	public int size() {
		return catalog.size();
	}
	
	public Item get(int index) {
		return catalog.get(index);
	}
	
	public String getName() {
		return name;
	}
}
