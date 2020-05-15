package AppBNC;

import java.io.FileNotFoundException;

import BayesianTAN.TAN;
import FileIO.*;
import Metrics.Measure;


public class Apprun {
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		
		InputParams param = new InputParams(args); 		//Parse input arguments
		ReadCSV file = new ReadCSV(param.trainfile);	//Read input file and return the content
		//file.print();
		
		
		TAN bncModel = new TAN(param.score);	// TAN(Features array, Score Model)
		bncModel.train(file.data());	
		
		
		ReadCSV fileTest = new ReadCSV(param.testfile);	//Read input file and return the content
		Dataset predict = bncModel.predict(fileTest.data());	
		
	
		Measure  metrics = new Measure(predict ,fileTest.data());
		metrics.print();
	}

}