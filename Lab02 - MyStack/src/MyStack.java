import java.util.EmptyStackException;

public class MyStack {
	Integer[] stack;
	int size;

	public MyStack() {
		this(7);
	}

	public MyStack(int initCap) {
		stack = new Integer[initCap];
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Integer peek() {

		if(isEmpty())
			throw new EmptyStackException();

		return stack[size-1];
	}

	public Integer pop() {

		if(isEmpty())
			throw new EmptyStackException();
		
		size--;
		return stack[size];
	}
	
	public void push(Integer item) {
		if(size + 1 > stack.length) {
			doubleCapacity();
		}
		
		stack[size] = item;
		size++;
	}

	private void doubleCapacity() {
		Integer[] newStack = new Integer[size*2];
		for(int i = 0; i < size; i++) {
			newStack[i] = stack[i];
		}
		stack = newStack;
	}
	
	public String toString() {
		String stackStr = "";
		for(int i = size - 1; i >= 0; i--) {
			if(i == size - 1) {
				stackStr = stackStr + stack[i] + "  <----- TOP\n";
			}
			else {
				stackStr = stackStr + stack[i] + "\n";
			}
		}
		stackStr += "_______________";
		return stackStr;
	}

}
