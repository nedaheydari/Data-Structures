/*  Neda Heydari
    masc2172
 */
package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

import data_structures.OrderedArrayList.IteratorHelper;

public class OrderedArrayList<E> implements OrderedListADT<E>{

	private int maxSize, currentSize;
	private E[] storage;

	public OrderedArrayList(int size) {
		maxSize = size;
		currentSize = 0;
		storage = (E[]) new Object[maxSize];
	}

	@Override
	public void insert(E obj) {
		if(isFull()) {
			throw new RuntimeException();
		}
		int where = findInsertionPoint (obj,0,currentSize-1) ;	//call binSearch
		for(int i = currentSize - 1; i >= where; i--) {			//shifting 
			storage[i+1] = storage[i];

		}
		storage[where] = obj;
		currentSize++;
	}

	private int findInsertionPoint(E obj, int lo, int hi) {		//binSearch
		if(hi<lo) return lo;
		int mid = (lo + hi) / 2;
		if(((Comparable<E>)obj).compareTo(storage[mid]) < 0) {
			return findInsertionPoint(obj, lo, mid-1);
		}
		return findInsertionPoint(obj, mid+1, hi);
	}


	@Override
	public E remove(int index) {
		E tmp = storage[index];
		for(int i = index; i < currentSize-1; i++) {			
			storage[i] = storage[i+1];
		}
		currentSize--;
		return tmp;
	}

	@Override
	public E remove(E obj) {
		if(! contains(obj)) {
			return null;
		}
		int index = find(obj);		//index is the index that has the object
		for(int i = index; i < currentSize-1; i++) {			
			storage[i] = storage[i+1];
		}
		currentSize--;
		return obj;
	}

	@Override
	public E removeMin() {
		if(isEmpty()) {
			return null;
		}
		E tmp = storage[0];
		for(int i = 0; i < currentSize-1; i++) {
			storage[i] = storage[i+1];
		}
		currentSize--;
		return tmp;
	}

	@Override
	public E removeMax() {
		if(isEmpty()) {
			return null;
		}
		E tmp = storage[currentSize - 1];	//update size
		storage[currentSize - 1] = null;
		currentSize--;
		return tmp;
	}

	@Override
	public E get(int index) {
		if(index < 0 || index >= currentSize) {
			throw new IndexOutOfBoundsException();
		}
		E obj = storage[index];
		return obj;
	}


	@Override
	public E get(E obj) {
		if(isEmpty() || ! contains(obj)) {
			return null;
		}
		int index = find(obj);
		if(index == -1) {
			return storage[0];
		}
		storage[index] = obj;
		return obj;
	}

	@Override
	public int find(E obj) {
		return findHelper(obj, 0, currentSize-1);	
	}

	private int findHelper(E obj, int lo, int hi) {
		if(hi == lo) {
			if(((Comparable<E>)obj).compareTo(storage[lo]) == 0) {
				return lo;
			}
			return -1;
		}
		int mid = (lo + hi) / 2;
		if(((Comparable<E>)obj).compareTo(storage[mid]) <= 0) {
			return findHelper(obj, lo, mid);
		}
		return findHelper(obj, mid+1, hi);
	}


	@Override
	public boolean contains(E obj) {
		return find(obj) != -1;
	}

	@Override
	public void clear() {
		currentSize = 0;
	}

	@Override
	public boolean isEmpty() {
		return currentSize == 0;
	}

	@Override
	public boolean isFull() {
		return currentSize == maxSize;
	}


	@Override
	public int size() {
		return currentSize;
	}

	@Override
	public Iterator<E> iterator() {
		return new IteratorHelper();
	}

	public class IteratorHelper implements Iterator<E> {
		int iterIndex;

		@Override
		public boolean hasNext() {
			return iterIndex < currentSize;
		}

		@Override
		public E next() {
			if(!hasNext()) 
				throw new NoSuchElementException ();
			return storage[iterIndex++];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();	
		}	
	}
}