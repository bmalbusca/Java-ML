package DataStructures;


// vertex definition 
public  class Edge {

	private double weight;
	protected int ID;
	protected Node[] nodes = new Node[2]; 
	protected boolean directed = false;
	
	//constructor
	public Edge(double w, Node n1, Node n2) {
		this.weight=w;
		this.nodes[0]=n1;
		this.nodes[1]=n2;
		
		
	}
	
	
	
	//overloading 
	public Edge(double w, Node parent, Node child, boolean directed) {
		this.weight=w;
		this.nodes[0]=parent;
		this.nodes[1]=child;
		this.directed = directed;
	}
	
	
	public Node from() {
		if(!this.directed) {
			System.out.println("*Alert* This graph is not directed");
		}
		return this.nodes[0];
	}
	
	public Node to() {
		if(!this.directed) {
			//System.out.println("*Alert* This graph is not directed");
		}
		return this.nodes[1];
	}
	
	public Node next() {
		return this.nodes[1];
		
	}
	
	public Node[] nodes() {
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
	
	public Edge clone() {
		
		Edge new_edge = new Edge(this.weight,this.nodes[0],this.nodes[1]);
		return new_edge;
	}
	
	
}



