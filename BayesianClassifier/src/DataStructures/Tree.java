package DataStructures;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import FileIO.Dataset;


/**
 * This class represents a Tree data structure and is a sub-type of graph type  
 * @author Group 20
 *
 */
public class Tree extends Graph{
	
	protected ArrayList<Node> nodes = super.nodes;// array of nodes
	protected ArrayList<Edge> edges = super.edges;// array of edges 
	protected Node Root;
	
	
	/**
	 * Constructor method. Initialize the inherited attributes 
	 */
	public Tree() {	 //constructor
	
		super.ne=0;
		super.n=0;
	}
	

	
	
	/**
	 * This method connect the Node 1 to the Node e with edge e
	 * @return None
	 */
	public void connect(Node n1, Node n2, Edge e) {
		
		//add node n2 to the connected nodes list of n1
		//add edge e to edge list seen by n1
		e.directed = true;
		
		super.ne++;
		edges.add(e);	
		n1.MapNodes(e, n2); 

	}

	/**
	 *  The method prints the graph  to the command line  
	 * 	@return None
	 */
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
	
	/**
	 * Prints the nodes of this graph
	 * @return None
	 */
	public void printNodes() {
		System.out.print("Nodes [");
		for(Node X : nodes) {
			System.out.print(" "+X.name() );
		}
		System.out.println(" ]");
	}
		
	
	/**
	 *  adds a node to graph and updates the attribute that saves the total number of nodes in this graph 
	 * @return None
	 */
	public void addNewNode( Node n) {
		this.nodes.add(n);
		super.n++;
		
	}

	
	/** 
	 * method adds an edge to edges list
	 * @return None
	 */
	public void addNewEdge( Edge e) {
		this.edges.add(e);
		
	}
	
	
	/**
	 * Returns all edges of this graph
	 * @return ArrayList<Edge> list of edges
	 */
	public ArrayList<Edge> getEdges(){
		return edges;
	}
	
	/**
	 * Returns all nodes of this graph
	 * @return ArrayList<Nodes> list of nodes
	 */
	public ArrayList<Node> getNodes(){
		return nodes;
	}
	
	/**
	 * Prints all edges  to the command line
	 * @return None
	 */
	public void printEdges() {
		System.out.print("Edges [");
		for(Edge e: edges) {
			System.out.print(" "+e.nodes[0].label+"-"+e.nodes[1].label);
		}
		System.out.println(" ]");
	}

	/**
	 * Checks if graph contains already a node n
	 * @param n node 
	 * @return boolean return True if contains the node n and false otherwise
	 */
	public boolean contains(Node n) {
		for(Node ncomp : nodes) {
			if(ncomp.label.equals(n.label)) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Returns the root node object
	 * @return Node object 
	 */
	public  Node root() {
		return this.Root;
	}
	
	
	/**
	 * Gives the number of edges in this Tree
	 */
	public int Nedges() {
		
		return super.ne;
	}
	
	/**
	 * Gives the number of nodes in this Tree
	 */
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
	
	public void updateThetas(Dataset data) {
		
		// We calculate the thetas for the Root, since it is the only with no parent.
		Root.theta = new double[1][Root.ri][data.getN_classes()];
		for (int k = 0; k < Root.ri; k++) {
			for (int c = 0; c < data.getN_classes(); c++) {

				int Npjkc = Root.Npjkc[0][0][k][c];

				int	NKjc = data.getNc()[c];

				Root.theta[0][k][c] = ((double)(Npjkc + 0.5)) / ((double)(NKjc + Root.ri * 0.5));
			}
		}		
		
		// We calculate the thetas for the child node of each edge
		for (Edge e : edges) {

			Node parent = e.nodes[0];
			Node child = e.nodes[1];
			int parentRange = parent.ri;
			int childRange = child.ri;
			int parentID = parent.ID;

			child.theta = new double[parentRange][childRange][data.getN_classes()];

			for (int j = 0; j < parentRange; j++) {
				for (int k = 0; k < childRange; k++) {
					for (int c = 0; c < data.getN_classes(); c++) {

						int Npjkc = child.Npjkc[parentID][j][k][c];

						int	NKjc = parent.NJkc[j][c];

						child.theta[j][k][c] = ((double)(Npjkc + 0.5)) / ((double)(NKjc + childRange * 0.5));
					}
				}
			}
		}
		// Update theta_c
		Node C= new Node(n+1, "C");
		C.thetac= new double[data.getN_classes()];
		C.ri = data.getN_classes(); 
		nodes.add(C);
		
		data.set_thetac(new double[data.getN_classes()]);
		for (int i = 0; i < data.getN_classes(); i++) {
			data.get_thetac()[i] = ((double)(data.getNc()[i] + 0.5)) / ((double)(data.getN_size() + data.getN_classes() * 0.5));
			C.thetac[i] =  data.get_thetac()[i];
		}
	}
}
