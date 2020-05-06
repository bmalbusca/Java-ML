package FileIO;

import java.io.File;
import java.util.Arrays;

public class ParseInput {

	
	
	public String testfile = new String();
	public String trainfile = new String();
	public String score = new String();
	protected String[] availableScore = {"LL", "MDL"} ;
	
	
	
	
	
	public ParseInput(String[] args) {
		 
		if(args.length < 3) {
			
			 System.err.println("Not enough Arguments.  Must be java -jar BayesianClassifier.jar  train_path test_path score_model");
		        System.exit(1);
		}
		this.trainfile = args[0];
		this.testfile = args[1];
		this.score = args[2];
		
		testFile(this.trainfile);
		testFile(this.testfile);
		testScoreOption(this.score);
		

	
	}
	
	
	public void testFile(String filepath) {
			
		File file = new File(filepath);
		
		if (!file.exists()){
			System.err.println("The file " + filepath + " not exists");
	        System.exit(1);
		    }
		
		
		
		
	}
	
	
	public void testScoreOption(String option) {
		
		if(!Arrays.stream(availableScore).anyMatch(option::equals)) {
			System.err.println("Score model option " + option + " not exists. Should be: " + Arrays.asList(availableScore) );	
	        System.exit(1);
		}
		 
	}


	
	
	
}
