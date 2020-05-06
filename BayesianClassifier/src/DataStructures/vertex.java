package DataStructures;


// vertex definition 
public  class vertex {

	private int weight;
	protected node n1;
	protected node n2;
	protected boolean directed = false;
	
	//constructor
	public vertex(int w, node n1, node n2) {
		this.weight=w; 
	}
	
	//overloading 
	public vertex(int w, node parent, node child, boolean directed) {
		this.weight=w;
		this.n1=parent;
		this.n2=child;
		this.directed = true;
	}
	
	
	public node parent() {
		return this.n1;
	}
	
	public node child() {
		return this.n2;
	}
	
	public void addWeight(int val) {
		this.weight=val;
	}
	
	public int weight() {
		return this.weight;
	}
	
}



