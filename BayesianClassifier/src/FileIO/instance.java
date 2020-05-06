package FileIO;

import java.util.ArrayList;
import java.util.Arrays;



public class instance {

	public ArrayList<String> row = new ArrayList<String>();
	
	public void add(String var) {
		row.add(var); 
	}
	
	public void addAll(String[] line) {
		
		row = (ArrayList<String>) Arrays.asList(line);
		
	}
	
	public String get(int index) {
		return row.get(index);
	}
	
	public ArrayList<String> getAll() {
		return row;
	}
	
	public void print() {
		System.out.println(this.row);
	}
}
