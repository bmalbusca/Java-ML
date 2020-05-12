package DataStructures;

import java.util.ArrayList;



public class Tree extends Graph{
	
	ArrayList<Node> nodes = super.nodes;// array of nodes
	ArrayList<Edge> edges = super.edges;// array of edges 
	protected Node Root;
	
	//constructor
	public Tree() {	 
	
		super.ne=0;
		super.n=0;
		
				
		
	}
	

	
	
	
	public void connect(Node n1, Node n2, Edge e) {
		
		//add node n2 to the connected nodes list of n1
		//add edge e to edge list seen by n1
		e.directed = true;
		
		super.ne++;
		edges.add(e);	
		n1.MapNodes(e, n2); 

	}

	
	public void printGraph() {
		for(Edge e : edges) {
			System.out.println(e.nodes[0].name()+"-"+e.nodes[1].name());
		}
	}
	
	public void printNodes() {
		System.out.print("Nodes [");
		for(Node X : nodes) {
			System.out.print(" "+X.name() );
		}
		System.out.println(" ]");
	}
	
	
	/** updateWeight
	 * @category method
	 * @provides updates vertex's weight  
	 * @return None
	 * @author brunocfigueiredo
	 */

	
	public void updateWeight( Edge e,  double weight) {
		
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


	public void addNewNode( Node n) {
		this.nodes.add(n);
		super.n++;
		
	}

	
	/** addNewEdge
	 * @category method
	 * @provides adds node to adjacency matrix
	 * @return None
	 * @author brunocfigueiredo
	 */


	public void addNewEdge( Edge e) {
		this.edges.add(e);
		
	}
	
	
	
	public ArrayList<Edge> getEdges(){
		return edges;
	}
	
	
	public ArrayList<Node> getNodes(){
		return nodes;
	}
	
	
	public void printEdges() {
		System.out.print("Edges [");
		for(Edge e: edges) {
			System.out.print(" "+e.nodes[0].label+"-"+e.nodes[1].label);
		}
		System.out.println(" ]");
	}

	public boolean contains(Node n) {
		for(Node ncomp : nodes) {
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
