package Model;

import java.io.Serializable;
import java.util.Vector;

public class Set<T extends Citizen> implements Serializable {

	private Vector <T> arr;

	
	public Set() {
		
		arr = new Vector<>(); 
	}

	
	public void add(Object object) {
		for (int i = 0; i < arr.size(); i++) {
			if (((Citizen) object).getID().equals(arr.get(i).getID())) {
				continue;
			}
		}
		arr.add((T) object);
	}
	
	
	public int size() {
		return this.arr.size();
	}
	
	
	
	public T get(int index){
		return this.arr.get(index);
	}
	
	
	public T remove(int i){
		return this.arr.remove(i);
	}
	
}

