package data_structures;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnorderedList<E> implements Iterable<E>{

	class Node<E> {
		E data;
		Node<E> next;
		public Node(E object) {
			data = object;
			next = null;
		}
	}

	private Node<E> head; 
	private int currentSize = 0;
	private long modCheck = 0;

	public boolean insert(E object) {		
		Node<E> newNode = new Node<E>(object);		
		Node<E> current = head;
		head = newNode;
		newNode.next = current;
		modCheck++;
		currentSize++;	
		return true;
	}

	public E remove(E object) {		
		if(isEmpty()){
			return null;
		}
		Node<E> previous = null, current = head;
		E tmp = object;
		while(current != null) {
			if(((Comparable<E>)object).compareTo(current.data) == 0) {
				if(current == head) {
					head = current.next;
				} else previous.next = current.next;
				modCheck++;
				currentSize--;
				return tmp;
			}
			previous = current;
			current = current.next;
		}
		return null;
	}	

	public E removeFirst() {		
		if(isEmpty()){
			return null;
		}
		E tmp = head.data;
		head = head.next;
		modCheck++;
		currentSize--;
		return tmp;
	}

	public void clear() {
		head = null;
		currentSize = 0;
		modCheck++;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public int size() {
		return currentSize;
	}

	public boolean contains(E obj) {
		if(find(obj) != null) {
			return false;
		}
		return true;
	}

	public E find(E tmp) {
		Node<E> current = head;
		while(current != null) {
			if(((Comparable<E>)tmp).compareTo(current.data) == 0) {
				return current.data;
			}
			current = current.next;
		}
		return null;
	}

	public Iterator<E> iterator() {
		return new IteratorHelper();
	}

	public class IteratorHelper implements Iterator<E> {	
		Node<E> iterPointer;
		long modCounter;

		public IteratorHelper() {
			iterPointer = head;
			modCounter = modCheck;
		}	
		@Override
		public boolean hasNext() {		
			if(modCounter != modCheck) {
				throw new ConcurrentModificationException();	
			}
			return iterPointer != null;
		}
		@Override
		public E next() {		
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			E tmp = iterPointer.data;
			iterPointer = iterPointer.next;
			return tmp;
		}
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}	
	}
}