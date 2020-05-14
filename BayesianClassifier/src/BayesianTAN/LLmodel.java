package BayesianTAN;

import DataStructures.Edge;
import DataStructures.Node;
import FileIO.Dataset;

public class LLmodel extends ScoreModel {

	protected double calculate(Edge edge, Dataset d) {
		Node child = edge.nodes()[0];
		Node parent = edge.nodes()[1];
		int s = d.getN_classes();
		int N = d.getN_size();
		double weight = 0;

		for (int j = 0; j < parent.get_ri(); j++) {
			for (int k = 0; k < child.get_ri(); k++) {
				for (int c = 0; c < s; c++) {
					int Nijkc = child.get_Npjkc()[parent.id()][j][k][c];
					int NJkc = child.get_NJkc()[k][c];
					int NKjc = parent.get_NJkc()[j][c];
					int Nc = d.getNc()[c];

					// Prevent log2() from exploding
					if (Nijkc != 0 && Nc != 0) {

						weight += ((double) Nijkc / N) * log2(((double) (Nijkc * Nc)) /((double) (NJkc * NKjc)));
					}
				}
			}
		}
		return weight;
	}
}
