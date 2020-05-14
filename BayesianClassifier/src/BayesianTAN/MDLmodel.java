package BayesianTAN;

import DataStructures.Edge;
import DataStructures.Node;
import FileIO.Dataset;

/**
 * Calculates the weight of an edge using the Minimum Description Length score.
 * MDL uses the same formula as the LL, with a penalty applied to it.
 * Subclass of LLmodel, overriding the calculate method to account for the penalization.
 * 
 * @author Group 20
 */
public class MDLmodel extends LLmodel {

	@Override
	protected double calculate(Edge edge, Dataset d) {
		Node child = edge.nodes()[0];	
		Node parent = edge.nodes()[1];
		double s = d.getN_classes();
		double N = d.getN_size();
		double LL = super.calculate(edge, d);
		double penalization = s * ((double) ((child.get_ri() - 1) * (parent.get_ri() - 1)) / 2) * Math.log(N);

		return LL - penalization;
	}
	
}
