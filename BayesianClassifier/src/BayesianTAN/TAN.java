package BayesianTAN;


import DataStructures.*;
import FileIO.instance;



public class TAN {
	
	//info
	String model; 
	
	
	public TAN(instance FeaturesNames, String model) {
		
		
		this.model= model;
		UndirFullGraph G = new UndirFullGraph(FeaturesNames);
	
		
		 //for bias-train.csv - small train set
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
	


	public void train() {
		
		System.out.println("Not yet!");
	}
	
	
	
	
	//ArrayList<C_predicted>   = TAN.predict( ArrayList<X_test> )
	
	public void predict() {
		System.out.println("Not yet!");
		
	}
	
	
	//f1.score(ArrayList<C_predicted>, ArrayList<C_test>)
	
}
