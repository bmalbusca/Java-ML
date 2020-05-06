package AppBNC;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import FileIO.*;


public class Apprun {
	
	
	public static void main(String[] args) throws FileNotFoundException {
		String path = "/Users/bmalbusca/Documents/IST/POO/poo1920-project/bias-train.csv" ;
		ArrayList<ArrayList<String>> dataset; 
		
		ReadCSV file = new ReadCSV(path);
		dataset = file.data();
		
		
		for(int i = 0; i<file.N(); ++i) {
			System.out.println(dataset.get(i));
			
			
		}
	
		
	
			}
}