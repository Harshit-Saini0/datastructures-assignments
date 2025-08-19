import java.util.Iterator;

public class LinkedListIterator<T> implements Iterator<Integer> {
	
	MyLinkedList.ListNode cursor;
	public LinkedListIterator(MyLinkedList myLinkedList) {
		cursor = myLinkedList.head;
	}

	public boolean hasNext() {
		return cursor != null;
	}

	public Integer next() {
		Integer val = cursor.val;
		cursor = cursor.next;
		return val;
	}

}
