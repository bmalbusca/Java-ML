package fileIO;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class represents an instance/row from a data set.
 * @author Group 20
 *
 */
 

public class Instance {

	protected int size=0;
	protected ArrayList<String> row = new ArrayList<String>();
	
	

	/**
	 * method adds a value/item to the instance 
	 * @param var a value from of a data set row
	 */
	
	public void add(String var) {
		row.add(var);
		++size;
	}
	
	/**
	 * Add a entire row to a instance 
	 * @param line row/line from data set 
	 */
	public void addAll(String[] line) {

		Collections.addAll(row, line);
		size = line.length;
	}
	
	/**
	 * Copy the content from an instance
	 * @param data	instance type
	 * @param len	number of values to be copied
	 */
	public void copy(Instance data, int len) throws ArrayIndexOutOfBoundsException {
		
		try {
			for(int i=0; i <len; ++i) {
		
				row.add(i,data.get(i));
			
			}
		} catch(Exception ArrayIndexOutOfBoundsException) {
			System.out.println("Position does not exists");
		}
	}
	
	/**
	 * set a value into Instance by index
	 * @param index integer values that represent a position
	 * @param value	to set 
	 */
	public void set(int index,String value) {
		if(!row.isEmpty()) {
		
		row.set(index, value);
		}
		else {
			System.out.println("Alert* This isntance is empty!");
		}
	}
	
	/**
	 * Get the value of a instance  using a positioning index 
	 * @param index correspond to position of attribute in instance
	 * @return String value that exists at index position
	 */
	public String get(int index) {
		return row.get(index);
	}
	
	
	/**
	 * Returns the entire row values
	 * @return ArrayList of String
	 */
	public ArrayList<String> getAll() {
		return row;
	}
	
	/**
	 * Returns a instance length
	 * @return int size
	 */
	public int len() {
		return row.size();
	}
	
	
	/**
	 * Method to print the instance
	 */
	public void print() {
		System.out.println(this.row);
	}
}
