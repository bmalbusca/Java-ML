package DataStructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import FileIO.Instance;

public class UndirFullGraph extends Graph {
	
	ArrayList<Node> nodes = super.nodes;// array of nodes
	public ArrayList<Edge> edges = super.edges;// array of edges
	//protected  node C = new node();  // can be a way to get easy access to C nodes 
	
	
	// constructor
	public UndirFullGraph(Instance Xi) {

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

	public void connect(Node n1, Node n2, Edge e) {

		// add node n2 to the connected nodes list of n1
		// add edge e to edge list seen by n1
		n1.MapNodes(e, n2);

		if (!e.directed) {

			n2.MapNodes(e, n1);
		}

	}

	public void printGraph() {
		for (Node X : nodes) {
			System.out.print(X.name() + " ID=" + X.ID + " Edges=[");
			for (Edge e : X.edges()) {
				System.out.print("to" + X.map.get(e).label + "(" + e.weight() + "), ");
			}
			System.out.println("]");
		}
	}

	public void printNodes() {
		System.out.print("Nodes [");
		for (Node X : nodes) {
			System.out.print(" " + X.name());
		}
		System.out.println("]");
	}

	/**
	 * updateWeight
	 * 
	 * @category method
	 * @provides updates edge's weight
	 * @return None
	 * @author brunocfigueiredo
	 */

	public void updateWeight(Edge e, double weight) {

		if (e.weight() != -1) { // Alert user from a possible mistake
			System.out.println("*Atencion* this edge was already updated!");
		}
		System.out.println("Edge " + e.ID + " " + e.nodes[0].label + "to" + e.nodes[1].label + " updated!");
		e.addWeight(weight);

	}

	/**
	 * addNewNode
	 * 
	 * @category method
	 * @provides adds node to adjacency matrix
	 * @return None
	 * @author brunocfigueiredo
	 */

	public void addNewNode(Node n) {
		this.nodes.add(n);

	}

	/**
	 * addNewEdge
	 * 
	 * @category method
	 * @provides adds node to adjacency matrix
	 * @return None
	 * @author brunocfigueiredo
	 */

	public void addNewEdge(Edge e) {
		this.edges.add(e);

	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}

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

	public Tree MST() {		// Prim

		Tree T = new Tree(); // Create the structure for the MST
		ArrayList<Node> TreeNodes; // Receive the nodes that make part of MST
		Map<Edge, Node> candidateE = new HashMap<Edge, Node>(); // to save the higher value candidate edges

		int i;
		Edge e, eMax;

		T.addNewNode(nodes.get(0)); // pick the first node

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

	public int Nedges() {

		return super.ne;
	}

	public int Nnodes() {
		return super.n;
	}
}
