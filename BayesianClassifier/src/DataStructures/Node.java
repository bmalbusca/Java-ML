package DataStructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import FileIO.Dataset;
import FileIO.Instance;




/** node
 * @provides Node definition - the parameters are only accessed by a method
 * @return None
 * @author brunocfigueiredo
 */

public class Node implements  Cloneable {
	
	protected int ID;			// node ID
	protected String label;		// Real name that comes with the CSV file
	
	
	//Useful data to calculate the weigths
	protected int ri;			// N values that xi can take
	protected int Npjkc[][][][];	// [p][j][k][c] ->p=parent ;   j-> parent value
	protected int NJkc[][];	// sum(Nijkc, j={1,..,qi}) - Number of instances where Xi takes its k-th value xik and C class takes c-th value
	protected double theta[][][];
	protected double thetac[];
	protected ArrayList<Edge> edges = new ArrayList<Edge>(); 		//edges seen by this node Xi
	protected Map<Edge, Node> map = new HashMap< Edge,Node>();				//Fast search for nodes-edge pairs
	
	//constructor 
	public Node(int id, String label) { //TO-DO check if id and label exists
		this.ID=id;
		this.label = label;
	}
	
	public int get_ri() {
		return ri;
	}
	
	public int[][][][] get_Npjkc() {
		return Npjkc;
	}
	
	public int[][] get_NJkc() {
		return NJkc;
	}
	
	public double[][][] get_theta() {
		return theta;
	}
	
	public double[] get_thetac() {
		return thetac;
	}
	
	public void addEdge(Edge e) {
		edges.add(e);
		
	}
	
	public void MapNodes(Edge e, Node n) {
		addEdge(e);
		map.put(e,n);
		
	}
	
	public Node getParent(Edge e) {
		Node parent = this.map.get(e);
		
		return parent; 
	}

	
	public ArrayList<Edge> edges(){
		return edges; 
	}
	
	
	public int id() {
		return this.ID; 
	}
	
	public String name() {
		return this.label;
	}

	
	public void sortEdges() {
		Collections.sort(edges,new EdgeCompare()); //Sort edge list
	}


	
	@Override
	protected Node clone() throws CloneNotSupportedException {

	    return (Node) super.clone();
	}
	
	public static void node_counts(Dataset d, Node n){
		n.ri = d.get_ri_val().get(n.ID);
		// Initialize Npjkc
		n.Npjkc = new int[d.get_ri_val().size()][][][];
		for(int p=0; p < d.get_ri_val().size() ; p++) {
			n.Npjkc[p] = new int[d.get_ri_val().get(p)][n.ri][d.getN_classes()];			
		}
		// Initialize NJkc
		n.NJkc = new int[n.ri][d.getN_classes()];
		// Count
		for(int i =0; i<d.getN_size(); i++) {
			Instance inst = d.getInstance(i+1); // First is atributes names
			int ki = Integer.parseInt(inst.get(n.ID));
			int ci = Integer.parseInt(inst.get(d.get_ri_val().size()));
			for(int p=0; p < d.get_ri_val().size() ; p++) {
				if(p == n.ID) {   // Empty parent configuration, we store it where node Xi is the parent of itself
					for(int k=0; k<n.ri; k++) {
						for(int c=0; c<d.getN_classes(); c++)
							if(ki==k && ci==c)
								n.Npjkc[p][0][k][c]++;
					}
					continue;
				}
				for(int j=0; j<d.get_ri_val().get(p); j++) {
					int ji = Integer.parseInt(inst.get(p));
					for(int k=0; k<n.ri; k++)
						for(int c=0; c<d.getN_classes(); c++)
							if(ji==j && ki==k && ci==c)
								n.Npjkc[p][j][k][c]++;    // Nijkc for this node and parent p
				}	
			}
			for(int k=0; k<n.ri; k++) {
				for(int c=0; c<d.getN_classes(); c++)
					if(ki==k && ci==c)
						n.NJkc[k][c]++;    // NJkc     xi-> k.th value    c-> c-th value
			}
		}
	}
	
	
	
}







