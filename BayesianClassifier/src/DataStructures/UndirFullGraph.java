package DataStructures;


import java.util.ArrayList;
//import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import FileIO.instance;


public class UndirFullGraph extends graph {

	ArrayList<node> nodes = super.nodes;// array of nodes
	ArrayList<edge> edges = super.edges;// array of edges 	 
	
	//constructor
	public UndirFullGraph(instance Xi) {	 
	
		super.ne =0;
		
		try {
			super.n= Xi.len()-1; 
		}catch (NullPointerException e) {
			System.out.print("Caught NullPointerException! Features names does not exists");
			System.exit(1);
		}
		
		//initNodes
		nodes.clear();
		for(int i =0; i< super.n;++i) {
			node x = new node(i,Xi.get(i));
			nodes.add(x);
			
			for(int j=i; j>0; --j) {
				
				edge e = new edge(-1,nodes.get(i),nodes.get(j-1));
				
				++ne;
				e.ID=ne; 
				
				connect(nodes.get(i), nodes.get(j-1), e);				
				edges.add(e);
				
				
			}
	
		}
		
	}
	
	
	
	

	
public void connect(node n1, node n2, edge e) {
		
		//add node n2 to the connected nodes list of n1
		//add edge e to edge list seen by n1
		n1.MapNodes(e, n2); 
		
		if(!e.directed) {

			n2.MapNodes(e, n1);
		}
		
	
	
	}

	
	public void printGraph() {
		for(node X : nodes) {
			System.out.print(X.name()+ " ID=" + X.ID + " Edges=[");
			for(edge e : X.edges()) {	
				System.out.print("to" + X.map.get(e).label +"("+e.weight() +"), ");
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
	
	

	
	
	
	protected ArrayList<node> cloneListNodes(){
		
		ArrayList<node> clone = new ArrayList<node>();		
		node copy;
		
		for (int i = 0; i < this.n ; ++i) {
			try {
				
				copy = (node) (nodes.get(i)).clone();
				clone.add(copy);
			
			} catch (CloneNotSupportedException e) {
				
				e.printStackTrace();
			}
		}			
		return clone;	
	}
	
	
public Tree MST(){
		
		Tree T = new Tree();									// Create the structure for the MST
		ArrayList<node> TreeNodes;								// Receive the nodes that make part of MST
		Map<edge, node> candidateE = new HashMap< edge,node>();	//to save the higher value candidate edges
		
		int i; 
		edge e, eMax;
		
		
		
		T.addNewNode(nodes.get(0)); //pick the first node
		
		
		
			
		do {
			
			TreeNodes = T.getNodes(); 						
		
			for(node n : TreeNodes) {
				//System.out.println("Node "+n.label +":");
				for(i=0; i<n.edges.size(); i++) {
					//System.out.print(" edge "+i+" ");
					e= n.edges.get(i);
					
					//System.out.println("Tree Contains ? "+n.map.get(e).label);
					if (! T.contains(n.map.get(e)) ) { 
						//System.out.println("No: "+e.nodes[0].label+"-"+e.nodes[1].label+ " (" +n.label+ "-"+ n.map.get(e).label +")");
						candidateE.put(e,n);
						break;
					}
					//System.out.println("Yes");
				}
				//System.out.println();		
			}
			
			/*if(candidateE.isEmpty()) {
				//System.out.println("Tree result"+ T.getNodes().size()+" "+T.getNodes() );
				break;
			}
			*/
			//System.out.println("Candidates: "+candidateE);
			eMax = candidateE.keySet().iterator().next();	//lets pick a reference
			
			
			for (edge ec : candidateE.keySet()) {
			    
				if(eMax.weight()<ec.weight()) {
					eMax=ec;
				}	
	
			}
			
			
			T.addNewEdge(eMax);
			T.addNewNode(candidateE.get(eMax).map.get(eMax)); //Max Edge -> FromNode() with this edge -> ToNode()
			
			//System.out.println("Edge Added and "+ candidateE.get(eMax).map.get(eMax).label  + " Connected");
			//System.out.println(T.getNodes()); 
			
			candidateE.clear();
	
			
			
		}while(TreeNodes.size()<super.n);
		
		return T; 
		
	}
	

	
	
	
	public int Nedges() {
		
		return super.ne;
	}

	public int Nnodes() {
		return super.n;
	}
}
