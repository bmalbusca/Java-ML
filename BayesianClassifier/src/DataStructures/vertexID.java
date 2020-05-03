package DataStructures;

public class vertexID extends vertex{
	
	protected int childID; //vertex's end-point ID

	//constructor
	public vertexID(int pID, int weight) {
		super(weight);
		this.childID = pID; 
		
	}
	
	public int id() {
		return this.childID;
	}
	

}
