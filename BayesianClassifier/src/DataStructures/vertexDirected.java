package DataStructures;

public class vertexDirected extends vertex{
	
	protected int ID; //vertex's end-point ID

	//constructor
	public vertexDirected(int id, int weight, node n1, node n2) {
		super(weight, n1, n2, true);
		this.ID = id; 
		
	}
	
	public int id() {
		return this.ID;
	}
	

}
