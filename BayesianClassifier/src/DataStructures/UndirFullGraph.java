package DataStructures;


import java.util.ArrayList; // import the ArrayList class


public class UndirFullGraph implements graph {

	protected int n;	// number of nodes Xi
	public ArrayList<ArrayList<node>>  G;  // array of nodes
	
	
	//constructor
	public UndirFullGraph(int num_nodes) {	 
	
		this.n= num_nodes;		   // (num_nodes x num_nodes) size
		initG(num_nodes);				   // graph initialization
		
		
		
	}
	
	
	/** insert
	 * @category method
	 * @provides updates vertex's weight  
	 * @return None
	 * @author brunocfigueiredo
	 */

	
	public void insert( int parentID,int childID , int weight) {
		
		if((G.get(parentID).get(childID)).weight() != -1) {	// Alert user from a possible mistake 
			System.out.println("*Atencion* this vertex was already updated!");
		}
		
		(G.get(parentID).get(childID)).addWeight(weight);
		
		System.out.printf("Weight inserted at vextex [%d][%d] \n", parentID , childID );
		
	}
	
	
	/** addEdge
	 * @category method
	 * @provides adds edge to adjacency matrix
	 * @return None
	 * @author brunocfigueiredo
	 */


	protected void addEdge( int nodeID , vertex v) {
		G.get(nodeID).add(v);
		
	}
	
	
	/** initG
	 * @category interface method
	 * @provides 
	 * @return None
	 * @author brunocfigueiredo
	 */

	
	@Override
	  public void initG(int n_nodes) {
	 
		
		G = new ArrayList<node>(n_nodes);
							
		for (int i = 0; i < n_nodes; i++) {
			
			
           G.add(new ArrayList<vertex>(n_nodes));
		   
           for(int j =0; j < n_nodes; j++ ) {	//initialization
			   addEdge( i ,  new vertex(-1));		  
		   }		  
		}			
	}
	
	

	
	
	/** initG
	 * @category interface method
	 * @provides 
	 * @return None
	 * @author brunocfigueiredo
	 */

	@Override
	public void printG() {
		System.out.println("");
		for( ArrayList<vertex> node : G) {
			for( vertex edge: node)
				 System.out.print(" "+edge.weight()+" ");
			
			//System.out.println(node);
			System.out.println("");
		}
	}

	
	
	/*
	  public void initG(int n_nodes) {
	 
		
		G = new ArrayList<ArrayList<nodes>>(n_nodes);
						
		
		for (int i = 0; i < n_nodes; i++) {
         G.add(new ArrayList<vertex>(n_nodes));
		   
         for(int j =0; j < n_nodes; j++ ) {	//initialization
			   addEdge( i ,  new vertex(-1));		  
		   }		  
		}			
	}
	
	
	*/
	
	
	
	
	
	
}
