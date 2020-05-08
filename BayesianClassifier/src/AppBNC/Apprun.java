package AppBNC;

import java.io.FileNotFoundException;
import DataStructures.*;
import FileIO.*;



public class Apprun {
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		
		
		InputParams param = new InputParams(args); 

		System.out.println(param.trainfile);
		ReadCSV file = new ReadCSV(param.trainfile);
		file.print();
		
		
		UndirFullGraph G = new UndirFullGraph(file.data().getInstance(0));
		//G.printGraph();
		
		G.updateWeight(G.getEdges().get(2), 2);
		G.updateWeight(G.getEdges().get(1), 5);
		G.updateWeight(G.getEdges().get(0), 3);
		G.updateWeight(G.getEdges().get(3), 1);
		G.updateWeight(G.getEdges().get(4), 1);
	
		
		System.out.println();
		G.printGraph();
		Tree T =  G.MST();
		
		T.printGraph();
		T.printNodes();

	
	
	}

	
	

}