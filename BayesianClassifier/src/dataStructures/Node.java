package dataStructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import fileIO.Dataset;
import fileIO.Instance;




/** 
 * Node definition - This class represents a node of a general graph but can also save other information more specific about TAN parameters 
 * 
 * @author Group 20
 */

public class Node implements  Cloneable {
	
	protected int ID;			// node ID
	protected String label;		// Real name that comes with the CSV file
	
	
									//Useful data to calculate the weigths
	protected int ri;				// N values that xi can take
	protected int Npjkc[][][][];	// [p][j][k][c] ->p=parent ;   j-> parent value
	protected int NJkc[][];			// Number of instances where Xi takes its k-th value xik and C class takes c-th value
	protected double theta[][][];	// theta values
	protected double thetac[];
	protected ArrayList<Edge> edges = new ArrayList<Edge>(); 		//edges seen by this node Xi
	protected Map<Edge, Node> map = new HashMap< Edge,Node>();		//Fast search for nodes-edge pairs
	
	/**
	 * Constructor method 
	 * @param id numeric identifier 
	 * @param label	extended name 
	 */
	public Node(int id, String label) { 
		this.ID=id;						
		this.label = label;
	}
	
	/**
	 * This method returns  the number of possible values that this node can take
	 * @return int number of values that node can have
	 */
	public int get_ri() {
		return ri;
	}
	
	/**
	 * Returns the counting matrix of all input pattern occurrences for a node 
	 * @return int[][][][] returns a matrix that saves all occurrences counts 
	 */
	public int[][][][] get_Npjkc() {
		return Npjkc;
	}
	
	/**
	 * Returns the counting matrix of NJkc pattern occurrences for a node
	 * @return int[][]returns a matrix that saves all occurrences counts
	 */
	public int[][] get_NJkc() {
		return NJkc;
	}
	
	/**
	 * Returns the matrix of theta values for a node
	 * @return double[][][]returns a matrix that saves all values
	 */
	public double[][][] get_theta() {
		return theta;
	}
	
	/**
	 * Returns the array of the theta values for each class
	 * @return double[] returns an array that saves all thetas
	 */
	public double[] get_thetac() {
		return thetac;
	}
	
	/**
	 * This method is used to add a edge to the graph
	 * @param e	edge of type Edge
	 */
	public void addEdge(Edge e) {
		edges.add(e);
		
	}
	
	/**
	 * This method is used to add the edge the uses this nodes and add a reference to the other node
	 * @param e the Edge that connects to this nodes
	 * @param n	the node that complements the formation of edge e
	 */
	public void MapNodes(Edge e, Node n) {
		addEdge(e);
		map.put(e,n);
		
	}
	
	/**
	 * Method is used to get the parent of this node 
	 * @param e	edge that is connected to this node
	 * @return	Node parent of this node
	 */
	public Node getParent(Edge e) {
		Node parent = this.map.get(e);
		
		return parent; 
	}

	/**
	 * Method to get all edges connected to this node
	 * @return ArrayList of Edge  list of edges seen by this nodes
	 */
	public ArrayList<Edge> edges(){
		return edges; 
	}
	
	/**
	 * method returns the identifier of a node
	 * @return int identifier 
	 */
	public int id() {
		return this.ID; 
	}
	
	/**
	 * method returns the name of a node
	 * @return String extended name
	 */
	public String name() {
		return this.label;
	}

	/**
	 * Method sorts the edge list by weight value in a decreasing order
	 */	
	public void sortEdges() {
		Collections.sort(edges,new EdgeCompare()); //Sort edge list
	}


	
	@Override
	protected Node clone() throws CloneNotSupportedException {

	    return (Node) super.clone();
	}
	
	/**
	 * Method to count all the Ncounts need to calculate the weight of the edges, for each node
	 * @param d Dataset with some data useful for the counts, for ex. N_size
	 * @param n Node for which we calculate the Ncounts
	 */
	public static void node_counts(Dataset d, Node n){
		n.ri = d.get_ri_val().get(n.ID);
		// Initialize Npjkc
		n.Npjkc = new int[d.get_ri_val().size()][][][];
		for(int p=0; p < d.get_ri_val().size() ; p++) {
			n.Npjkc[p] = new int[d.get_ri_val().get(p)][n.ri][d.getN_classes()];			
		}
		// Initialize NJkc
		n.NJkc = new int[n.ri][d.getN_classes()];
		// Count
		for(int i =0; i<d.getN_size(); i++) {
			Instance inst = d.getInstance(i+1); // First is atributes names
			int ki = Integer.parseInt(inst.get(n.ID));
			int ci = Integer.parseInt(inst.get(d.get_ri_val().size()));
			for(int p=0; p < d.get_ri_val().size() ; p++) {
				if(p == n.ID) {   // Empty parent configuration, we store it where node Xi is the parent of itself
					for(int k=0; k<n.ri; k++) {
						for(int c=0; c<d.getN_classes(); c++)
							if(ki==k && ci==c)
								n.Npjkc[p][0][k][c]++;
					}
					continue;
				}
				for(int j=0; j<d.get_ri_val().get(p); j++) {
					int ji = Integer.parseInt(inst.get(p));
					for(int k=0; k<n.ri; k++)
						for(int c=0; c<d.getN_classes(); c++)
							if(ji==j && ki==k && ci==c)
								n.Npjkc[p][j][k][c]++;    // Nijkc for this node and parent p
				}	
			}
			for(int k=0; k<n.ri; k++) {
				for(int c=0; c<d.getN_classes(); c++)
					if(ki==k && ci==c)
						n.NJkc[k][c]++;    // NJkc     xi-> k.th value    c-> c-th value
			}
		}
	}
	
	
	
}







