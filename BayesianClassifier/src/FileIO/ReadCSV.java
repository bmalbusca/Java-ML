package FileIO;

import java.io.*;
import java.util.ArrayList;



public class ReadCSV implements ReadFile {
	
	protected BufferedReader file;
	protected Dataset T; 
	
	public ReadCSV(String filecsv) throws FileNotFoundException {
		
		
		try {
				
		
			file = new BufferedReader(new FileReader(filecsv));
			String line;			//to read each line
			T = new Dataset();		//to Save all instances
			T.N_size=-1; 			//empty
			T.N_classes=0;
			int length = 0;
			ArrayList<String> C_aux = new ArrayList<String>();		//all possible values of c					
			ArrayList<ArrayList<String>> ri_aux_list = new ArrayList<ArrayList<String>>();	//arraylist of all possible value of xi
		
			while ((line = file.readLine()) != null) {
				
				if( line.trim().isEmpty() ) {
			          continue;
			     }
				 
				String[] data = line.split(","); 		
				Instance row = new Instance();	
				row.addAll(data);			
				T.add(row);
				
				if(T.N_size == -1) {	//Just first time					
					length = T.getInstance(0).len();				
				    for(int i = 0; i < length -1 ;i++) {
				    	ArrayList<String> ri_aux = new ArrayList<String>();
				    	ri_aux_list.add(ri_aux);
				    	T.ri_val.add(0);
				    }
				}else {
					for(int i = 0; i < length -1 ;i++) {
						if(!ri_aux_list.get(i).contains(row.get(i))) {
							ri_aux_list.get(i).add(row.get(i));
							int k = T.ri_val.get(i);
							T.ri_val.set(i, k+1);  // FALTA METER NO NODE
						}
				    }
					String ci = row.get(length-1);
					if(!C_aux.contains(ci)) {
						C_aux.add(ci);
						T.N_classes++;
					}
				}
				
				++T.N_size;

			}
			System.out.print("readCSV   -   Teste ri + N_classes: ");
			for(int i = 0; i < length -1 ;i++) {
				System.out.print(T.ri_val.get(i) + " ");
			}
			System.out.print(T.N_classes + " ");
			System.out.println("");
				
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
		
		
	public void close() throws FileNotFoundException {
		
		try {
			file.close();
		}	
		catch (IOException e) {		
				e.printStackTrace();
			}
		
	}
	
	public void print() {
		
		if (T != null && this.T.N_size > 0) {
			for(int i = 0; i<= this.T.N_size; ++i) {
				T.getInstance(i).print();	
			}
		}
		else {
			System.out.println("Data set is Empty!");
		}
	}
	
	
	public Dataset data(){
		return T;
	}
	
	public Instance get(int row_id) {
		return T.getInstance( row_id );
	}
	
	public static void Nc_count(Dataset d){
		// Initialize Npjkc
		d.Nc = new int[d.N_classes];
		// Count
		for(int i =0; i<d.N_size; i++) {
			Instance inst = d.getInstance(i+1); // First is atributes names
			int ci = Integer.parseInt(inst.get(d.ri_val.size()));
			for(int c=0; c<d.N_classes; c++)
				if(ci==c)
					d.Nc[c]++;	
		}
	}
	
	
	
/*	public void update_N_classes() {
		int length = T.getInstance(0).len();	//lenght of instance
		T.N_classes=0;
		ArrayList<String> C_aux = new ArrayList<String>();
		for(int i=0; i<T.N_size; i++) {
			String ci = T.getInstance(i+1).get(length-1);
			if(!C_aux.contains(ci)) {
				C_aux.add(ci);
				T.N_classes++;
			}
		}
	}
	
	//falta meter no node
	public void update_ri() {
		int length = T.getInstance(0).len();	//lenght of instance		
		ArrayList<Integer> ri_val = new ArrayList<Integer>();		//contains ri value of each xi					
		ArrayList<ArrayList<Integer>> ri_aux_list = new ArrayList<ArrayList<Integer>>();	//arraylist of all possible value of xi
		for(int i = 0; i < length -1 ;i++) {
	    	ArrayList<Integer> ri_aux = new ArrayList<Integer>();
	    	ri_aux_list.add(ri_aux);
	    	ri_val.add(0);
	    }		
		for(int i=0; i<T.N_size; i++) {
			instance xk = T.getInstance(i+1);
			for(int k=0; k<length-1; k++) {				
				if(!ri_aux_list.contains(ci)) {
					C_aux.add(ci);
				}
			}
		}
	}*/
	
	
}
