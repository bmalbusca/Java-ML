package DataStructures;

import java.util.ArrayList;



public class Tree extends graph{
	
	ArrayList<node> nodes = super.nodes;// array of nodes
	ArrayList<edge> edges = super.edges;// array of edges 
	protected node Root;
	
	//constructor
	public Tree() {	 
	
		super.ne=0;
		super.n=0;
		
				
		
	}
	

	
	
	
	public void connect(node n1, node n2, edge e) {
		
		//add node n2 to the connected nodes list of n1
		//add edge e to edge list seen by n1
		e.directed = true;
		
		super.ne++;
		edges.add(e);	
		n1.MapNodes(e, n2); 

	}

	
	public void printGraph() {
		for(node X : nodes) {
			System.out.print(X.name()+ " ID=" + X.ID+ "]" );
		}
	}
	
	
	
	/** updateWeight
	 * @category method
	 * @provides updates vertex's weight  
	 * @return None
	 * @author brunocfigueiredo
	 */

	
	public void updateWeight( edge e,  double weight) {
		
		if(e.weight() != -1) {	// Alert user from a possible mistake 
			System.out.println("*Atencion* this vertex was already updated!");
		}
		System.out.println("Edge "+ e.ID + " " + e.nodes[0].label + "to" +e.nodes[1].label+  " updated!");
		e.addWeight(weight);
		
		
	}
	
	
	/** addNewNode
	 * @category method
	 * @provides adds node to adjacency matrix
	 * @return None
	 * @author brunocfigueiredo
	 */


	public void addNewNode( node n) {
		this.nodes.add(n);
		super.n++;
		
	}

	
	/** addNewEdge
	 * @category method
	 * @provides adds node to adjacency matrix
	 * @return None
	 * @author brunocfigueiredo
	 */


	public void addNewEdge( edge e) {
		this.edges.add(e);
		
	}
	
	
	
	public ArrayList<edge> getEdges(){
		return edges;
	}
	
	
	public ArrayList<node> getNodes(){
		return nodes;
	}
	
	
	public void printEdges() {
		System.out.print("[");
		for(edge e: edges) {
			System.out.print(" "+e.nodes[0].label+"-"+e.nodes[1].label);
		}
		System.out.println("]");
	}

	public boolean contains(node n) {
		for(node ncomp : nodes) {
			if(ncomp.label.equals(n.label)) {
				return true;
			}
		}
		return false;
	}
	
	
	public int Nedges() {
		
		return super.ne;
	}

	public int Nnodes() {
		return super.n;
	}

}
