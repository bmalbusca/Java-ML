package bayesianTAN;

import dataStructures.*;
import fileIO.Dataset;

/** 
 *  Abstract class.
 *  Score Model to calculate weight of an edge in a graph.
 *  Implemented in LLmodel or MDLmodel
 *  
 *  @author Group 20
 */
public abstract class ScoreModel {
	
	/**
     * Implemented in LLmodel or MDLmodel
     */
	protected abstract double calculate(Edge edge, Dataset d);

	/** Stores the logarithm base-e of 2. */
	protected static final double ln2 = Math.log(2);

	/** Calculates the logarithm base-2 of a value.
	 *  
	 * @param number Number to calculate log2 of.
	 */
	protected final double log2(double number) {

		double log2Value = Math.log(number) / ScoreModel.ln2;

		if (Double.isNaN(log2Value))
			throw new RuntimeException("Tried to calculate log2(" + number + "): NaN");

		return log2Value;
	}
	
}
