import java.util.ArrayList;

public class MyHashTable<K, V> {
	EntryGeneric<K,V>[] entries;
	int size;


	@SuppressWarnings("unchecked")
	public MyHashTable() {
		entries = new EntryGeneric[16];
		size = 0;
	}

	@SuppressWarnings("unchecked")
	public MyHashTable(int initCapacity) {
		entries = new EntryGeneric[initCapacity];
		size = 0;
	}

	private int getIndex(EntryGeneric<K,V> e) {
		return Math.abs(e.hashCode()) % entries.length;
	}

	public V put(K key, V value) {
		EntryGeneric<K,V> entry = new EntryGeneric<K,V>(key, value);
		V previousEntryValue = null;
		if(entries[this.getIndex(entry)] == null) {
			//hash slot is empty
			entries[this.getIndex(entry)] = entry;
			size++;
		}
		else {
			//hash slot has entries
			EntryGeneric<K, V> list = entries[this.getIndex(entry)];
			EntryGeneric<K,V> previousEntry = null;
			while(list != null && !list.key.equals(entry.key)) {
				previousEntry = list;
				list = list.next;
			}
			//reaches end of entries without dupes
			if(list == null) {
				previousEntry.next = entry;
				size++;
			}
			//replace dupe
			else if(list.key.equals(entry.key)) {
				previousEntryValue = list.value;
				EntryGeneric<K,V> nextEntry = list.next;
				if(previousEntry != null) {
					previousEntry.next = entry;
				}
				else {
					entries[this.getIndex(entry)] = entry;
				}
				entry.next = nextEntry;
			}
		}

		if((((double) size) / ((double) entries.length)) > 0.75) {
			rehash();
		}

		return previousEntryValue;
	}

	@SuppressWarnings("unchecked")
	private void rehash() {
		ArrayList<EntryGeneric<K,V>> temp = new ArrayList<>();
		for(EntryGeneric<K,V> e: entries) {
			while(e != null) {
				temp.add(e);
				e = e.next;
			}
		}
		entries = new EntryGeneric[entries.length * 2];
		size = 0;
		while(!temp.isEmpty()) {
			EntryGeneric<K,V> e = temp.remove(0);
			this.put(e.key, e.value);
		}
	}

	public V get(K key) {
		EntryGeneric<K,V> entry = new EntryGeneric<K, V>(key);
		EntryGeneric<K,V> list = entries[this.getIndex(entry)];
		V retValue = null;
		if(list != null) {
			while(!list.key.equals(key) && list.next != null) {
				list = list.next;
			}
			if(list.key.equals(key)) {
				retValue = list.value;
			}
		}
		return retValue;
	}

	public V remove(K key) {
		EntryGeneric<K,V> entry = new EntryGeneric<K,V>(key);
		EntryGeneric<K,V> list = entries[this.getIndex(entry)];
		V previousEntryValue = null;
		EntryGeneric<K,V> previousEntry = null;
		while(list != null && !list.key.equals(key)) {
			previousEntry = list;
			list = list.next;
		}
		if(list != null) {
			previousEntryValue = list.value;
			if(previousEntry != null) {
				previousEntry.next = list.next;
			}
			else {
				entries[this.getIndex(entry)] = list.next;
			}
			size--;
		}
		return previousEntryValue;
	}

	public int size() {
		return size;
	}

	public String toString() {
		String str = "[";
		for(EntryGeneric<K,V> e: entries) {
			if(e != null) {
				str += e.toString() + "-";
			}
			else {
				str += null + "-";
			}
		}
		return str.substring(0,str.length()-1) + "]";
	}

}
