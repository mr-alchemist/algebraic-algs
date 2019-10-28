package storage;

public class VectorArray<T> implements IDynamicArray<T> {
	
	Object[] array;
	int size;
	int vector;
	
	
	
	public VectorArray(int vector) {
		this.vector = vector;
		array = new Object[0];
		size = 0;
	}
	
	public VectorArray() {
		this(100);
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public void add(T item) {
		if(size() == array.length )
			resize_plus();
		array[size] = item;
		size++;
	}
	
	private void resize_plus() {
		Object[] newArray = new Object[size() + vector];
		System.arraycopy(array, 0, newArray, 0, size());
		array = newArray;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public T get(int index) {
		return (T)array[index];
	}
	
	@Override
	public void add(T item, int index) {
		if(index > size() || index < 0)throw new IndexOutOfBoundsException();
		if(size() == array.length ) {
			resize_plus(index);
		    array[index] = item;
		}
		else {
			/*for(int i = size() - 1; i >= index   ; i--) 
				array[i+1] = array[i];*/
			System.arraycopy(array, index, array, index + 1, size()-index);
			
			array[index] = item;
		}
		size++;
	}
	
	private void resize_plus(int index) {
		Object[] newArray = new Object[size() + vector];
		System.arraycopy(array, 0, newArray, 0, index);
		System.arraycopy(array, index, newArray, index + 1, size() - index);
		array = newArray;
	}
	
	@Override
	public T remove(int index) {
		if(index >= size())throw new IndexOutOfBoundsException();
		T item = get(index);
		if(array.length - vector == size() - 1  ) 
			resize_minus(index);
		
		else {
			System.arraycopy(array, index + 1, array, index, size() - index - 1);
			array[size() - 1] = null;
		}
		size--;
		return item;
	}
	
	private void resize_minus(int index) {
		Object[] newArray = new Object[array.length - vector];
		System.arraycopy(array, 0, newArray, 0, index);
		System.arraycopy(array, index+1, newArray, index, size() - index - 1);
		array = newArray;
	}
	
}
