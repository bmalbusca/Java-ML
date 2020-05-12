package FileIO;

import java.util.ArrayList;
import java.util.Collections;



public class Instance {

	public ArrayList<String> row = new ArrayList<String>();
	
	
	
	/* Example for method add(String )
	*	for (String cell: data ) {
	*		row.add(cell);
	*	}
	*/
	
	
	public void add(String var) {
		row.add(var); 
	}
	
	public void addAll(String[] line) {

		Collections.addAll(row, line);
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
