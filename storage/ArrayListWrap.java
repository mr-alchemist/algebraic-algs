package storage;

import java.util.ArrayList;

public class ArrayListWrap<T> implements IDynamicArray<T> {
	
	@SuppressWarnings("all")
	ArrayList arrayList; 
	
	
	@SuppressWarnings("all")
	public ArrayListWrap() {
		arrayList = new ArrayList();
	}
	

	@Override
	public int size() {
		return arrayList.size();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void add(T item) {
		arrayList.add(item);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T get(int index) {
		return (T)arrayList.get(index);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void add(T item, int index) {
		arrayList.add(index, item);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T remove(int index) {
		return (T)arrayList.remove(index);
	}
	
}
