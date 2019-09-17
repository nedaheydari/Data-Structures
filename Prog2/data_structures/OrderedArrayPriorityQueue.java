/*  Neda Heydari
    masc2172
*/
package data_structures;

import java.util.Iterator;

public class OrderedArrayPriorityQueue<E> implements PriorityQueue<E> {

	private OrderedArrayList<E> array;
	
	public OrderedArrayPriorityQueue(int size) {
		array = new OrderedArrayList<E>(size);
	}
	
	public OrderedArrayPriorityQueue() {
		array = new OrderedArrayList<E>(DEFAULT_MAX_CAPACITY);
	}
	
	@Override
	public boolean insert(E object) {
		if(isFull()) {
			return false;
		}
		array.insert(object);
		return true;
	}

	@Override
	public E remove() {
		return array.removeMin();
	}

	@Override
	public E peek() {
		if(isEmpty()) {
			return null;
		}
		return array.get(0);
	}

	@Override
	public boolean contains(E obj) {
		if(isEmpty())
			return false;
		return array.contains(obj);
	}

	@Override
	public int size() {
		return array.size();
	}

	@Override
	public void clear() {
		array.clear();
	}

	@Override
	public boolean isEmpty() {
		return array.isEmpty();
	}

	@Override
	public boolean isFull() {
		return array.isFull();
	}

	@Override
	public Iterator<E> iterator() {
		return array.iterator();
	}
}
