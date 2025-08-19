import java.util.EmptyStackException;

public class MyStack implements StackADT {
	
	private Square[] stack;
	private int size;

	public MyStack() {
		this(7);
	}

	public MyStack(int initCap) {
		stack = new Square[initCap];
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Square peek() {

		if(isEmpty())
			throw new EmptyStackException();

		return stack[size-1];
	}

	public Square pop() {

		if(isEmpty())
			throw new EmptyStackException();
		
		size--;
		return stack[size];
	}
	
	public void push(Square item) {
		if(size + 1 > stack.length) {
			doubleCapacity();
		}
		
		stack[size] = item;
		size++;
	}

	private void doubleCapacity() {
		Square[] newStack = new Square[size*2];
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

	public int size() {
		return size;
	}

	public void clear() {
		stack = new Square[7];
		size = 0;
	}

}
