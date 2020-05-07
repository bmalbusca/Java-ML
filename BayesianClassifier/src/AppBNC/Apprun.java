package AppBNC;

import java.io.FileNotFoundException;

import DataStructures.UndirFullGraph;
import FileIO.*;



public class Apprun {
	
	
	/*  
	*	String path = "/Users/bmalbusca/Documents/IST/POO/poo1920-project/bias-train.csv" ;
	*/
	
	public static void main(String[] args) throws FileNotFoundException {
		
		
		
		InputParams param = new InputParams(args); 
		ReadCSV file = new ReadCSV(param.trainfile);
		file.print();
		
		
		UndirFullGraph G = new UndirFullGraph(file.data().getInstance(0));
		G.printGraph();
		

	
	}

	
	

}