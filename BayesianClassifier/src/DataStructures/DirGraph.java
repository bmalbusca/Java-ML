package DataStructures;

import java.util.ArrayList;

public class DirGraph implements graph{

	protected int num_nodes;
	
	 
	public ArrayList<ArrayList<vertexDirected>>  G;  //array of nodes
	
	public DirGraph(int num_nodes) {
	
		this.num_nodes= num_nodes;
		initG(num_nodes);
		
		
	}
	
	
	
	public void addEdge( int nodeID , vertexDirected v) {
		G.get(nodeID).add(v);
		System.out.println("Edge inserted at node " + nodeID );
	}
	
	

	
	@Override
	public void initG(int n_nodes) {
		G = new ArrayList<ArrayList<vertexDirected>>(n_nodes);
		
		for (int i = 0; i < n_nodes; i++) 
           G.add(new ArrayList<vertexDirected>());
			
	}
	
	@Override
	public void printG() {
		
		for( ArrayList<vertexDirected> node : G) {
			for( vertexDirected edge: node)
				 System.out.print(edge.weight());
			
			System.out.println(node);
		}
		
		
	
	
	
	}
}
