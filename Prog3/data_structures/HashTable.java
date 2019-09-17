package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashTable<K,V> implements DictionaryADT<K,V>{
	class DictionaryNode<K,V> implements Comparable <DictionaryNode<K,V>> {
		K key;
		V value;

		public DictionaryNode(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public int compareTo(DictionaryNode<K, V> node) {
			return ((Comparable<K>)key).compareTo(node.key);
		}
	}

	private int currentSize, maxSize, tableSize;
	public UnorderedList<DictionaryNode<K,V>>[] list;
	private long modCounter;

	public HashTable (int n) {
		currentSize = 0;
		maxSize = n;
		tableSize = (int)(maxSize*1.3f);
		list = new UnorderedList[tableSize];
		modCounter = 0;
		for(int i = 0; i<tableSize; i++) {
			list[i] = new UnorderedList<DictionaryNode<K,V>>();
		}
	}

	@Override
	public boolean contains(K key) {
		DictionaryNode<K,V> tmp = new DictionaryNode<K,V>(key, null);
		return list[getIndex(key)].find(tmp) != null;
	}

	@Override
	public boolean add(K key, V value) {
		if(isFull()) 
			return false;
		if(contains(key)) 
			return false;
		DictionaryNode<K,V> newNode = new DictionaryNode<K,V>(key,value);
		list[getIndex(key)].insert(newNode);
		modCounter++;
		currentSize++;
		return true;
	}

	private int getIndex(K key) {
		return(key.hashCode() & 0x7FFFFFFF) % tableSize;
	}

	@Override
	public boolean delete(K key) {
		if(isEmpty()) 
			return false;
		if(list[getIndex(key)].remove(new DictionaryNode<K,V>(key,null)) == null)
			return false;
		modCounter++;
		currentSize--;
		return true;
	}

	@Override
	public V getValue(K key) {
		DictionaryNode<K,V> tmp = new DictionaryNode<K,V>(key,null);
		DictionaryNode<K,V>returnValue = list[getIndex(key)].find(tmp);
		if(returnValue == null)
			return null;
		return returnValue.value;
	}

	@Override
	public K getKey(V value) {		
		for(int i = 0;i<tableSize; i++) 
			for(DictionaryNode n: list[i]) 
				if(((Comparable<V>)value).compareTo((V)n.value) == 0) 
					return (K)n.key;
		return null;
	}

	@Override
	public int size() {
		return currentSize;
	}

	@Override
	public boolean isFull() {
		return currentSize == maxSize;
	}

	@Override
	public boolean isEmpty() {
		return currentSize == 0;
	}

	@Override
	public void clear() {
		for(int i = 0; i< tableSize; i++) 		
			list[i].clear();
		modCounter++;
		currentSize = 0;
	}

	abstract class IteratorHelper<E> implements Iterator<E> {
		protected DictionaryNode<K,V> [] nodes;
		protected int idx;
		protected long modCheck;

		public IteratorHelper() {
			nodes = new DictionaryNode[currentSize];
			idx = 0;
			int j = 0;
			modCheck = modCounter;
			for(int i = 0; i<tableSize; i++)
				for(DictionaryNode<K,V> n: list[i])	
					nodes[j++] = n;
			nodes = (DictionaryNode<K,V>[]) shellSort(nodes);
		}
		
		public boolean hasNext() {
			if(modCheck != modCounter)
				throw new ConcurrentModificationException();
			return idx < currentSize;
		}
		
		public abstract E next();

		public void remove() {
			throw new UnsupportedOperationException();
		}

		private DictionaryNode<K,V> [] shellSort(DictionaryNode<K,V>[] array) {
			DictionaryNode<K, V>[] n = array;
			DictionaryNode<K, V> tmp;
			int in, out, h = 1;
			int size = n.length;
			while(h <= size/3 )		
				h = h*3+1;
			while(h > 0) {
				for(out=h; out < size; out++) {
					tmp = n[out];		
					in = out;		
					while(in > h-1 && ((Comparable<DictionaryNode<K,V>>)n[in - h]).compareTo(tmp) >= 0) {
						n[in] = n[in-h];
						in -= h;	
					}
					n[in] = tmp;
				}
				h = (h-1)/3;
			}
			return n;
		}
	}

	@Override
	public Iterator<K> keys() {				
		return new KeyIteratorHelper();
	}

	class KeyIteratorHelper extends IteratorHelper<K> {
		public KeyIteratorHelper() {
			super();
		}
		
		public K next() {
			if(!hasNext()) throw new NoSuchElementException();
			return (K) nodes[idx++].key;
		}
	}

	@Override
	public Iterator<V> values() {
		return new ValueIteratorHelper();
	}

	class ValueIteratorHelper extends IteratorHelper<V> {
		public ValueIteratorHelper() {
			super();
		}
		
		public V next() {
			return (V) nodes[idx++].value;
		}

	}
}
