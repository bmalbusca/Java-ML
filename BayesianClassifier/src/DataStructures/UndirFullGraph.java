package DataStructures;


import java.util.ArrayList; 
import FileIO.instance;


public class UndirFullGraph implements graph {

	protected int n;	// number of nodes Xi
	protected ArrayList<node>  nodes = new ArrayList<node>();  // array of nodes
	protected ArrayList<edge> edges = new ArrayList<edge>();  	// array of edges - to compute the MST we only need the edges list, adding a root is than easy to build the tree
	
	
	//constructor
	public UndirFullGraph(instance Xi) {	 
		int i,j;
		
		try {
			this.n= Xi.len()-1; 
		}catch (NullPointerException e) {
			System.out.print("Caught NullPointerException! Features names does not exists");
			System.exit(1);
		}
		
		//initNodes
		nodes.clear();
		for(i =0; i< this.n;++i) {
			node x = new node(i,Xi.get(i));
			nodes.add(x);
			
			for(j=i; j>0; --j) {
				
				edge e = new edge(-1,nodes.get(i),nodes.get(j-1));	
				connect(nodes.get(i), nodes.get(j-1), e,false);				
				edges.add(e);
				
				
			}
	
		}
		
		
		
	}
	
	
	public void connect(node n1, node n2, edge e, boolean directed) {
		
		n1.addNode(n2);		//add node n2 to the connected nodes list of n1
		n1.edges.add(e);	//add edge e to edge list seen by n1
		
		if(!directed) {
			n2.addNode(n1);
			n2.edges.add(e);
		}
		else {
			e.directed=directed; 
		}
	
	
	}
	
	public void printGraph() {
		for(node X : nodes) {
			System.out.print(X.name()+ " ID=" + X.ID + " Edges=[");
			for(node n :  X.connNodes()) {
				System.out.print("to" + n.name()+", ");
			}
			System.out.println("]");
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
	
	
	
	
	
}
