package bnc;
import DataStructures.*;


public class Apprun {
	
	
	public static void main(String[] args) {
		
	
		//vertexID vtest = new vertexID(1,2);
		undirGraph G = new undirGraph(10);
		G.printG();
		vertex v = new vertex(3);
		G.addEdge(1, v);
		G.addEdge(1, v);
		G.addEdge(1, v);
		G.printG();
		
		//System.out.println(vtest.weight() );
		//System.out.println(vtest.id());
		
	}

}
