package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashTable<K, V> implements DictionaryADT<K, V> {

	private int currentSize, maxSize, tableSize;
	private UnorderedList<DictionaryNode<K, V>>[] list;
	public HashTable(int n) {
		currentSize = 0;
		maxSize = n;
		tableSize = (int) (maxSize * 1.3f);
		list = new UnorderedList[tableSize];
		for (int i = 0; i < tableSize; i++) {
			list[i] = new UnorderedList<DictionaryNode<K, V>>();
}
}
	public boolean add(K key, V value) {
		if (this.isFull()) {
			return false;
}
		DictionaryNode<K, V> newNode = new DictionaryNode<K, V>(key, value);
		int index = getIndex(key);
		list[index].insertLast(newNode);
		currentSize++;
		return true;
}

	public void clear() {
		currentSize = 0;
		list = new UnorderedList[tableSize];
		for (int i = 0; i < tableSize; i++) {
			list[i] = new UnorderedList<DictionaryNode<K, V>>();
}
}
	public boolean contains(K key) {
		UnorderedList<DictionaryNode<K, V>> value = find(key);
		if (value.getCurrentSize() > 0)
			return true;
		else
			return false;
}

	public boolean delete(K key) {
		if (this.contains(key)) {
			int index = getIndex(key);
			int children_count = list[index].getCurrentSize();
			list[index].clear();
			currentSize = currentSize - children_count;
			return true;

}
		return false;
}
	public UnorderedList<DictionaryNode<K, V>> find(K key) {
		int index = getIndex(key);
		UnorderedList<DictionaryNode<K, V>> value = this.list[index];
		return value;
}

	public int getIndex(K key) {
		return (key.hashCode() & 0x7FFFFFFF) % tableSize; // largest positive number in 32 bit hex
}

	public K getKey(V value) {
		for (int i = 0; i < list.length; i++) {
			Iterator<DictionaryNode<K, V>> list_items = list[i].iterator();
			while (list_items.hasNext()) {
				DictionaryNode<K, V> temp = list_items.next();
	if (temp != null && temp.value.equals(value))
	return temp.key;
}
}
		return null;
}

	public V getValue(K key) {
		int index = getIndex(key);
		DictionaryNode<K, V> fake_node = new DictionaryNode<K, V>(key, null);
		DictionaryNode<K, V> temp = list[index].find(fake_node);
	if (temp == null)
		return null;
	return temp.value;
}

	public boolean isEmpty() {
		return this.size() < 1;
}

	public boolean isFull() {
		return (this.size() >= this.maxSize);
}
	public Iterator<K> keys() {
		return new KeyIterator();
}

	public int size() {
		return currentSize;
}

	public Iterator<V> values() {
		return new ValueIterator();
}
	class DictionaryNode<K, V> implements Comparable<DictionaryNode<K, V>> {
		K key;
		V value;

		public DictionaryNode(K key, V value) {
			this.key = key;
			this.value = value;
}

		public int compareTo(DictionaryNode<K, V> node) {
			return ((Comparable<K>) key).compareTo(node.key);
		}
}


	public class KeyIterator implements Iterator<K> {
		private DictionaryNode<K, V>[] nodes;
		private int idx;
		public KeyIterator() {
			nodes = new DictionaryNode[currentSize];
			idx = 0;
			int j = 0;
			for (int i = 0; i < tableSize; i++){
				for (DictionaryNode<K, V> n : list[i]) {
					nodes[j++] = n;
}
			}
			Sorters.quicksort(nodes);
}

		public boolean hasNext() {
			return idx < currentSize;
}

		public K next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return nodes[idx++].key;
}

		public void remove() {
			throw new UnsupportedOperationException();
}
}

	private static class Sorters {
		static <T extends Comparable<? super T>> void quicksort(T[] array) {
			quicksort(array, 0, array.length - 1);
}
		static <T extends Comparable<? super T>> void quicksort(T[] array, int left0, int right0) {
			int left = left0;
			int right = right0 + 1;
			T pivot, temp;
			pivot = array[left0];
			do {
				do
					left++;
				while (left <= right0 && array[left].compareTo(pivot) < 0);
				do
					right--;
				while (array[right].compareTo(pivot) > 0);
				if (left < right) {
					temp = array[left];
					array[left] = array[right];
					array[right] = temp;
				}
			}
			while (left <= right);
			temp = array[left0];
			array[left0] = array[right];
			array[right] = temp;
			if (left0 < right)
				quicksort(array, left0, right);
			if (left < right0)
				quicksort(array, left, right0);
		}
	}

	public class ValueIterator implements Iterator<V> {
		private Iterator<K> k;
		public ValueIterator() {
			k = keys();
}
		public boolean hasNext() {
			return k.hasNext();
}
		public V next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
}
			return getValue(k.next());
}
		public void remove() {
			throw new UnsupportedOperationException();
	}
}
}
