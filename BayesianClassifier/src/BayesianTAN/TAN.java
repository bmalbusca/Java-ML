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
	
	/**
	 * Runs the counter of each class and each node.
	 * 
	 * @param d Dataset with some useful counts.
	 * @param G Undirected full graph where the nodes are stored.
	 */
	private void update_counts(Dataset d, UndirFullGraph G) {
		ReadCSV.Nc_count(d);	
		for(int i=0; i<d.get_ri_val().size(); i++)
			Node.node_counts(d, G.getNodes().get(i));	
	}
	
	/**
	 * Runs the score model to calculate the weight of the edges.
	 * 
	 * @param graph Undirected full graph where the edges are stored.
	 * @param d Dataset where some counts are stored.
	 * @param m String with input parameter to select which model to run.
	 */
	private void run_ScoreModel(UndirFullGraph graph, Dataset d, String m){
		ScoreModel scoremodel = null;
		if ("LL".equals(m)) {
			scoremodel = new LLmodel();
		} else if ("MDL".equals(m)) {
			scoremodel = new MDLmodel();
		}
		for(Edge e : graph.getEdges()) {
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
		this.N_classes=Data.getN_classes();
		this.N_instances= Data.getN_size();
		
		this.tree.printClassifier();
		System.out.println("Time to build:\t"+timeBuild+ " ms");
		
	}
	
	
	
	public Dataset predict(Dataset T) {
		long startTime = new Date().getTime();
		Dataset predict = Classify(T);
		
		long endTime = new Date().getTime();
		this.timeTest = endTime - startTime;
		System.out.println("Time to test:\t"+timeTest+ " ms");
		
		return predict;
	}

	
	
	
	
	private double JointProbC(Instance values, int c,  Tree T) {
	
		double prob =1;
		int j,k;
		
		//root
		k = Integer.parseInt(values.get(T.root().id()));	
		prob *= T.root().get_theta()[0][k][c];
		for (Edge e : T.getEdges() ) {
			
			Node parent = e.parent(); 
			Node child = e.child();
			
			j = Integer.parseInt(values.get(parent.id())); //use parseInt()
			k = Integer.parseInt(values.get(child.id()));	
			
			prob *= child.get_theta()[j][k][c];
			
		}	
		return prob; 	
	}
	
	
	
	
	public Dataset Classify(Dataset T) {
		
		int  idxC = tree.Nnodes();
		Node C = tree.getNodes().get(idxC);
		
		Dataset Tpredicted = new Dataset();
		Tpredicted.add(T.getInstance(0));
		Tpredicted.setN_size(T.getN_size());
		Tpredicted.setN_classes(T.getN_classes());
		
		double[] probs = new double[C.get_ri()];
		double MaxProb;
		int c_class =0;

		System.out.println("Testing the classifier:");
		for(int i =1; i<=T.getN_size(); i++) {
			Instance inst = T.getInstance(i);
			Instance instPredict = new Instance();
			
			instPredict.copy(inst, inst.len());
			MaxProb = 0;
			
			
			for (int c = 0; c < C.get_ri(); c++) {
				probs[c] = JointProbC(inst, c, tree ) * C.get_thetac()[c];
			
				if (probs[c] > MaxProb) {
					MaxProb = probs[c];
					c_class = c;
				}		
			}

			instPredict.set( idxC, String.valueOf(c_class)); 
			Tpredicted.add(instPredict);
			System.out.println("-> instance "+i+ ":\t" + c_class);

		}	
		
		return Tpredicted;  
	
	}
	
}
