package FileIO;

import java.util.ArrayList;

public class dataset {
	
	public int N_size = -1;		// data set size or number of rows starts as empty  
	public ArrayList<instance> T = new	ArrayList<instance>();
	
	public void add(instance row){
		this.T.add(row);
	}
	
	
	public instance getInstance(int row_id ) {
		return this.T.get(row_id);
	}



}
