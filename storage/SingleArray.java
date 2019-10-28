package storage;

public class SingleArray<T> implements IDynamicArray<T> {
	
	Object[] array;
	
	public SingleArray() {
		array = new Object[0];
	}
	
	@Override
	public int size() {
		return array.length;
	}

	@Override
	public void add(T item) {
		resize_plus();
		array[size() - 1] = item;
	}
	
	private void resize_plus() {
		Object[] newArray = new Object[size() + 1];
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
		resize_plus(index);
		array[index] = item;
	}
	
	private void resize_plus(int index) {
		Object[] newArray = new Object[size() + 1];
		System.arraycopy(array, 0, newArray, 0, index);
		System.arraycopy(array, index, newArray, index+1, size() - index );
		array = newArray;
	}
	
	@Override
	public T remove(int index) {
		if(index >= size())throw new IndexOutOfBoundsException();
		T item = get(index);
		resize_minus(index);
		return item;
	}
	
	private void resize_minus(int index) {
		Object[] newArray = new Object[size() - 1];
		System.arraycopy(array, 0, newArray, 0, index);
		System.arraycopy(array, index+1, newArray, index, size() - index - 1);
		array = newArray;
	}
}

