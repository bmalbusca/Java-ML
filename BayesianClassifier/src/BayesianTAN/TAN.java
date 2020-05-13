package BayesianTAN;

import DataStructures.*;
import FileIO.*;



public class TAN {
	
	//info
	protected String model;
	protected int  N_instances;
	protected int  N_classes;


	public TAN(Dataset Data, String model) {
		
		
		this.N_classes=Data.N_classes;
		this.N_instances= Data.N_size; 
		this.model= model;
		
		Instance FeaturesNames = Data.getInstance(0);
		UndirFullGraph G = new UndirFullGraph(FeaturesNames);
	
		update_counts(Data, G);
		run_model(G, Data, model);
		
		Tree T =  G.MST();
		T.directTree();
		
		T.printClassifier();
		
		//for bias-train.csv - small train set
		//G.updateWeight(G.getEdges().get(2), 2);
		//G.updateWeight(G.getEdges().get(1), 5);
		//G.updateWeight(G.getEdges().get(0), 3);
		//G.updateWeight(G.getEdges().get(3), 1);
		//G.updateWeight(G.getEdges().get(4), 1);

		
		System.out.println();
		//G.printGraph();
				

			
		
	}
	
	public void update_counts(Dataset d, UndirFullGraph G) {
		ReadCSV.Nc_count(d);	
		for(int i=0; i<d.ri_val.size(); i++)
			Node.node_counts(d, G.getNodes().get(i));	
	}
	
	public void run_model(UndirFullGraph graph, Dataset d, String m){
		ScoreModel scoremodel = null;
		if ("LL".equals(m)) {
			scoremodel = new LLmodel();
		} else if ("MDL".equals(m)) {
			scoremodel = new MDLmodel();
		}
		for(Edge e : graph.edges) {
			double weight = scoremodel.calculate(e, d);
			graph.updateWeight(e, weight);
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
