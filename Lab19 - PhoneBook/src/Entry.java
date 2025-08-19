public class Entry {
	Entry next;
	Person person;
	PhoneNumber number;
	
	public Entry(Person person, PhoneNumber number) {
		this.person = person;
		this.number = number;
		next = null;
	}
	
	public Entry(Person person) {
		this.person = person;
		number = null;
		next = null;
	}
	
	public boolean equals(Object obj) {
		Entry other = (Entry) obj;
		return this.person.equals(other.person) && this.number.equals(other.number);
	}
	
	public int hashCode() {
		return person.hashCode();
	}
	
	public String toString() {
		return "<" + person + ", " + number + ":" + next + ">";
	}

}
