package BayesianTAN;

import DataStructures.*;
import FileIO.Dataset;

/** Abstract class. Score Model to calculate weights of an edge in a graph. */
public abstract class ScoreModel {
	
	/**
	 * Calculates the weight of an edge in a graph, according to a certain Score Model.
	 * 
	 * @return Weight of the edge
	 */
	public abstract double calculate(Edge edge, Dataset d);

	/** Stores the logarithm base-e of 2. */
	protected static final double ln2 = Math.log(2);

	/** Calculates the logarithm base-2 of a value. */
	protected final double log2(double number) {

		double log2Value = Math.log(number) / ScoreModel.ln2;

		if (Double.isNaN(log2Value))
			throw new RuntimeException("Tried to calculate log2(" + number + "): NaN");

		return log2Value;
	}	
}
