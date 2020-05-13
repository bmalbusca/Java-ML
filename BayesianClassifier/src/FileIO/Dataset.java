package FileIO;

import java.util.ArrayList;

public class Dataset {
	
	public int N_size = -1;		// data set size or number of rows starts as empty  
	public int N_classes=0; 	// Number of values C can take -> also "s"
	public int Nc[];            // Number of instances where C takes its c-th value
	public ArrayList<Integer> ri_val = new ArrayList<Integer>();		//contains ri value of each xi
	public ArrayList<Instance> T = new	ArrayList<Instance>();
	
	public void add(Instance row){
		this.T.add(row);
	}
	
	
	public Instance getInstance(int row_id ) {
		return this.T.get(row_id);
	}



}