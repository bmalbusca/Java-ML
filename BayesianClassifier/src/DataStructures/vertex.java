package DataStructures;


// vertex definition 
public  class vertex {

	private int weight;
	protected node[] nodes = new node[2]; // [parent, child]
	protected boolean directed = false;
	
	//constructor
	public vertex(int w, node n1, node n2) {
		this.weight=w;
		this.nodes[0]=n1;
		this.nodes[1]=n2;
		
	}
	
	//overloading 
	public vertex(int w, node parent, node child, boolean directed) {
		this.weight=w;
		this.nodes[0]=parent;
		this.nodes[1]=child;
		this.directed = true;
	}
	
	
	public node parent() {
		return this.nodes[0];
	}
	
	public node child() {
		return this.nodes[1];
	}
	
	public void addWeight(int val) {
		this.weight=val;
	}
	
	public int weight() {
		return this.weight;
	}
	
}



