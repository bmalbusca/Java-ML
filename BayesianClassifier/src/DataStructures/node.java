package DataStructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import FileIO.dataset;
import FileIO.instance;




/** node
 * @provides Node definition - the parameters are only accessed by a method
 * @return None
 * @author brunocfigueiredo
 */

public class node implements  Cloneable {
	
	protected int ID;			// node ID
	protected String label;		// Real name that comes with the CSV file
	
	
	//Useful data to calculate the weigths
	public int ri;			// N values that xi can take
	public int Npjkc[][][][];	// [p][j][k][c] ->p=parent ;   j-> parent value
	public int NJkc[][];	// sum(Nijkc, j={1,..,qi}) - Number of instances where Xi takes its k-th value xik and C class takes c-th value
	public double theta[][][];
	
	//protected ArrayList<node> LinkedNodes = new ArrayList<node>();  //parent nodes or simply the connected nodes to Xi
	protected ArrayList<edge> edges = new ArrayList<edge>(); 		//edges seen by this node Xi
	Map<edge, node> map = new HashMap< edge,node>();				//Fast search for nodes-edge pairs
	
	//constructor 
	public node(int id, String label) { //TO-DO check if id and label exists
		this.ID=id;
		this.label = label;
	}
	
	public void addEdge(edge e) {
		edges.add(e);
		
	}
	
	public void MapNodes(edge e, node n) {
		addEdge(e);
		map.put(e,n);
		
	}
	
	public node getParent(edge e) {
		node parent = this.map.get(e);
		
		return parent; 
	}

	
	public ArrayList<edge> edges(){
		return edges; 
	}
	
	
	public int id() {
		return this.ID; 
	}
	
	public String name() {
		return this.label;
	}

	
	public void sortEdges() {
		Collections.sort(edges,new edgeCompare()); //Sort edge list
	}


	
	@Override
	protected node clone() throws CloneNotSupportedException {

	    return (node) super.clone();
	}
	
	public static void node_counts(dataset d, node n){
		n.ri = d.ri_val.get(n.ID);
		// Initialize Npjkc
		n.Npjkc = new int[d.ri_val.size()][][][];
		for(int p=0; p < d.ri_val.size() ; p++) {
			n.Npjkc[p] = new int[d.ri_val.get(p)][n.ri][d.N_classes];			
		}
		// Initialize NJkc
		n.NJkc = new int[n.ri][d.N_classes];
		// Count
		for(int i =0; i<d.N_size; i++) {
			instance inst = d.getInstance(i+1); // First is atributes names
			int ki = Integer.parseInt(inst.get(n.ID));
			int ci = Integer.parseInt(inst.get(d.ri_val.size()));
			for(int p=0; p < d.ri_val.size() ; p++) {
				if(p == n.ID) {   // Empty parent configuration, we store it where node Xi is the parent of itself
					for(int k=0; k<n.ri; k++) {
						for(int c=0; c<d.N_classes; c++)
							if(ki==k && ci==c)
								n.Npjkc[p][0][k][c]++;
					}
					continue;
				}
				for(int j=0; j<d.ri_val.get(p); j++) {
					int ji = Integer.parseInt(inst.get(p));
					for(int k=0; k<n.ri; k++)
						for(int c=0; c<d.N_classes; c++)
							if(ji==j && ki==k && ci==c)
								n.Npjkc[p][j][k][c]++;    // Nijkc for this node and parent p
				}	
			}
			for(int k=0; k<n.ri; k++) {
				for(int c=0; c<d.N_classes; c++)
					if(ki==k && ci==c)
						n.NJkc[k][c]++;    // NJkc     xi-> k.th value    c-> c-th value
			}
		}
	}
	
	
	
}







