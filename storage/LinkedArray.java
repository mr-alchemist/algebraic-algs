package storage;

public class LinkedArray<T> /*implements IDynamicArray<T>*/ {
	private Queue<T> queue;
	private int size;
	
	public LinkedArray() {
		queue = new Queue<T>();
		size = 0;
	}
	
	//@Override
	public int size() {
		return size;
	}
	
	//@Override
	public void add(T item) {
		queue.enqueue(item);
		size++;
	}
	
	//@Override
	public T get(int index) {
		if(index >= size() )
			return null;
		
		Node<T> curr = queue.getHead();
		for (int i = 0; i < index; i++)
			curr = curr.getNext();
		
		return curr.getItem();
	}
	
	
}
