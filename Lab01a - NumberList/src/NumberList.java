public class NumberList {
	
	private Integer[] list;
	private int size;
	
	public NumberList() {
		list = new Integer[2];
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
		
	}
	
	public String toString() {
		String str = "[";
		for(int i = 0; i < size; i++) {
			if(i == 0) {
				str += list[i];
			}
			else {
				str += ", " + list[i];
			}
		}
		str += "]";
		return str;
	}
	
	private void doubleCapacity() {
		Integer[] newList = new Integer[size*2];
		for(int i = 0; i < size; i++) {
			newList[i] = list[i];
		}
		list = newList;
	}
	
	public void add(int index, Integer val) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		
		if(size + 1 > list.length) {
			doubleCapacity();
		}
		
		for(int i = size - 1; i >= index; i--) {
			list[i+1] = list[i];
		}
		
		list[index] = val;
		size++;
	}
	
	public boolean add(Integer element) {
		add(size, element);
		return true;
	}

	public Integer get(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		return list[index];
	}
	
	public Integer set(int index, Integer val) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		Integer oldVal = list[index];
		list[index] = val;
		return oldVal;
	}
	
	public Integer remove(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		
		Integer removedVal = list[index];
			
		for(int i = index; i < size; i++) {
			list[i] = list[i + 1];
		}
		
		size--;
		
		return removedVal;
	}
}
