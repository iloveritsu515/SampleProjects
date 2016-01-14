package data_structures;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

public class RedBlackTree<K,V> implements DictionaryADT<K,V> {
	TreeMap<K,V> tree;
	int maxSize;

	public RedBlackTree() {
		maxSize = 100000;
		tree = new TreeMap<K,V>();
		}

	public RedBlackTree(int n) {
		maxSize = n;
		tree = new TreeMap<K,V>();
	}

	public boolean contains(K key) {
		return tree.containsKey(key);
	}

	public boolean add(K key, V value) {
		if (this.isFull()) return false;
		if (this.contains(key)) return false;
		tree.put (key,value);
		return true;
	}

	public boolean delete(K key) {
		V status = tree.remove(key);
		if (status != null) return true;
		else
			return false;
	}

	public V getValue(K key) {
		return tree.get(key);
	}

	public K getKey(V value) {
		for(Entry<K, V> entry : tree.entrySet()) {
			if(value.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
	}

	public int size() {
		return tree.size();
	}

	public boolean isFull() {
		return(this.size() >= this.maxSize);
	}

	public boolean isEmpty() {
		return (this.size() < 1);
	}

	public void clear() {
		tree.clear();
	}

	public Iterator<K> keys() {
		return tree.keySet().iterator();
	}

	public Iterator<V> values() {
		return tree.values().iterator();
	}

}
