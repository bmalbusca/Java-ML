package AppBNC;

import java.io.FileNotFoundException;
import java.util.ArrayList;



import FileIO.*;



public class Apprun {
	
	
	/*  
	*	String path = "/Users/bmalbusca/Documents/IST/POO/poo1920-project/bias-train.csv" ;
	*/
	
	public static void main(String[] args) throws FileNotFoundException {
		
		 
		
		ArrayList<ArrayList<String>> dataset; 
		
		
		ParseInput param = new ParseInput(args); 
		ReadCSV file = new ReadCSV(param.trainfile);
		
		
		dataset = file.data();
	
		for(int i = 0; i<file.N(); ++i) {
			System.out.println(dataset.get(i));	
			
		}
	
	}

	
	

}