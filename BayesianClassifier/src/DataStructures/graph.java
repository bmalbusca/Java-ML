package DataStructures;

import java.util.ArrayList;

public  abstract class graph {
	

	protected int n;	// number of nodes Xi
	protected int ne;	// number of edges
	protected ArrayList<node>  nodes = new ArrayList<node>();  // array of nodes
	protected ArrayList<edge> edges = new ArrayList<edge>();  	// array of edges 
	
	
	abstract public void printGraph();
	abstract public void connect(node n1, node n2, edge e);
	abstract public void addNewNode( node n);
	abstract public void addNewEdge( edge e);
	abstract public int Nnodes();
	abstract public int Nedges();
	


}
