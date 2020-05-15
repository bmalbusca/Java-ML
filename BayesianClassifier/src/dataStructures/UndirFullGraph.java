package dataStructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import fileIO.Instance;

/**
 * This class represents a Undirected Fully connected graph which is a sub-type of graph data structure 
 * @author Group 20
 *
 */
public class UndirFullGraph extends Graph {
	
	protected ArrayList<Node> nodes = super.nodes; // array of nodes
	protected ArrayList<Edge> edges = super.edges; // array of edges
	
	/**
	 * Constructor method
	 * @param Xi Data set instance 
	 * @exception NullPointerException for Features names that don't exist
	 * @see Graph
	 */
	public UndirFullGraph(Instance Xi) {// constructor

		super.ne = 0;

		try {
			super.n = Xi.len() - 1;
		} catch (NullPointerException e) {
			System.out.print("Caught NullPointerException! Features names does not exists");
			System.exit(1);
		}

		// initNodes
		nodes.clear();
		InitNodes(Xi);

	}
	
	private void InitNodes(Instance Xi) {

		for (int i = 0; i < super.n; ++i) {
			Node x = new Node(i, Xi.get(i));
			nodes.add(x);

			for (int j = i; j > 0; --j) {

				Edge e = new Edge(-1, nodes.get(i), nodes.get(j - 1));

				++super.ne;
				e.ID = ne;

				connect(nodes.get(i), nodes.get(j - 1), e);
				edges.add(e);

			}
		}
	}
	
	/**
	 * This method connect the Node 1 to the Node e with edge e
	 */
	public void connect(Node n1, Node n2, Edge e) {

		// add node n2 to the connected nodes list of n1
		// add edge e to edge list seen by n1
		n1.MapNodes(e, n2);

		if (!e.directed) {

			n2.MapNodes(e, n1);
		}

	}

	/**
	 *  The method prints the graph  to the command line
	 */
	public void printGraph() {
		for (Node X : nodes) {
			System.out.print(X.name() + " ID=" + X.ID + " Edges=[");
			for (Edge e : X.edges()) {
				System.out.print("to" + X.map.get(e).label + "(" + e.weight() + "), ");
			}
			System.out.println("]");
		}
	}
	
	/**
	 * Prints the nodes of this graph
	 */
	public void printNodes() {
		System.out.print("Nodes [");
		for (Node X : nodes) {
			System.out.print(" " + X.name());
		}
		System.out.println("]");
	}

	/**
	 * Method updates edge's weight
	 * @param e edge to update
	 * @param weight int value of weight
	 */
	public void updateWeight(Edge e, double weight) {

		if (e.weight() != -1) { // Alert user from a possible mistake
			System.out.println("*Atencion* this edge was already updated!");
		}
		e.addWeight(weight);

	}

	/**
	 * addNewNode method adds node to the graph
	 */
	public void addNewNode(Node n) {
		this.nodes.add(n);

	}

	/**
	 * addNewEdge method adds edge to the graph
	 */
	public void addNewEdge(Edge e) {
		this.edges.add(e);

	}

	/**
	 * the method returns the list of edges 
	 * @return ArrayList of Edge list of edges
	 */
	public ArrayList<Edge> getEdges() {
		return edges;
	}

	/**
	 * the method returns the list of nodes
	 * @return ArrayListof Node list of nodes
	 */
	public ArrayList<Node> getNodes() {
		return nodes;
	}
	
	protected ArrayList<Node> cloneListNodes() {

		ArrayList<Node> clone = new ArrayList<Node>();
		Node copy;

		for (int i = 0; i < this.n; ++i) {
			try {

				copy = (Node) (nodes.get(i)).clone();
				clone.add(copy);

			} catch (CloneNotSupportedException e) {

				e.printStackTrace();
			}
		}
		return clone;
	}
	
	/**
	 * the method calculates the Maximum spanning Tree using Prim's algorithm and returns the respective MST Tree  
	 * @return Tree returns a graph's MST
	 */
	public Tree MST() {		// Prim

		Tree T = new Tree(); // Create the structure for the MST
		ArrayList<Node> TreeNodes; // Receive the nodes that make part of MST
		Map<Edge, Node> candidateE = new HashMap<Edge, Node>(); // to save the higher value candidate edges

		int i;
		Edge e, eMax;

		T.addNewNode(nodes.get(0)); // pick the first node
		T.Root = nodes.get(0);

		do { 

			TreeNodes = T.getNodes();

			for (Node nFrom : TreeNodes) {
				for (i = 0; i < nFrom.edges.size(); i++) {		//passar isto para for( : )
					e = nFrom.edges.get(i);
					if (!T.contains(nFrom.map.get(e))) {	//{ key:value  }; key=edge value=no
						candidateE.put(e, nFrom);
						break;
					}
				}
			}

			eMax = candidateE.keySet().iterator().next(); // lets pick a reference

			for (Edge ec : candidateE.keySet()) {
				if (eMax.weight() < ec.weight()) {
					eMax = ec;
				}
			}
			
			T.addNewEdge(eMax);
			T.addNewNode(candidateE.get(eMax).map.get(eMax)); // FromNode in this edge find ToNode
			candidateE.clear();

		} while (TreeNodes.size() < super.n);
		
		return T;

	}

	/**
	 * Gives the number of edges in the Graph
	 * @return int number of edges
	 */
	public int Nedges() {

		return super.ne;
	}
	/**
	 * Gives the number of nodes in the Graph
	 * @return int number of nodes
	 */
	public int Nnodes() {
		return super.n;
	}
}
