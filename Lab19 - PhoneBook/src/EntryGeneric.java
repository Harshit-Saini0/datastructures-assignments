public class EntryGeneric<K,V> {
	EntryGeneric<K,V> next;
	K key;
	V value;
	
	public EntryGeneric(K key, V value) {
		this.key = key;
		this.value = value;
		next = null;
	}
	
	public EntryGeneric(K key) {
		this.key = key;
		value = null;
		next = null;
	}
	
	public boolean equals(Object obj) {
		@SuppressWarnings("unchecked")
		EntryGeneric<K,V> other = (EntryGeneric<K,V>) obj;
		return this.key.equals(other.key) && this.value.equals(other.value);
	}
	
	public int hashCode() {
		return key.hashCode();
	}
	
	public String toString() {
		return "<" + key + ", " + value + ":" + next + ">";
	}

}
