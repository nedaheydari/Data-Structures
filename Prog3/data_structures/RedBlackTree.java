package data_structures;

import java.util.Iterator;
import java.util.*;

public class RedBlackTree<K,V> implements DictionaryADT<K,V> {
	private TreeMap<K,V> tree;
	
	public RedBlackTree() {
		tree = new TreeMap<K,V>();
	}
	
	@Override
	public boolean contains(K key) {
		return tree.containsKey(key);
	}

	@Override
	public boolean add(K key, V value) {
		if(tree.containsKey(key)) 
			return false;
		tree.put(key, value);
		return true;
	}

	@Override
	public boolean delete(K key) {
		return tree.remove(key) != null;
	}

	@Override
	public V getValue(K key) {
		return tree.get(key);
	}

	@Override
	public K getKey(V value) {
		Iterator<K> keyIter = keys();
		Iterator valueIter = values();
		while(keyIter.hasNext()) {
			K tmpK = keyIter.next();
			V tmpV = (V) valueIter.next();
			if(((Comparable<V>)value).compareTo(tmpV)==0)
				return tmpK;
		}
		return null;
	}

	@Override
	public int size() {
		return tree.size();
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return tree.size()==0;
	}

	@Override
	public void clear() {
		tree.clear();
	}

	@Override
	public Iterator<K> keys() {
		return tree.keySet().iterator();
	}

	@Override
	public Iterator<V> values() {
		return tree.values().iterator();
	}
}
