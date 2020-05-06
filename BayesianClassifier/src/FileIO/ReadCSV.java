package FileIO;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ReadCSV {
	
	protected BufferedReader file;
	protected boolean header = false;   	//detect header NOT USED YET
	protected int N=-1; 					// dataset size (number of lines) 
	protected ArrayList<ArrayList<String>> T;
	
	public ReadCSV(String filecsv) throws FileNotFoundException {
		
		
		try {
		
			file = new BufferedReader(new FileReader(filecsv));
			String line;
			T = new ArrayList<ArrayList<String>>();
		
			while ((line = file.readLine()) != null) {
				
				if( line.trim().isEmpty() ) {
			          continue;
			     }
				 
				String[] data = line.split(",");
				ArrayList<String> row = new ArrayList<String>();
				
				for (String cell: data ) {
					row.add(cell);
				}
				
				
				T.add(row);
				++N;

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
	
	
	
	public ArrayList<ArrayList<String>> data(){
		return T;
	}
	
	public int N() {
		return N;
	}
	
	
	
	
}
