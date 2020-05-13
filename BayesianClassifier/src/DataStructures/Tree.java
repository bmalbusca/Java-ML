package DataStructures;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import FileIO.Dataset;



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
			System.out.println(e.nodes[1].name()+"-"+e.nodes[0].name());
		}
	}
	
	
	public void printClassifier() {
		System.out.println("Classifier :\t" + Root.name() + " : class");
		for(int i=0; i<edges.size(); i++) {
			for (Edge e : edges) {
				if(e.nodes[1].ID == i+1)
					System.out.println("\t\t" + e.nodes[1].label + " : class " + e.nodes[0].label);
			}
		}
	}
	
	public void printNodes() {
		System.out.print("Nodes [");
		for(Node X : nodes) {
			System.out.print(" "+X.name() );
		}
		System.out.println(" ]");
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
	
	/**
	 * Chooses a node arbitrarily and transforms an undirected tree into a directed one.
	 */
	public void directTree() {

		Set<Edge> visitedEdges = new HashSet<Edge>(edges.size());
		Queue<Node> bag = new LinkedList<Node>();
		bag.add(Root);  // Root node is the first one
		
		// Put nodes in a bag, recursively
		while (!bag.isEmpty()) {

			// Get a node
			Node node = bag.poll();

			for (Edge e : edges) {
				// Prevent us from going up in the tree
				if (visitedEdges.contains(e))
					continue;
				if(node.name().equals(e.nodes[1].name()) || node.name().equals(e.nodes[0].name())) {
					e.directed = true;
					visitedEdges.add(e);
	
					// Make sure the edges point outwards (down)
					if (!e.nodes[0].equals(node)) {   // nodes[0] = parent
						e.flip();
					}
	
					// Add all the children to the bag, so their edges are also flipped outwards
					bag.add(e.nodes[1]); // nodes[1] = child	
				}	
			}
		}
	}
	/*
	public void updateNodeThetas(Dataset data) {

		for (Edge e : edges) {

			// Find the node's parent to know how many parameters there will be
			// If the node has no parent, there is only one row of parameters
			Node parent = getParent(node);
			int parentRange = parent == null ? 1 : parent.attribute.getRange();
			int parentIndex = parent == null ? node.attribute.index : parent.attribute.index;

			node.parameters = new double[parentRange][node.attribute.getRange()][outputNode.attribute.getRange()];

			for (int j = 0; j < node.parameters.length; j++) {
				for (int k = 0; k < node.parameters[j].length; k++) {
					for (int c = 0; c < node.parameters[j][k].length; c++) {

						int count = node.counts[parentIndex][j][k][c];

						int kCount = 0;

						// If the node has no parent, counting the times
						// "the parent has any configuration and C has c-th value"
						// is just counting the times "c has c-th value"
						if (parent != null)
							kCount = parent.jCounts[j][c];
						else
							kCount = outputNode.cCounts[c];

						node.parameters[j][k][c] = (count + 0.5) / (kCount + node.attribute.getRange() * 0.5);

					}
				}
			}
		}*/

}
