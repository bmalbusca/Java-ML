package AppBNC;

import java.io.FileNotFoundException;
import FileIO.*;



public class Apprun {
	
	
	/*  
	*	String path = "/Users/bmalbusca/Documents/IST/POO/poo1920-project/bias-train.csv" ;
	*/
	
	public static void main(String[] args) throws FileNotFoundException {
		
		
		
		ParseInput param = new ParseInput(args); 
		ReadCSV file = new ReadCSV(param.trainfile);
		file.print();
	

	
	}

	
	

}