package FileIO;

import java.io.*;



public class ReadCSV implements readFile {
	
	protected BufferedReader file;
	protected dataset T; 
	
	public ReadCSV(String filecsv) throws FileNotFoundException {
		
		
		try {
				
		
			file = new BufferedReader(new FileReader(filecsv));
			String line;			//to read each line
			T = new dataset();		//to Save all instances
			T.N_size=-1; 			//empty
		
			while ((line = file.readLine()) != null) {
				
				if( line.trim().isEmpty() ) {
			          continue;
			     }
				 
				String[] data = line.split(","); 		
				instance row = new instance();
				
				row.addAll(data);
				T.add(row);
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
			for(int i = 0; i< this.T.N_size; ++i) {
				T.getInstance(i).print();	
			}
		}
		else {
			System.out.println("Data set is Empty!");
		}
	}
	
	
	public dataset data(){
		return T;
	}
	
	public instance get(int row_id) {
		return T.getInstance( row_id );
	}
	
	
	
	
	
	
}
