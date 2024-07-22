public class MyQueue<T> implements QueueADT<T> {
	MyLinkedList<T> queue;
	
	public MyQueue() {
		queue = new MyLinkedList<T>();
	}
	
	@SuppressWarnings("unchecked")
	public MyQueue(T... vals) {
		queue = new MyLinkedList<T>(vals);
	}
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	public void offer(T item) {
		queue.add(item);
	}
		
	public T poll() {
		return queue.remove(0);
	}
	
	public int size() {
		return queue.size();
	}
	
	public void clear() {
		while(!queue.isEmpty()) {
			poll();
		}
	}	

	public T peek() {
		return null;
	}
}
