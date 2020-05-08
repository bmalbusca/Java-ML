package DataStructures;


// vertex definition 
public  class edge {

	private double weight;
	protected int ID;
	protected node[] nodes = new node[2]; 
	protected boolean directed = false;
	
	//constructor
	public edge(double w, node n1, node n2) {
		this.weight=w;
		this.nodes[0]=n1;
		this.nodes[1]=n2;
		
		
	}
	
	
	
	//overloading 
	public edge(double w, node parent, node child, boolean directed) {
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
			//System.out.println("*Alert* This graph is not directed");
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
		this.nodes[0].sortEdges();
		this.nodes[1].sortEdges();
	}
	
	public double weight() {
		return this.weight;
	}
	
	public edge clone() {
		
		edge new_edge = new edge(this.weight,this.nodes[0],this.nodes[1]);
		return new_edge;
	}
	
	
}



