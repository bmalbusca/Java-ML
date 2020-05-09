package BayesianTAN;


import DataStructures.*;
import FileIO.*;



public class TAN {
	
	//info
	protected String model;
	protected int  N_instances;
	protected int  N_classes; 
	
	
	public TAN(dataset Data, String model) {
		
		
		this.N_classes=Data.N_classes;
		this.N_instances= Data.N_size; 
		this.model= model;
		
		instance FeaturesNames = Data.getInstance(0);
		UndirFullGraph G = new UndirFullGraph(FeaturesNames);
	
		
		//for bias-train.csv - small train set
		//G.updateWeight(G.getEdges().get(2), 2);
		//G.updateWeight(G.getEdges().get(1), 5);
		//G.updateWeight(G.getEdges().get(0), 3);
		//G.updateWeight(G.getEdges().get(3), 1);
		//G.updateWeight(G.getEdges().get(4), 1);
	
		
		System.out.println();
		G.printGraph();
		
		
		
		//Tree T =  G.MST();
		//T.printGraph();
		//T.printNodes();
		
		
		
		
		
		
		
		
		
		
	}
	
	
	public void computeAlpha(node child) {
		
		
		/*
		 * 		sum(sum(sum  Njkc/N log2(Njkc * Nc / NJkc * NKjc) ))
		 * 		- Falta actualizar N_instances e N_calsses no ReadCSV
		 * 		- Falta fazer a funcao log2
		 * 		- Falta adicionar o no ao grafo sem edge - para conseguir fazer algo como C.N[s]
		 */
		
		
		node parent;		//Will receive the parents of this nodes; Attention! The actual node is the child and have all the remains as parents
		int qi,rx,s;
		
		double alpha =0 ;	//about of calculations
		
		

		for(edge e : child.edges()) {
				
			parent = child.getParent(e); 
			
			for(qi=0; qi < parent.ri; ++qi ) {
				for(rx=0 ;rx<child.ri; ++rx ) {
					for(s=0; s<this.N_classes; s++) {
						//alpha += ((child.Njkc[qi][rx][s])* log(((child.Njkc[qi][rx][s]) * C.N[s]) / ( child.NJkc[qi][rx][s] * child.NKjc[qi][rx][s])));
						alpha += 0; // erase this please
					}
				}
			}
			
			alpha = alpha/this.N_instances; 
			e.addWeight(alpha);				//update this edge
			alpha =0;						//reset alpha for other edges
			
		}
		
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
