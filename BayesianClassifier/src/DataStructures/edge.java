package DataStructures;


// vertex definition 
public  class edge {

	private double weight;
	protected int ID;
	protected node[] nodes = new node[2]; // [parent, child] in a directed graph
	protected boolean directed = false;
	
	//constructor
	public edge(int w, node n1, node n2) {
		this.weight=w;
		this.nodes[0]=n1;
		this.nodes[1]=n2;
		
		
	}
	
	
	
	//overloading 
	public edge(int w, node parent, node child, boolean directed) {
		this.weight=w;
		this.nodes[0]=parent;
		this.nodes[1]=child;
		this.directed = directed;
	}
	
	
	public node from() {
		if(!this.directed) {
			System.out.println("*Alert* This graph is not directed");
		}
		return this.nodes[0];
	}
	
	public node to() {
		if(!this.directed) {
			System.out.println("*Alert* This graph is not directed");
		}
		return this.nodes[1];
	}
	
	public node next() {
		return this.nodes[1];
		
	}
	
	public node[] nodes() {
		return nodes;
	}
	
	public void addWeight(double val) {
		this.weight=val;
	}
	
	public double weight() {
		return this.weight;
	}
	
}



