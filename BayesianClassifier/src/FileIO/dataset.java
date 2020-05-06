package FileIO;

import java.util.ArrayList;

public class dataset {
	
	public int N_size = -1;		// data set size or number of rows starts as empty  
	public ArrayList<ArrayList<String>> T = new	ArrayList<ArrayList<String>>();
	
	public void add(ArrayList<String> row){
		this.T.add(row);
	}
	
	
	
	public ArrayList<String> getRow(int row ) {
		return this.T.get(row);
	}
}
