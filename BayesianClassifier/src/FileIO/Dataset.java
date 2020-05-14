package FileIO;

import java.util.ArrayList;

public class Dataset {
	
	protected int N_size = -1;		// data set size or number of rows starts as empty  
	protected int N_classes=0; 	// Number of values C can take -> also "s"
	protected int Nc[];            // Number of instances where C takes its c-th value
	protected double thetac[];
	protected ArrayList<Integer> ri_val = new ArrayList<Integer>();		//contains ri value of each xi
	protected ArrayList<Instance> T = new	ArrayList<Instance>();
	
	public void add(Instance row){
		this.T.add(row);
	}
	
	public Instance getInstance(int row_id ) {
		return this.T.get(row_id);
	}
	
	public int getN_size() {
		return N_size;
	}
	
	public void setN_size(int x) {
	    this.N_size = x;
	}
	
	public int getN_classes() {
		return N_classes;
	}
	
	public void setN_classes(int x) {
	    this.N_classes = x;
	}
	
	public ArrayList<Integer> get_ri_val() {
		return ri_val;
	}
	
	public int[] getNc() {
		return Nc;
	}
	
	public double[] get_thetac() {
		return thetac;
	}
	
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
