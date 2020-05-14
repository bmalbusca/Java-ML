package FileIO;

import java.util.ArrayList;

public class Dataset {
	
	protected int N_size = -1;		// data set size or number of rows starts as empty  
	protected int N_classes=0; 		// Number of values C can take -> also "s"
	protected int Nc[];            	// Number of instances where C takes its c-th value
	protected double thetac[];		
	protected ArrayList<Integer> ri_val = new ArrayList<Integer>();		//contains ri value of each xi
	protected ArrayList<Instance> T = new	ArrayList<Instance>();		
	
	public void add(Instance row){
		this.T.add(row);
	}
	
	public Instance getInstance(int row_id ) {
		return this.T.get(row_id);
	}
	
	/**
	 * Method to get the number of instances.
	 * 
	 * @return N_size Number of instances.
	 */
	public int getN_size() {
		return N_size;
	}
	
	/**
	 * Method to set the number of instaces in the database.
	 * 
	 * @param x What we set in the variable with the number of instances.
	 */
	public void setN_size(int x) {
	    this.N_size = x;
	}
	
	/**
	 * Method to get the number of different classes present in the file.
	 * 
	 * @return N_classes Number of classes.
	 */
	public int getN_classes() {
		return N_classes;
	}
	/**
	 * Method to set the number of different classes in the database.
	 * 
	 * @param x What we set in the variable with the number of classes.
	 */
	public void setN_classes(int x) {
	    this.N_classes = x;
	}
	
	/**
	 * Method to get the Arraylist of the ri values of each attribute.
	 * 
	 * @return ri_val Arraylist of ri values with position 0 to attibutes X1, 1 to X2, etc.
	 */
	public ArrayList<Integer> get_ri_val() {
		return ri_val;
	}
	
	/**
	 * Method to get the array with the number of ocurrences of each class.
	 * 
	 * @return Nc Array of int with the number of ocurrences of each class.
	 */
	public int[] getNc() {
		return Nc;
	}
	
	/**
	 * Method to get the array with the theta_c values of each class.
	 * 
	 * @return thetac Array of double with the values of theta_c of each class.
	 */
	public double[] get_thetac() {
		return thetac;
	}
	
	/**
	 * Method to set the array with the theta_c values of each class.
	 * 
	 * @param x Array of doubles that we will set on the array.
	 */
	public void set_thetac(double[] x) {
	    this.thetac = x;
	}

	public void print() {
		
		if (!T.isEmpty()) {
			for(int i = 0; i<= this.N_size; ++i) {
				this.T.get(i).print();	
			}
		}
		else {
			System.out.println("Data set is Empty!");
		}
	}

}
