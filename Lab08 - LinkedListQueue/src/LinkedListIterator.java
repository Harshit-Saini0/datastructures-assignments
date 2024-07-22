import java.util.Iterator;

public class LinkedListIterator<T> implements Iterator<T> {
	
	MyLinkedList<T>.ListNode cursor;
	public LinkedListIterator(MyLinkedList<T> myLinkedList) {
		cursor = myLinkedList.head;
	}

	public boolean hasNext() {
		return cursor != null;
	}

	public T next() {
		T val = cursor.val;
		cursor = cursor.next;
		return val;
	}

}
