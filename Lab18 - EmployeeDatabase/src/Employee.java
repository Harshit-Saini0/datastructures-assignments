public class Employee {
	
	String name;
	int ID;
	
	public Employee(String name, int ID) {
		this.name = name;
		this.ID = ID;
	}
	
	public int hashCode() {
		return ID % 89;
	}
	
	public boolean equals(Object other) {
		return this.ID == ((Employee) other).ID && this.name.equals(((Employee) other).name);
	}
	
	public String toString() {
		return "(Name:" + name + " | ID:" + ID + ")";
	}

}
