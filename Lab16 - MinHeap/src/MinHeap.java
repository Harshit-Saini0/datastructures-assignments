public class MinHeap {
	private Integer[] heap;
	private int size;
	private static final int DEFAULT_CAPACITY = 8;
	
	public MinHeap() {
		heap = new Integer[DEFAULT_CAPACITY];
		size = 0;
	}
	
	public MinHeap(int size) {
		heap = new Integer[size];
		size = 0;
	}
	
	public MinHeap(Integer... nums) {
		heap = new Integer[DEFAULT_CAPACITY];
		size = 0;
		buildHeap(nums);
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int peekMinimum() {
		return heap[1];
	}
	
	public int getLeftChildIndex(int index) {
		return index * 2;
	}
	
	public int getRightChildIndex(int index) {
		return index * 2 + 1;
	}
	
	public int getParentIndex(int index) {
		return index / 2;
	}
	
	public void doubleCapacity() {
		Integer[] heapCopy = new Integer[heap.length * 2];
		for(int i = 1; i < heap.length; i++) {
			heapCopy[i] = heap[i];
		}
		heap = heapCopy;
	}

	public void insert(int value) {
		if(size + 1 == heap.length) {
			doubleCapacity();
		}
		heap[size + 1] = value;
		bubbleUp(size + 1);
		size++;
	}
	
	private void bubbleUp(int index) {
		if(getParentIndex(index) < 1) 
			return;
		if(heap[index] > heap[getParentIndex(index)]) 
			return;
		
		int temp = heap[index];
		heap[index] = heap[getParentIndex(index)];
		heap[getParentIndex(index)] = temp;
		bubbleUp(getParentIndex(index));
	}
	
	public int popMinimum() {
		int min = heap[1];
		heap[1] = heap[size];
		heap[size] = null;
		size--;
		siftDown(1);
		return min;
	}
	
	private void siftDown(int index) {
		if(getLeftChildIndex(index) > size || getRightChildIndex(index) > size) 
			return;
		if(heap[index] < heap[getLeftChildIndex(index)] && heap[index] < heap[getRightChildIndex(index)]) 
			return;
		int minIndex = (heap[getLeftChildIndex(index)] > heap[getRightChildIndex(index)]) 
				? getRightChildIndex(index) : getLeftChildIndex(index);
		int temp = heap[index];
		heap[index] = heap[minIndex];
		heap[minIndex] = temp;
		siftDown(minIndex);
	}
	
	public void buildHeap(Integer[] nums) {
		while(nums.length > heap.length) {
			doubleCapacity();
		}
		
		for(int i = 0; i < nums.length; i++) {
			heap[i] = nums[i];
		}
		
		for(int i = getSize()/2; i > 1; i--) {
			siftDown(i);
		}
	}

	@Override
	public String toString()
	{
		String output = "";

		for (int i = 1; i <= getSize(); i++) 
			output += heap[i] + ", ";

		return output.substring(0, output.lastIndexOf(",")); //lazily truncate last comma
	}

	/** method borrowed with minor modifications from the internet somewhere, for printing */
	public void display() {
		int nBlanks = 32, itemsPerRow = 1, column = 0, j = 1;
		String dots = "...............................";
		System.out.println(dots + dots);      
		while (j <= this.getSize())
		{
			if (column == 0)                 
				for (int k = 0; k < nBlanks; k++)
					System.out.print(' ');

			System.out.print((heap[j] == null)? "" : heap[j]);
			
			if (++column == itemsPerRow) {
				nBlanks /= 2;                 
				itemsPerRow *= 2;             
				column = 0;                   
				System.out.println();         
			}
			else                             
				for (int k = 0; k < nBlanks * 2 - 2; k++)
					System.out.print(' ');
			
			j++;
		}
		System.out.println("\n" + dots + dots); 
	}
}
