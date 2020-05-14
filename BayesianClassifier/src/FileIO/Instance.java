package FileIO;

import java.util.ArrayList;
import java.util.Collections;



public class Instance {

	protected int size=0;
	public ArrayList<String> row = new ArrayList<String>();
	
	
	
	/* Example for method add(String )
	*	for (String cell: data ) {
	*		row.add(cell);
	*	}
	*/
	
	
	public void add(String var) {
		row.add(var);
		++size;
	}
	
	
	public void addAll(String[] line) {

		Collections.addAll(row, line);
		size = line.length;
	}
	
	public void copy(Instance data, int len) {
		
			for(int i=0; i <len; ++i) {
		
				row.add(i,data.get(i));
			
			}
		
		/*	if(size ==0) { 
			System.out.println("*Alert* This instance is not empty!");
			
		}*/
	}
	
	
	public void set(int index,String value) {
		if(!row.isEmpty()) {
		
		row.set(index, value);
		}
		else {
			System.out.println("Alert* This isntance is empty!");
		}
	}
	
	public String get(int index) {
		return row.get(index);
	}
	
	public ArrayList<String> getAll() {
		return row;
	}
	
	
	public int len() {
		return row.size();
	}
	
	public void print() {
		System.out.println(this.row);
	}
}
