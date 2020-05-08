package AppBNC;

import java.io.FileNotFoundException;
import DataStructures.*;
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
		
		G.updateWeight(G.getEdges().get(7), 5);
		
		System.out.println();
		G.printGraph();
		Tree T =  G.MST();
		T.printEdges();
		
	/*	for(node n : T) {
			System.out.print(n.name()+" ");
		}
		System.out.println();
		
		node nn = new node(30, "A90");
		G.addNewNode(nn);
		
		for(node n : T) {
			System.out.print(n.name()+" ");
		}
		
		System.out.println();
		for(node n : G.getNodes()) {
			System.out.print(n.name()+" ");
		}
	*/	
	
	}

	
	

}