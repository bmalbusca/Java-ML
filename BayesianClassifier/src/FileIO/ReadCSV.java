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
	
}
