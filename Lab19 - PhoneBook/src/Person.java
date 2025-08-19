public class Person {
	String name;

	public Person(String name) {
		this.name = name;
	}
	
	public boolean equals(Object obj) {
		Person other = (Person) obj;
		return this.name.equals(other.name);
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	public String toString() {
		return name;
	}
}
