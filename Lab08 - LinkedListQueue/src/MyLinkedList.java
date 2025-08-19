import java.util.Iterator;

public class MyLinkedList<T> implements Iterable<T> {
	ListNode head;
	ListNode tail;
	private int size;

	public class ListNode {
		T val;
		ListNode next;

		public ListNode(T val) {
			this.val = val;
		}

		public String toString() {
			return "" + val;
		}
	}

	public MyLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	public MyLinkedList(T val) {
		head = new ListNode(val);
		tail = head;
		size = 1;
	}
	
	@SuppressWarnings("unchecked")
	public MyLinkedList( T... vals) {
		size = 0;
		head = null;
		tail = null;
		for(T val: vals) {
			add(val);
		}
	}

	public void add(T newVal) {
		if (head == null) {
			head = new ListNode(newVal);
			tail = head;
		} 
		else {
			/*
			 * ListNode temp = head;
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = new ListNode(newVal);
			tail = tail.next;
			 */
			tail.next = new ListNode(newVal);
			tail = tail.next;
		}
		size++;
	}

	public boolean contains(T target) {
		ListNode node = head;
		while(node != null) {
			if(node.val == target) {
				return true;
			}
			node = node.next;
		}
		return false;
	}

	public T get(int index) {
		if(index < 0) {
			throw new IndexOutOfBoundsException();
		}
		int i = 0;
		ListNode node = head;
		while(node != null) {
			if(i == index) {
				return node.val;
			}
			node = node.next;
			i++;
		}
		throw new IndexOutOfBoundsException();
	}

	public int indexOf(T target) {
		int i = 0;
		ListNode node = head;
		while(node != null) {
			if(node.val == target) {
				return i;
			}
			node = node.next;
			i++;
		}
		return -1;
	}

	public void set(T newVal, int index) {
		if(index < 0) {
			throw new IndexOutOfBoundsException();
		}

		int i = 0;
		ListNode node = head;
		while(node != null) {
			if(i == index) {
				node.val = newVal;
				return;
			}
			node = node.next;
			i++;
		}
		throw new IndexOutOfBoundsException();
	}

	public int size() {
		/*
		int size = 0;
		ListNode node = head;
		while(node != null) {
			size++;
			node = node.next;
		}
		*/
		return size;
	}

	public int sizeRecursive(ListNode node) {
		if(node == null) {
			return 0;
		}
		return sizeRecursive(node.next) + 1;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public T remove(int index) {
		T removedVal = null;
		if(index < 0) {
			throw new IndexOutOfBoundsException();
		}
		else if(index == 0) {
			removedVal = head.val;
			head = head.next;
		}
		else {
			ListNode node = head;
			int i = 0;
			while(node != null) {
				//index before
				if(i == index - 1) {
					if(node.next == null) {
						throw new IndexOutOfBoundsException();
					}
					removedVal = node.next.val;
					node.next = node.next.next;
				}
				node = node.next;
				i++;
			}
		}
		size--;
		return removedVal;
	}

	public void add(T newVal, int index) {
		if(index < 0) {
			throw new IndexOutOfBoundsException();
		}
		else if(isEmpty()) {
			head = new ListNode(newVal);
		}
		else {
			ListNode node = head;
			int i = 0;
			while(node != null) {
				if(i == index && i == 0) {
					ListNode nextNode = head;
					head = new ListNode(newVal);
					head.next = nextNode;
				}
				else if(i == index - 1) {
					ListNode nextNode = node.next;
					node.next = new ListNode(newVal);
					node.next.next = nextNode;
				}
				node = node.next;
				i++;
			}
		}
		size++;
	}

	public String toString() {
		String str = "[";
		ListNode node = head;
		if(isEmpty()) {
			return str += "]";
		}
		while(node != null) {
			str = str + node.val + ", ";
			node = node.next;
		}
		return str.substring(0, str.length() - 2) + "]";
	}

	public Iterator<T> iterator() {
		return new LinkedListIterator<T>(this);
	}
}
