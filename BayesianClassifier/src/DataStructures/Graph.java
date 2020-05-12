package DataStructures;

import java.util.ArrayList;

public  abstract class Graph {
	

	protected int n;	// number of nodes Xi
	protected int ne;	// number of edges
	
	//protected int s; 	//number of possible C values 
	//protected int N; 	//number of instances 
	protected ArrayList<Node>  nodes = new ArrayList<Node>();  // array of nodes
	protected ArrayList<Edge> edges = new ArrayList<Edge>();  	// array of edges 
	
	
	abstract public void printGraph();
	abstract public void printNodes();
	abstract public void connect(Node n1, Node n2, Edge e);
	abstract public void addNewNode( Node n);
	abstract public void addNewEdge( Edge e);
	abstract public int Nnodes();
	abstract public int Nedges();
	
	


}
