package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

	public class BinarySearchTree<K,V> implements DictionaryADT<K,V> {
		private DictionaryNode<K,V> root;
		private int currentSize;
		private int modCounter;

		public BinarySearchTree() {
			root = null;
			currentSize = 0;
			modCounter = 0;
		}

		public BinarySearchTree(int n){
			root = null;
			modCounter = 0;
			currentSize = 0;
		}

		private class DictionaryNode<K,V> {
			private K key;
			private V value;
			private DictionaryNode<K,V> leftChild;
			private DictionaryNode<K,V> rightChild;
			public DictionaryNode(K k, V v) {
				key = k;
				value = v;
				leftChild = rightChild = null;
			}
		}

		public boolean contains(K key) {
			if(root == null) return false;
			Iterator<K> keys = this.keys();
			while (keys.hasNext()) {
				if (keys.next().equals(key))
					return true;
			}
			return false;
		}

		public boolean add(K k, V v) {
			if (root == null)
				root = new DictionaryNode<K,V>(k,v);
			else
				insert(k,v,root,null,false);
			currentSize++;
			modCounter++;
			return true;
		}

		private void insert(K k, V v, DictionaryNode<K,V> n, DictionaryNode<K,V> parent, boolean wasLeft) {
			if (n == null) {
				if (wasLeft) parent.leftChild = new DictionaryNode<K,V>(k,v);
				else
					parent.rightChild = new DictionaryNode<K,V>(k,v);
			}
			else if (((Comparable<K>)k).compareTo((K)n.key) < 0)
				insert(k,v,n.leftChild,n,true);
			else
				insert(k,v,n.rightChild,n,false);
		}


		public boolean delete(K key) {
				return remove(root,key);
				}

			private boolean remove(DictionaryNode<K,V> curr, K key) {
				DictionaryNode<K,V> parent = null;
				DictionaryNode<K,V> child = null;
				while (curr != null && ((Comparable<K>)key).compareTo((K)curr.key) != 0) {
					parent = curr;
					if (((Comparable<K>)key).compareTo((K)curr.key) < 0)
						curr = curr.leftChild;
					else
						curr = curr.rightChild;
					}
				if (curr == null)
					return false;
				if (curr.leftChild == null && curr.rightChild == null) {
					if (parent == null)
						root = null;
					else if(((Comparable<K>) key).compareTo(parent.key) < 0)
						parent.leftChild = null;
					else
						parent.rightChild = null;
					}
				else if (curr.leftChild != null && curr.rightChild == null) {
					child = curr.leftChild;
					copy(curr,child);
					curr.leftChild = child.leftChild;
					curr.rightChild = child.rightChild;
					child.leftChild = null;
					child.rightChild = null;
					}
				else if (curr.leftChild == null && curr.rightChild != null) {
					child = curr.rightChild;
					copy(curr,child);
					curr.leftChild = child.leftChild;
					curr.rightChild = child.rightChild;
					child.leftChild = null;
					child.rightChild = null;
					}
				else {
					child = curr.rightChild;
					parent = null;
					while (child.leftChild != null) {
						parent = child;
						child = child.leftChild;
						}
					if (parent == null) {
						copy(curr,child);
						curr.rightChild = child.rightChild;
						child.leftChild = null;
						child.rightChild = null;
						}
					else {
						copy(curr,child);
						parent.leftChild = child.rightChild;
						child.leftChild = null;
						child.rightChild = null;
						}
					}
				currentSize--;
				modCounter++;
				return true;
				}
			private void copy(DictionaryNode<K,V> curr, DictionaryNode<K,V> child) {
				curr.key = child.key;
				curr.value = child.value;
				}

		public V getValue(K key) {
			return find(key,root);
		}
		private V find(K key, DictionaryNode<K,V> n) {
			if (n == null)
				return null;
			if (((Comparable<K>)key).compareTo(n.key) < 0) {
				return find(key, n.leftChild);
			}
			else if (((Comparable<K>)key).compareTo(n.key) > 0) {
				return find(key, n.rightChild);
			}
			else
				return (V) n.value;
		}
		private DictionaryNode<K, V> find(K key, DictionaryNode<K, V> n, int dummy) {
			if (n == null) return null;
			if (((Comparable<K>)key).compareTo(n.key) < 0) {
				return find(key, n.leftChild, 0);
			}
			else if (((Comparable<K>)key).compareTo(n.key) > 0) {
				return find(key, n.rightChild, 0);
			}
			else
				return n;
}

		public K getKey(V value) {
			Iterator<K> k = keys();
			Iterator<V> v = values();
			while (k.hasNext()) {
				K tempK = k.next();
				V tempV = v.next();
				if (((Comparable<V>)tempV).compareTo(value) == 0)
					return tempK;
			}
			return null;
		}

		public int size() {
			return currentSize;
		}

		public boolean isFull() {
			return false;
		}

		public boolean isEmpty() {
			return this.size() < 1;
		}

		public void clear() {
			root = null;
			currentSize = 0;
		}

		public Iterator<K> keys() {
			return new KeyIteratorHelper();
		}

		public Iterator<V> values() {
			return new ValuesIterator();
		}

		public class KeyIteratorHelper implements Iterator<K> {
			K[] array;
			private int index;
			private int endIndex;
			public KeyIteratorHelper() {
				array = (K[]) new Object[currentSize];
				inOrderFillArray(root);
				index = 0;
				this.endIndex = array.length;//size();
			}
			private void inOrderFillArray(DictionaryNode<K, V> n) {
				if (n == null)
					return;
				inOrderFillArray(n.leftChild);
				array[index++] = (K) n.key; //this cast is questionable...
				inOrderFillArray(n.rightChild);
			}

			public boolean hasNext() {
				return (index < endIndex);
			}

			public K next() {
				if (hasNext() == false) {
					throw new NoSuchElementException();
				}
				return (K) array[index++];
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}
		}

		public class ValuesIterator implements Iterator<V> {
			V[] array;
			private int index;
			private int endIndex;
			public ValuesIterator() {
				array = (V[]) new Object[currentSize];
				inOrderFillArray(root);
				index = 0;
				this.endIndex = array.length;
			}
			private void inOrderFillArray(DictionaryNode<K, V> n) {
				if (n == null) return;
				inOrderFillArray(n.leftChild);
				array[index++] = (V) n.value;
				inOrderFillArray(n.rightChild);
			}

			public boolean hasNext() {
				return (index < endIndex);
			}

			public V next() {
				if (hasNext() == false) {
					throw new NoSuchElementException();
				}
				return (V) array[index++];
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}
		}
}
