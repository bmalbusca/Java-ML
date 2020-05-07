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

public class node  {
	
	protected int ID;			// node ID
	protected String label;		// Real name that comes with the CSV file
	protected boolean visited = false;
	
	//Useful data to calculate the weigths
	public int ri;			// N values that xi can take
	public int Njkc[][][];	// Nijkc counts
	public int NKjc[][][];  // sum(Nijkc, k={1,..,ri}) - Number of instances where the possible parents take their j-th and C class takes c-th value
	public int NJkc[][][];	// sum(Nijkc, j={1,..,qi}) - Number of instances where Xi takes its k-th value xik and C class takes c-th value
	
	//Here we will use the same index to know each edge from this node are related to others nodes
	protected ArrayList<node> LinkedNodes = new ArrayList<node>(); //parent nodes or simply the connected nodes to Xi
	protected ArrayList<edge> edges = new ArrayList<edge>(); //edges seen by this node Xi
	Map<edge, node> map = new HashMap< edge,node>();
	
	//constructor 
	public node(int id, String label) {
		this.ID=id;
		this.label = label;
	}
	
	public void addNode(node n) {
		LinkedNodes.add(n);
	}
	
	public void addEdge(edge e) {
		edges.add(e);
		
	}
	
	public void MapNodes(edge e, node n) {
		//edges.add(e);
		map.put(e,n);
		
	}
	
	public ArrayList<node> connNodes(){
		return LinkedNodes;
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


	
}







