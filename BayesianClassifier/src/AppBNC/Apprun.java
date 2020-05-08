package AppBNC;

import java.io.FileNotFoundException;

import BayesianTAN.TAN;
import FileIO.*;

/* O que falta fazer neste momento -  Project progress: %45
 * 
 * - Falta verificar antes de inserir um novo no, se este ja existe
 * - O mesmo aplica-se para os edges
 * - Falta Fazer as contagens e fazer update dos pesos - Implementar LL e MDL
 * - Falta tornar UndirFullgraph.MST() mais eficiente - O algoritmo testa sempre todas edges existentes apartir de  um no
 * mesmo quando ja faz parte da MST Tree 
 * - Falta fazer os passos 4 e 5 da Structure Learning (pag 3)
 * - Falta implemntar o predict - Parameter learning (pag 3 - slack Q&A)
 * - Falta implementar metrics F1.score, accuracy, AvgAccuracy
 */

public class Apprun {
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		
		
		InputParams param = new InputParams(args); 		//Parse input arguments
		ReadCSV file = new ReadCSV(param.trainfile);	//Read input file and return the content
		file.print();
		
		
		TAN bncModel = new TAN(file.data().getInstance(0), param.score);	// TAN(Features array, Score Model)
		
		bncModel.train();	//not implemented
		bncModel.predict();	//not implemented

	
	
	}

	
	

}