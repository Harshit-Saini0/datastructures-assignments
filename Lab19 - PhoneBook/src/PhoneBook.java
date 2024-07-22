import java.util.ArrayList;

public class PhoneBook implements IMap {
	Entry[] entries;
	int size;


	public PhoneBook() {
		entries = new Entry[16];
		size = 0;
	}

	public PhoneBook(int initCapacity) {
		entries = new Entry[initCapacity];
		size = 0;
	}

	private int getIndex(Entry e) {
		return e.hashCode() % entries.length;
	}

	public PhoneNumber put(Person person, PhoneNumber phone) {
		Entry entry = new Entry(person, phone);
		PhoneNumber previousEntryNumber = null;
		if(entries[this.getIndex(entry)] == null) {
			//hash slot is empty
			entries[this.getIndex(entry)] = entry;
			size++;
		}
		else {
			//hash slot has entries
			Entry list = entries[this.getIndex(entry)];
			while(list.next != null && !list.next.person.equals(entry.person)) {
				list = list.next;
			}
			//reaches end of entries without dupes
			if(list.next == null) {
				list.next = entry;
				size++;
			}
			//replace dupe
			else if(list.next.person.equals(entry.person)) {
				previousEntryNumber = list.next.number;
				Entry nextEntry = list.next.next;
				list.next = entry;
				entry.next = nextEntry;
			}
		}

		if((((double) size) / ((double) entries.length)) > 0.75) {
			rehash();
		}

		return previousEntryNumber;
	}

	private void rehash() {
		ArrayList<Entry> temp = new ArrayList<>();
		for(Entry e: entries) {
			while(e != null) {
				temp.add(e);
				e = e.next;
			}
		}
		entries = new Entry[entries.length * 2];
		size = 0;
		while(!temp.isEmpty()) {
			Entry e = temp.remove(0);
			this.put(e.person, e.number);
		}
	}

	public PhoneNumber get(Person person) {
		Entry entry = new Entry(person);
		Entry list = entries[this.getIndex(entry)];
		PhoneNumber retNumber = null;
		if(list != null) {
			while(!list.person.equals(person) && list.next != null) {
				list = list.next;
			}
			if(list.person.equals(person)) {
				retNumber = list.number;
			}
		}
		return retNumber;
	}

	public PhoneNumber remove(Person person) {
		Entry entry = new Entry(person);
		Entry list = entries[this.getIndex(entry)];
		PhoneNumber previousEntryNumber = null;
		Entry previousEntry = null;
		while(list != null && !list.person.equals(person)) {
			previousEntry = list;
			list = list.next;
			System.out.println(list.person.equals(person));
		}
		if(list != null) {
			previousEntryNumber = list.number;
			if(previousEntry != null) {
				previousEntry.next = list.next;
			}
			else {
				entries[this.getIndex(entry)] = null;
			}
			size--;
		}
		return previousEntryNumber;
	}

	public int size() {
		return size;
	}

	public String toString() {
		String str = "[";
		for(Entry e: entries) {
			if(e != null) {
				str += e.toString() + "-";
			}
			else {
				str += null + "-";
			}
		}
		return str.substring(0,str.length()-1) + "]";
	}

	public static void main(String[] args) {
		PhoneBook p = new PhoneBook(5);
		p.put(new Person("John"), new PhoneNumber(2018712));
		p.put(new Person("JONY"), new PhoneNumber(121212));
		p.put(new Person("JO"), new PhoneNumber(34343));
		System.out.println(p);
		p.put(new Person("JOJO"), new PhoneNumber(12345));
		System.out.println(p.get(new Person("JOJO")));
		System.out.println(p.toString());
		System.out.println(p.remove(new Person("John")));
		System.out.println(p.toString());
	}
}
