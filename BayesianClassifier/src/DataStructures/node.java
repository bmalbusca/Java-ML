package DataStructures;

import java.util.ArrayList;

/** node
 * @provides Node definition - the parameters are only accessed by a method
 * @return None
 * @author brunocfigueiredo
 */

public class node {
	
	protected int ID;		// node ID
	protected String label;
	
	public int ri;			// N values that xi can take
	public int Njkc[][][];	// Nijkc counts
	public int NKjc[][][];  // sum(Nijkc, k={1,..,ri}) - Number of instances where the possible parents take their j-th and C class takes c-th value
	public int NJkc[][][];	// sum(Nijkc, j={1,..,qi}) - Number of instances where Xi takes its k-th value xik and C class takes c-th value
	
	
	protected ArrayList<node> Parent = new ArrayList<node>(); //parent nodes or simply the connected nodes to Xi
	protected ArrayList<vertex> edges = new ArrayList<vertex>(); //edges seen by this node Xi
	
	//constructor 
	public node(int id, String label) {
		this.ID=id;
		this.label = new String(label);
	}
	
	public void addNode(node n) {
		Parent.add(n);
	}
	
	public void addEdge(vertex e) {
		edges.add(e);
	}
	
	
	public ArrayList<node> connNodes(){
		return Parent;
	}
	
	
	public ArrayList<vertex> edges(){
		return edges; 
	}
	
	
	public int id() {
		return this.ID; 
	}
	
	public String name() {
		return this.label;
	}

	
}







