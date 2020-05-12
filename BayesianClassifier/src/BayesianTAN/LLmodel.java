package BayesianTAN;

import DataStructures.Edge;
import DataStructures.Node;
import FileIO.Dataset;

public class LLmodel extends ScoreModel {

	public double calculate(Edge edge, Dataset d) {
		Node child = edge.nodes()[0];
		Node parent = edge.nodes()[1];
		int s = d.N_classes;
		int N = d.N_size;
		double weight = 0;

		for (int j = 0; j < parent.ri; j++) {
			for (int k = 0; k < child.ri; k++) {
				for (int c = 0; c < s; c++) {
					int Nijkc = child.Npjkc[parent.id()][j][k][c];
					int NJkc = child.NJkc[k][c];
					int NKjc = parent.NJkc[j][c];
					int Nc = d.Nc[c];

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
