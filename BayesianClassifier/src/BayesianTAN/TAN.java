package BayesianTAN;

import DataStructures.*;
import FileIO.*;



public class TAN {
	
	//info
	protected String model;
	protected int  N_instances;
	protected int  N_classes;
	private Tree tree; 


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
		T.updateNodeThetas(Data);
		
		T.printClassifier();

		this.tree = T; 
		System.out.println();
	
				

			
		
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
	
	
	
	public void predict(Dataset T) {
		
		Classify(T);
		
	}

	
	
	
	
	public double JointProbC(Instance values, int c,  Tree T) {
	
		double prob =1;
		int j,k;
		
		for (Edge e : T.getEdges() ) {
			
			Node parent = e.parent(); 
			Node child = e.child();
			
			j = Integer.parseInt(values.get(parent.id())); //use parseInt()
			k = Integer.parseInt(values.get(child.id()));	
			
			prob *= child.theta[j][k][c];
			
		}	
		return prob; 	
	}
	
	
	
	
	public double[] Classify(Dataset T) {
		
		int  idxC = tree.Nnodes();
		Node C = tree.getNodes().get(idxC);
		
		double[] probs = new double[C.ri];
		double MaxProb;
		int c_class =0;

		
		for(int i =0; i<T.N_size; i++) {
			Instance inst = T.getInstance(i+1);
			MaxProb = 0;
			for (int c = 0; c < C.ri; c++) {
				probs[c] = JointProbC(inst, c, tree );
			
				if (probs[c] > MaxProb) {
					MaxProb = probs[c];
					c_class = c;
					
				}		
			}
			System.out.println("Row "+i+" "+ c_class );	
		}
		
		return probs;  
	
	}
	

	

	
	
}
