package BayesianTAN;


import DataStructures.*;
import FileIO.*;



public class TAN {
	
	//info
	protected String model;
	protected int  N_instances;
	protected int  N_classes;

	protected static final double ln2 = Math.log(2);

	protected final double log2(double number) {

		double log2Value = Math.log(number) / TAN.ln2;

		if (Double.isNaN(log2Value))
			throw new RuntimeException("Tried to calculate log2(" + number + "): NaN");

		return log2Value;
	}
	
	public TAN(dataset Data, String model) {
		
		
		this.N_classes=Data.N_classes;
		this.N_instances= Data.N_size; 
		this.model= model;
		
		instance FeaturesNames = Data.getInstance(0);
		UndirFullGraph G = new UndirFullGraph(FeaturesNames);
	
		update_counts(Data, G);
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
	
	public void update_counts(dataset d, UndirFullGraph G) {
			for(int i=0; i<d.ri_val.size(); i++)
				node.node_counts(d, G.getNodes().get(i));
			ReadCSV.Nc_count(d);			
		}
	
	
	public void computeAlphaLL(node child) {
		
		
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

						int Nijkc = child.ri.counts[parent.ri.index][qi][rx][s];
						int N = this.N_instances;

						int Nc = this.N_classes.sCounts[s];

						int Nikc_J = child.ri.qiCounts[rx][s];
						int Nijc_K = parent.ri.qiCounts[qi][s];
						//alpha += ((child.Njkc[qi][rx][s])* log(((child.Njkc[qi][rx][s]) * C.N[s]) / ( child.NJkc[qi][rx][s] * child.NKjc[qi][rx][s])));
						if (Nijkc != 0 && Nc != 0) {

							alpha += (double) Nijkc / N * log2((double) (Nijkc * Nc) / (Nikc_J * Nijc_K));
						}
					}
				}
			}

			e.addWeight(alpha);				//update this edge
			alpha =0;						//reset alpha for other edges
		}
		
	}

	public void computeAlphaMDL(node child) {

			double alpha =0 ;

			double original = super.computeAlphaLL(node child);

			double s = this.N_classes.getRange();
			double N = this.N_instances;

			double penalization = s * (child.getRange() - 1) * (child.getParent().getRange() - 1) / 2 * Math.log(N);

			alpha = original - penalization;
			e.addWeight(alpha);				//update this edge
			alpha =0;						//reset alpha for other edges

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
