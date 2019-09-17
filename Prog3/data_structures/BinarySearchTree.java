package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinarySearchTree<K,V> implements DictionaryADT<K,V> {

	class Node<K,V> {
		K key;
		V value;
		Node<K,V> left;
		Node<K,V> right;

		public Node(K k, V v) {
			key = k;
			value = v;
			left = right = null;
		}
	}	

	private Node<K,V> root;
	private int currentSize;
	private long modCounter;
	private K tmp;

	public BinarySearchTree() {
		root = null;
		currentSize = 0;
		modCounter = 0;
	}

	@Override
	public boolean contains(K key) {
		if(isEmpty()) 
			return false;
		if(findKey(key,root) != null)
			return true;
		return false;
	}

	@Override
	public boolean add(K key, V value) {
		Node<K,V> newNode = new Node<K,V>(key,value);
		if(root == null)
			root = newNode;
		else 
			if(!insert(key,value,root,null,false))
				return false;
		currentSize++;
		modCounter++;
		return true;
	}

	private boolean insert(K key, V value, Node<K,V> n, Node<K,V> parent, boolean wasLeft) {
		if(n == null) {
			if(wasLeft) 
				parent.left = new Node<K,V>(key,value);
			else parent.right = new Node<K,V>(key,value);
			return true;
		}
		else if(((Comparable<K>)key).compareTo(n.key) == 0)
			return false;
		else if(((Comparable<K>)key).compareTo((K)n.key) < 0)
			return insert(key,value,n.left,n,true);
		else 
			return insert(key,value,n.right,n,false);
	}

	@Override
	public boolean delete(K key) {		
		if(! delete(key,root,null,false))	
			return false;
		currentSize--;
		modCounter++;
		return true;
	}

	private boolean delete(K key, Node<K,V> n, Node<K,V> parent, boolean wentLeft) {
		if(n == null) 		
			return false;
		int comp = ((Comparable<K>)key).compareTo(n.key);
		if(comp < 0)	
			return delete(key, n.left, n, true);
		else if(comp > 0)		
			return delete(key,n.right,n,false);
		else {			
			if(n.left == null && n.right == null) {		
				if(parent == null) 
					root = null;				
				else if(wentLeft) 
					parent.left = null; 
				else parent.right = null;
			}else if(n.left == null) {				
				if(parent == null) 
					root = n.right;
				else if(wentLeft) 
					parent.left = n.right;
				else parent.right = n.right;
			}else if(n.right == null) {		
				if(parent == null) 
					root = n.left;
				else if(wentLeft) 
					parent.left = n.left;	
				else parent.right = n.left;
			}else {		
				Node<K,V> rightNode = n.right;
				Node<K,V> Successor = getSuccessor(n.right);
				if(parent == null) {
					if(Successor != null) {
						Successor.right = n.right;
						Successor.left = n.left;
						root = Successor;
					}	
					else{ 
						rightNode.left = root.left;
						root = n.right;
					}
				}else if(wentLeft) {
					if(Successor != null) {
						Successor.left = n.left;
						Successor.right = n.right;
						parent.left = Successor;
					}	
					else {
						rightNode.left = n.left;
						parent.left = n.right;
					}	
				} else {
					if(Successor != null) {
						Successor.right = n.right;
						Successor.left = n.left;
						parent.right = Successor;
					}	
					else{ 
						rightNode.left = n.left;
						parent.right = n.right;
					}
				}
			}
			return true;
		}		
	}

	private Node<K,V> getSuccessor(Node<K, V> n) {
		Node<K,V> parent = null;
		while(n.left != null) {
			parent = n;
			n = n.left;
		}
		Node<K,V> temp = n;
		if(parent == null) 
			return null;
		else parent.left = n.right;
		return temp;
	}

	@Override
	public V getValue(K key) {
		return findKey(key,root);
	}

	private V findKey(K key, Node<K,V> n) {
		if(n == null) 
			return null;
		int comp = ((Comparable<K>)key).compareTo(n.key);
		if(comp < 0) 
			return findKey(key,n.left);
		else if(comp > 0) 
			return findKey(key,n.right);
		else return (V) n.value;
	}

	@Override
	public K getKey(V value) {
		tmp = null;
		findValue(value,root);
		return tmp;
	}

	private void findValue(V value, Node<K,V> n) {
		if(n == null)	
			return;
		findValue(value,n.left);
		if(((Comparable<V>)value).compareTo(n.value) == 0)
			if(tmp == null)
				tmp = n.key;	
		findValue(value,n.right);
	}

	@Override
	public int size() {
		return currentSize;
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public void clear() {
		currentSize = 0;
		modCounter = 0;
		root = null;
	}

	abstract class IteratorHelper<E> implements Iterator<E> {		
		Node<K,V> [] nodes;
		int index;
		int j;
		protected long modCheck;

		public IteratorHelper() {
			nodes = new Node[currentSize];
			inOrderFill(root);
			index = 0;
			j = 0;
			modCheck = modCounter;
		}	

		private void inOrderFill(Node<K,V> n) {
			if(n == null) return;
			inOrderFill(n.left);		
			nodes[j++] = n;
			inOrderFill(n.right);	
		}
		public boolean hasNext() {		
			if(modCheck != modCounter)
				throw new ConcurrentModificationException();
			return index < currentSize;
		}

		public abstract E next();

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public Iterator<K> keys() {		
		return new KeyIteratorHelper();
	}

	class KeyIteratorHelper<K> extends IteratorHelper<K> {
		public KeyIteratorHelper() {
			super();
		}
		public K next() {
			if(!hasNext()) 
				throw new NoSuchElementException();
			return (K) nodes[index++].key;
		}
	}

	@Override
	public Iterator<V> values() {
		return new ValueIteratorHelper();
	}

	class ValueIteratorHelper<V> extends IteratorHelper<V> {
		public ValueIteratorHelper() {
			super();
		}
		public V next() {
			if(!hasNext())
				throw new NoSuchElementException();
			return (V) nodes[index++].value;
		}
	}
}