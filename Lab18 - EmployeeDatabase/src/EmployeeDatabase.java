public class EmployeeDatabase {
	Employee[] arr;
	int size;
	
	public EmployeeDatabase() {
		arr = new Employee[11];
		size = 0;
	}
	
	public EmployeeDatabase(int initialCapacity) {
		arr = new Employee[initialCapacity];
		size = 0;
	}

	public boolean add(Employee employee) {
		
		for(int i = 0; i < arr.length; i++) {
			
//			linear probing
//			int offset = i;
			
//			quadratic probing
			int offset = (int) Math.pow(i, 2);
			
			
			if(arr[(employee.hashCode() + offset) % arr.length] != null && 
					arr[(employee.hashCode() + offset) % arr.length].equals(employee)) {
				return false;
			}
			else if(arr[(employee.hashCode() + offset) % arr.length] == null) {
				size++;
				arr[(employee.hashCode() + offset) % arr.length] = employee;
				return true;
			}
		}
		return false;
	}

	public boolean contains(Employee employee) {
		for(int i = 0; i < arr.length; i++) {
//			linear probing
//			int offset = i;
			
//			quadratic probing
			int offset = (int) Math.pow(i, 2);
			
			if(arr[(employee.hashCode() + offset) % arr.length] != null && 
					arr[(employee.hashCode() + offset) % arr.length].equals(employee)) {
				return true;
			}
			if(arr[(employee.hashCode() + offset) % arr.length] == null) {
				return false;
			}
		}
		return false;
	}

	public boolean remove(Employee employee) {
		for(int i = 0; i < arr.length; i++) {
//			linear probing
//			int offset = i;
			
//			quadratic probing
			int offset = (int) Math.pow(i, 2);
			
			if(arr[(employee.hashCode() + offset) % arr.length] != null && 
					arr[(employee.hashCode() + offset) % arr.length].equals(employee)) {
				arr[(employee.hashCode() + offset) % arr.length] = null;
				size--;
				return true;
			}
			if(arr[(employee.hashCode() + offset) % arr.length] == null) {
				return false;
			}
		}
		return false;
	}
	
	public int size() {
		return size;
	}
	
	public String toString() {
		String ret = "[";
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] != null) {
				ret = ret + arr[i] + ", ";
			}
		}
		return ret.substring(0, ret.length()-2) + "]";
	}
}
