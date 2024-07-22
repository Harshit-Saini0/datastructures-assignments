public class PhoneNumber {
	int number;
	
	public PhoneNumber(int number) {
		this.number = number;
	}
	
	public boolean equals(Object obj) {
		PhoneNumber other = (PhoneNumber) obj;
		return this.number == other.number;
	}
	
	public int hashCode() {
		return number;
	}
	
	public String toString() {
		return ""+number;
	}
}
