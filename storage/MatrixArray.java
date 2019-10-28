package storage;

public class MatrixArray<T> implements IDynamicArray<T> {
	int size;
	int vector;
	private SingleArray<IDynamicArray<T>> array;
	
	public MatrixArray(int vector) {
		this.vector = vector;
		array = new SingleArray<>();
		size = 0;
	}
	
	public MatrixArray() {
		this(100);
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public void add(T item) {
		if(size == array.size() * vector)
			array.add(new VectorArray<>(vector));
		
		array.get(size / vector).add(item);
		size++;
	}

	@Override
	//@SuppressWarnings("unchecked")
	public T get(int index) {
		return (T)array.get(index / vector).get(index % vector);
	}
	
	@Override
	public void add(T item, int index) {
		if(index > size() || index < 0)
			throw new IndexOutOfBoundsException();
		
		if(size == array.size() * vector) 
			array.add(new VectorArray<>(vector));
		
		array.get(size / vector).add(null);
		
		int i = index / vector;
		int j = index % vector;
		
		//shift data
		VectorArray<T> rowI = (VectorArray<T>)array.get(i);
		T buf1 = rowI.get(vector - 1);
		System.arraycopy(rowI.array, j, rowI.array, j+1, vector - j -1);
		
		T buf2 = null;
		for(int k = i + 1; k < array.size(); k++) {
			VectorArray<T> row = (VectorArray<T>)array.get(k);
			buf2 = row.get(vector - 1);
			System.arraycopy(row.array, 0, row.array, 1, vector - 1);
			row.array[0] = buf1;
			buf1 = buf2;
		}
		rowI.array[j] = item;
		
		size++;
	}
	
	@Override
	public T remove(int index) {
		if(index >= size() || index < 0)throw new IndexOutOfBoundsException();
		
		T item = get(index);
		
		int i = index / vector;
		int j = index % vector;
		
		//shift data
		T buf1 = null;
		T buf2 = null;
		for(int k = array.size() - 1; k > i ; k--) {
			VectorArray<T> row = (VectorArray<T>)array.get(k);
			buf2 = row.get(0);
			System.arraycopy(row.array, 1, row.array, 0, vector - 1);
			row.array[vector - 1] = buf1;
			buf1 = buf2;
		}
		VectorArray<T> rowI = (VectorArray<T>)array.get(i);
		System.arraycopy(rowI.array, j + 1, rowI.array, j, vector - j - 1);
		rowI.array[vector - 1] = buf1;
		
		VectorArray<T> lastRow = (VectorArray<T>)array.get(array.size() - 1);
		lastRow.remove(lastRow.size-1);
		
		if((size() - 1) == (array.size() - 1)*vector)
			array.remove(array.size()-1);
		
		size--;
		return item;
	}
	
	public void printMatrix() {
		for(int i=0; i < array.size() ; i++) {
			VectorArray<T> row = (VectorArray<T>)array.get(i);
			String s = "";
			for(int j = 0; j < row.size() ; j++) 
				s += row.get(j).toString() + " ";
			
			System.out.println(s);
		}
	}

}
