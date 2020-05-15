package DataStructures;

import java.util.ArrayList;

/**
 * 
 * @author Group 20
 *
 */
public  abstract class Graph {
	
	/*
	 * general info: number of nodes,  which is related to the number of features in this specific application
	 */
	protected int n;	// number of nodes Xi
	
	/*
	 * general info: number of edges that make part of this graph 
	 */
	protected int ne;	// number of edges
	
	/*
	 *  List of nodes that belongs to the graph
	 */
	protected ArrayList<Node>  nodes = new ArrayList<Node>();  // array of nodes
	
	/*
	 * List of edges that belongs to the graph 
	 */
	protected ArrayList<Edge> edges = new ArrayList<Edge>();  	// array of edges 
	
	abstract public void printGraph();
	abstract public void printNodes();
	abstract public void connect(Node n1, Node n2, Edge e);
	abstract public void addNewNode( Node n);
	abstract public void addNewEdge( Edge e);
	abstract public int Nnodes();
	abstract public int Nedges();
	
}
