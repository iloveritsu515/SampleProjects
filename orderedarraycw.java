package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

	public class OrderedArrayDictionary<K,V> implements DictionaryADT<K,V> {

		class DictionaryNode<K,V> implements Comparable<DictionaryNode<K,V>> {
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


		private int currentSize;
		private DictionaryNode<K, V>[] storage;
		private int maxSize;
		public OrderedArrayDictionary() {
			this(100);
}

		public OrderedArrayDictionary(int maxSize) {
			this.maxSize = maxSize;
			currentSize = 0;
			storage = new DictionaryNode[maxSize];
}

		public boolean add(K key, V value) {
			if (isFull()) {
				return false;
}
			int where = insertionPoint(key, 0, currentSize - 1);
			if (where == -1)
				return false;
			DictionaryNode<K, V>[] newSortedArray = new DictionaryNode[storage.length + 1];
			System.arraycopy(storage, 0, newSortedArray, 0, where);
			System.arraycopy(storage, where, newSortedArray, where + 1, storage.length - where);
			newSortedArray[where] = new DictionaryNode<K, V>(key, value);
			storage = newSortedArray;
			currentSize++;
			return true;
}

		public void clear() {
			currentSize = 0;
}

		public boolean contains(K key) {
			int where = insertionPoint(key, 0, currentSize - 1);
			return (where == -1);
}

		public boolean delete(K key) {
			int index = getIndex(key);
			int clip = index - 1;
			if (index == 0)
				clip = 0;
			DictionaryNode<K, V>[] newArray = new DictionaryNode[maxSize];
			System.arraycopy(storage, 0, newArray, 0, clip);
			System.arraycopy(storage, index + 1, newArray, index, (currentSize - clip) - 1);
			storage = newArray;
			currentSize--;
			return true;
}


		public int getIndex(K key) {
			for (int i = 0; i < currentSize; i++) {
				if (((Comparable<K>)storage[i].key).compareTo(key) == 0) {
					return i;
}
}
			return -1;
}

		public K getKey(V value) {
			if (isEmpty() || value == null) return null;
			for (int i = 0; i < currentSize; i++) {
				DictionaryNode<K, V> val = storage[i];
				if (val.value.equals(value)) {
					return val.key;
}
}
			return null;
}

		public V getValue(K key) {
			if (isEmpty() || key == null)
				return null;
			for (int i = 0; i < currentSize; i++) {
				if (((Comparable<K>) storage[i].key).compareTo(key) == 0) {
					return storage[i].value;
}
}
			return null;
}

		public boolean isEmpty() {
			return (size() < 1);
}

		public boolean isFull() {
			return currentSize == maxSize;
}

		public Iterator<K> keys() {
			return new KeysIterator();
}

		public int size() {
			return currentSize;
}

		public Iterator<V> values() {
			return new ValuesIterator();
}
		public class KeysIterator implements Iterator<K> {
			DictionaryNode<K, V>[] collection = new DictionaryNode[currentSize];
			private int idx;
			public KeysIterator() {
				for (int i = 0; i < currentSize; i++) {
					collection[i] = new DictionaryNode<K, V>(storage[i].key, storage[i].value);
				}
				Sorters.quicksort(collection);
}

			public boolean hasNext() {
				return idx < currentSize;
}

			public K next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
}
				return collection[idx++].key;
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

		public class ValuesIterator implements Iterator<V> {
			DictionaryNode<K, V>[] collection = new DictionaryNode[currentSize];
			private int idx;
			public ValuesIterator() {
				for (int i = 0; i < currentSize; i++) {
					collection[i] = new DictionaryNode<K, V>(storage[i].key, storage[i].value);
}
				Sorters.quicksort(collection);
}

			public boolean hasNext() {
				return idx < currentSize;
}

			public V next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
}
				return collection[idx++].value;
}

			public void remove() {
				throw new UnsupportedOperationException();
}
}

		private int binarySearch(K k, int lo, int hi) {
			if (hi < lo) return -1;
			int mid = (lo + hi) >> 1; //(lo+h1)/2
			int tmp = ((Comparable<K>) k).compareTo(storage[mid].key);
			if (tmp == 0) return mid;
			if (tmp < 0)
				return binarySearch(k, lo, mid - 1);
			return binarySearch(k, mid + 1, hi);
}
		public int insertionPoint(K k, int lo, int hi) {
			if (hi < lo) {
				return lo;
}
			int mid = (lo + hi) >> 1;
			if (((Comparable<K>) k).compareTo(storage[mid].key) == 0) {
				return -1;
}
			if (((Comparable<K>) k).compareTo(storage[mid].key) < 0) {
				return insertionPoint(k, lo, mid - 1);
}
			return insertionPoint(k, mid + 1, hi);
}
	}
