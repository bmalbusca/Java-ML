package BayesianTAN;

import DataStructures.Edge;
import DataStructures.Node;
import FileIO.Dataset;

public class MDLmodel extends LLmodel {

	/*
	 * MDL uses the same formula as the LL, with a penalty applied to it.
	 * Subclass of LLmodel, overriding the calculate method to account for the penalization.
	 */

	@Override
	public double calculate(Edge edge, Dataset d) {
		Node child = edge.nodes()[0];	
		Node parent = edge.nodes()[1];
		double s = d.N_classes;
		double N = d.N_size;
		double LL = super.calculate(edge, d);
		double penalization = s * ((double) ((child.ri - 1) * (parent.ri - 1)) / 2) * Math.log(N);

		return LL - penalization;
	}
}
