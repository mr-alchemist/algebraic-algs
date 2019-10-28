package storage;

public class PriorityQueue<T> {

	FactorArray<Queue<T>> array;
	
	public PriorityQueue(){
		array = new FactorArray<Queue<T>>();
	}
	
	public void enqueue(int priority, T item) {
		while(array.size() - 1 < priority )
			array.add(null);
		
		Queue<T> q = array.get(priority);
		
		if(q == null) {
			q = new Queue<T>();
			array.array[priority] = q;
		}
		q.enqueue(item);
	}
	
	public T dequeue() {
		T res = null;
		
		for(int i = array.size() - 1; i >= 0; i--) {
			Queue<T> q = array.get(i);
			if(q == null || q.isEmpty()) {
				array.remove(i);
				continue;
			}
			res = q.dequeue();
			break;
		}
		
		return res;
	}
	
	
	
}
