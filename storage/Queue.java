package storage;

public class Queue<T> {
	private Node<T> head;
	private Node<T> tail;
	
	public Queue() {
		head = null;
		tail = null;
	}
	
	public Node<T> getHead(){
		return head;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public void enqueue(T item) {
		Node<T> node = new Node<T>(item);
		if(isEmpty()) 
			head = node;
		else 
			tail.setNext(node);
		
		tail = node;
	}
	
	public T dequeue() {
		if(isEmpty())
			return null;
		
		T item = head.getItem();
		
		if(head == tail) {
			head = null;
			tail = null;
		}
		else {
			head = head.getNext();
		}
		
		return item;
	}
}
