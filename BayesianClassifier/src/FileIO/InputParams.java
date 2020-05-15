package FileIO;

import java.io.File;
import java.util.Arrays;
/**
 * 
 * This Class makes the input parameters parsing and saves the arguments as attributes
 * @author Group 20
 *
 */
public class InputParams {

	
	/**
	 *  String with pathname file of test file
	 */
	public String testfile = new String();
	
	/**
	 *  String with pathname file of train file
	 */
	public String trainfile = new String();
	
	/**
	 *  String with score model file 
	 */
	public String score = new String();
	
	protected String[] availableScore = {"LL", "MDL"} ;
	
	/**
	 * Check if is missing arguments and if the arguments are valid. In case of all valid, the method saves into attributes
	 * @param argsString  command line arguments
	 */
	public InputParams(String[] args) {
		 
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
	
	/**
	 * Method verifies if File exists. If not the program ends with a error
	 * @param filepath
	 */
	public void testFile(String filepath) {
			
		File file = new File(filepath);
		
		if (!file.exists()){
			System.err.println("The file " + filepath + " not exists");
	        System.exit(1);
		    }
	}
	
	/**
	 * Method verifies if the score model options is valid. If not the program ends with a error
	 * @param option
	 */
	 
	public void testScoreOption(String option) {
		
		if(!Arrays.stream(availableScore).anyMatch(option::equals)) {
			System.err.println("Score model option " + option + " not exists. Should be: " + Arrays.asList(availableScore) );	
	        System.exit(1);
		}
	}

}
