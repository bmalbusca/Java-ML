package DataStructures;


/**
 * This Class represents an edge of a general graph. A edge is composed by two nodes
 * 
 * @author Group 20
 *
 */
public  class Edge { // vertex definition 
	
	/*
	* Contains weight value of a weighted edge
	*/
	private double weight;
	/*
	* The identifier of an edge
	*/
	protected int ID;
	
	/*
	* Contains the two Nodes objects that makes the edge  
	*/
	protected Node[] nodes = new Node[2];
	
	/*
	 *  Represents the edge as a directed edge. From the parent (nodes[0]) to the child (nodes[1])
	 */  
	protected boolean directed = false;
	
	/**
	 * Constructor 
	 * @param w weight value of an edge
	 * @param n1 node that belongs to the edge
	 * @param n2 the remain	node that belongs to the edge
	 */
	public Edge(double w, Node n1, Node n2) {//constructor
		this.weight=w;
		this.nodes[0]=n1;
		this.nodes[1]=n2;
	}
	
	/**
	 * Constructor - Overloading
	 * @param w	weight value of an edge
	 * @param parent the parent node of a directed edge
	 * @param child the child node of a directed edge
	 * @param directed	boolean tag to identify this edges as directed
	 */
	public Edge(double w, Node parent, Node child, boolean directed) { //overloading 
		this.weight=w;
		this.nodes[0]=parent;
		this.nodes[1]=child;
		this.directed = directed;
	}
	
	/**
	 * This method is used to find the start/parent node of a edge
	 * @return Node	this return the parent node
	 */
	public Node from() {
		if(!this.directed) {
			System.out.println("*Alert* This graph is not directed");
		}
		return this.nodes[0];
	}
	
	
	/**
	 * This method is used to find the start/parent node of a edge
	 * @return Node return the parent node of this edge
	 */
	public Node parent() {
		return nodes[0]; 
	}
	
	/**
	 * This method is used to find the end/child node of a edge
	 * @return Node return the child node of this edge
	 */
	public Node child() {
		
		return nodes[1];
	}
	

	/**
	 * This method is used to get all nodes of this edge
	 * @return	Node[]	return a array of nodes that makes this edge 
	 */
	public Node[] nodes() {
		return nodes;
	}
	
	/**
	 * This method is used to change the weigth value of a edge
	 * @param val weigth value
	 */
	public void addWeight(double val) {
		this.weight=val;
		this.nodes[0].sortEdges();
		this.nodes[1].sortEdges();
	}
	
	/**
	 * The method to give the edge's weight 
	 * @return double weigth value
	 */
	public double weight() {
		return this.weight;
	}
	
	/**
	 * method used to create a clone of this edge
	 * @return Edge returns a edge object with equal parameters
	 */
	public Edge clone() {
		
		Edge new_edge = new Edge(this.weight,this.nodes[0],this.nodes[1]);
		return new_edge;
	}
	
	/** 
	 * Flips the edge, switching the parent and child nodes. 
	 * */
	public void flip() {
		Node tempParent = nodes[0];
		nodes[0] = nodes[1];
		nodes[1] = tempParent;
	}
	
	
}



