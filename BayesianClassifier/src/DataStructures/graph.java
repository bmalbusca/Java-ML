package DataStructures;


public  interface graph {
	

	public void printGraph() ;
	public void connect(node n1, node n2, edge e, boolean directed);
	public void addNewNode( node n);
	public void addNewEdge( edge e);
	public int Nnodes();
	public int Nedges();
	


}
