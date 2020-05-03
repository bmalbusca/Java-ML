package DataStructures;


import java.util.ArrayList; // import the ArrayList class


public class undirGraph implements graph {

	protected int num_nodes;
	
	 
	public ArrayList<ArrayList<vertex>>  G;  //array of nodes
	
	public undirGraph(int num_nodes) {
	
		this.num_nodes= num_nodes;
		initG(num_nodes);
		
		
		
	}
	
	
	
	public void addEdge( int nodeID , vertex v) {
		G.get(nodeID).add(v);
		System.out.println("Edge inserted at node " + nodeID );
	}
	
	
	

	
	@Override
	public void initG(int n_nodes) {
		G = new ArrayList<ArrayList<vertex>>(n_nodes);
		
		for (int i = 0; i < n_nodes; i++) 
           G.add(new ArrayList<vertex>());
			
	}
	
	@Override
	public void printG() {
		
		for( ArrayList<vertex> node : G) {
			for( vertex edge: node)
				 System.out.print(edge.weight());
			
			System.out.println(node);
		}
		
		
		
	}

	
	
	
	
	
	
	
}
