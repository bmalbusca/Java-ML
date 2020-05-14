package BayesianTAN;

import DataStructures.*;
import FileIO.*;
import java.util.Date;




public class TAN {
	
	//info
	protected String model;
	protected int  N_instances;
	protected int  N_classes;
	private Tree tree;
	
	private long timeBuild =0; 
	private long timeTest =0; 
	


	public TAN(String model) {
		this.model= model;

	}
	
	private void update_counts(Dataset d, UndirFullGraph G) {
		ReadCSV.Nc_count(d);	
		for(int i=0; i<d.ri_val.size(); i++)
			Node.node_counts(d, G.getNodes().get(i));	
	}
	
	private void run_ScoreModel(UndirFullGraph graph, Dataset d, String m){
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
	
	public void train(Dataset Data) {
		
	    long startTime = new Date().getTime();
		
		Instance FeaturesNames = Data.getInstance(0);
		UndirFullGraph G = new UndirFullGraph(FeaturesNames);
	
		update_counts(Data, G);
		run_ScoreModel(G, Data, this.model);

		
		Tree T =  G.MST();
		T.directTree();
		T.updateThetas(Data);

		
		long endTime = new Date().getTime();
		timeBuild = endTime - startTime;
	
		this.tree = T; 
		this.N_classes=Data.N_classes;
		this.N_instances= Data.N_size;
		
		
		this.tree.printClassifier();
		System.out.println("Time to build: "+timeBuild+ "ms");
		
		
		
		
		
	}
	
	
	
	public Dataset predict(Dataset T) {
		long startTime = new Date().getTime();
		Dataset predict = Classify(T);
		
		long endTime = new Date().getTime();
		this.timeTest = endTime - startTime;
		System.out.println("Time to test: "+timeTest+ "ms");
		
		return predict;
	}

	
	
	
	
	private double JointProbC(Instance values, int c,  Tree T) {
	
		double prob =1;
		int j,k;
		
		//root
		k = Integer.parseInt(values.get(T.root().id()));	
		prob *= T.root().theta[0][k][c];
		for (Edge e : T.getEdges() ) {
			
			Node parent = e.parent(); 
			Node child = e.child();
			
			j = Integer.parseInt(values.get(parent.id())); //use parseInt()
			k = Integer.parseInt(values.get(child.id()));	
			
			prob *= child.theta[j][k][c];
			
		}	
		return prob; 	
	}
	
	
	
	
	public Dataset Classify(Dataset T) {
		
		int  idxC = tree.Nnodes();
		Node C = tree.getNodes().get(idxC);
		
		Dataset Tpredicted = new Dataset();
		Tpredicted.add(T.getInstance(0));
		Tpredicted.N_size=T.N_size;
		Tpredicted.N_classes=T.N_classes;
		
		double[] probs = new double[C.ri];
		double MaxProb;
		int c_class =0;

		for(int i =1; i<=T.N_size; i++) {
			Instance inst = T.getInstance(i);
			Instance instPredict = new Instance();
			
			instPredict.copy(inst, inst.len());
			MaxProb = 0;
			
			
			for (int c = 0; c < C.ri; c++) {
				probs[c] = JointProbC(inst, c, tree ) * C.thetac[c];
			
				if (probs[c] > MaxProb) {
					MaxProb = probs[c];
					c_class = c;
					
				}		
			}
			

			instPredict.set( idxC, String.valueOf(c_class)); 
			Tpredicted.add(instPredict);
			System.out.println("instance "+i+ " : " + c_class + " ("+inst.get(idxC)+")");

		}	
		
		return Tpredicted;  
	
	}
	

	

	
	
}
