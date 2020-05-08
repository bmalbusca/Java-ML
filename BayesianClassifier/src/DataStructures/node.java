package DataStructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;




/** node
 * @provides Node definition - the parameters are only accessed by a method
 * @return None
 * @author brunocfigueiredo
 */

public class node implements  Cloneable {
	
	protected int ID;			// node ID
	protected String label;		// Real name that comes with the CSV file
	
	
	//Useful data to calculate the weigths
	public int ri;			// N values that xi can take
	public double Njkc[][][];	// Nijkc counts
	public double NKjc[][][];  // sum(Nijkc, k={1,..,ri}) - Number of instances where the possible parents take their j-th and C class takes c-th value
	public double NJkc[][][];	// sum(Nijkc, j={1,..,qi}) - Number of instances where Xi takes its k-th value xik and C class takes c-th value
	public double theta[][][];
	
	protected ArrayList<node> LinkedNodes = new ArrayList<node>();  //parent nodes or simply the connected nodes to Xi
	protected ArrayList<edge> edges = new ArrayList<edge>(); 		//edges seen by this node Xi
	Map<edge, node> map = new HashMap< edge,node>();				//Fast search for nodes-edge pairs
	
	//constructor 
	public node(int id, String label) { //TO-DO check if id and label exists
		this.ID=id;
		this.label = label;
	}
	
	public void addEdge(edge e) {
		edges.add(e);
		
	}
	
	public void MapNodes(edge e, node n) {
		addEdge(e);
		map.put(e,n);
		
	}

	
	public ArrayList<edge> edges(){
		return edges; 
	}
	
	
	public int id() {
		return this.ID; 
	}
	
	public String name() {
		return this.label;
	}

	
	public void sortEdges() {
		Collections.sort(edges,new edgeCompare()); //Sort edge list
	}


	
	@Override
	protected node clone() throws CloneNotSupportedException {

	    return (node) super.clone();
	}
	
	
	
}







