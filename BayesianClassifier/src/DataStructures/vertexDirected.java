package DataStructures;

public class vertexDirected extends vertex{
	
	protected int childID; //vertex's end-point ID

	//constructor
	public vertexDirected(int pID, int weight) {
		super(weight);
		this.childID = pID; 
		
	}
	
	public int id() {
		return this.childID;
	}
	

}
