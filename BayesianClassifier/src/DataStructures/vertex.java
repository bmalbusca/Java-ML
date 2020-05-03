package DataStructures;


// vertex definition 
public  class vertex {

	private int weight; 	
	
	//constructor
	public vertex(int w) {
		this.weight=w; 
	}
	
	
	public void addWeight(int val) {
		this.weight=val;
	}
	
	public int weight() {
		return this.weight;
	}
	
}



