/*  Neda Heydari
    masc2172
*/
package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class OrderedListPriorityQueue<E> implements PriorityQueue<E> {
	
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
	
	@Override
	public boolean insert(E object) {
		Node<E> newNode = new Node<E>(object);		
		Node<E> previous = null, current = head;
		if(currentSize == 0) {
			head = newNode;
			modCheck++;
			currentSize++;	
			return true;
		} 
		if(currentSize == 1) {
			if(((Comparable<E>)object).compareTo(current.data) < 0) {
				newNode.next = head;
				head = newNode;
			} else {
			head.next = newNode;
			}
			modCheck++;
			currentSize++;	
			return true;
		} 
		while(current != null) {
			if(((Comparable<E>)object).compareTo(current.data) < 0) {
				if(head == current) {
					head = newNode;
					newNode.next = current;
				} else {
					previous.next = newNode;
					newNode.next = current;
				}					
				modCheck++;
				currentSize++;	
				return true;
			} else {
				previous = current;
				current = current.next;
			}
		}	
		previous.next = newNode;
		modCheck++;
		currentSize++;	
		return true;
	}

	@Override
	public E remove() {		//always remove head	
		if(isEmpty()){
			return null;
		}
		E tmp = head.data;
		head = head.next;
		modCheck++;
		currentSize--;
		return tmp;
	}

	@Override
	public E peek() {
		if(isEmpty()) {
			return null;
		}
		return head.data;
	}

	@Override
	public boolean contains(E obj) {
		Node<E> current = head;
		while(current != null) {
			if(((Comparable<E>)obj).compareTo(current.data) ==0) {
				return true;
			} 
			current = current.next;
		}
		return false;
	}

	@Override
	public int size() {
		return currentSize;
	}

	@Override
	public void clear() {
		head = null;
		currentSize = 0;
		modCheck++;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
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
