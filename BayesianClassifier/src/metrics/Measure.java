package metrics;

import java.util.ArrayList;

import fileIO.Dataset;

/**
 * Class for the assessment metrics of the classifier: accuracy, specificity, sensitivity and F1score
 * @author Group 20
 */
public class  Measure {

	/*
	 *  variables metrics
	 * 
	 */
	protected ArrayList<Integer[]> confusionMatrix ;
	
	protected double accValue = 0; 
	protected double [] sensitivity;
	protected double [] specificity;
	protected double [] F1_score;
	protected int [] Nc;
	
	
	/*
		"TP of C1" is all C1 instances that are classified as C1.
		"TN of C1" is all non-C1 instances that are not classified as C1.
		"FP of C1" is all non-C1 instances that are classified as C1.
		"FN of C1" is all C1 instances that are not classified as C1.
		
							 C1	test Predicted	 
							 *  c1     c1  TP	
							 *  c1	   c2  FN		
							 *  c3     c1  FP
							 *  c4     c4  TN
		
	*/
	
	/**
	 * Reads class values on predition data and test data and compares them.
	 * Calculates TP, TN, FP and FN based on the results.
	 * Calculates accuracy and runs the methods to calculate other metrics
	 * @param Cpredict Dataset with preditions
	 * @param Ctest Dataset with test data
	 */
	public Measure ( Dataset Cpredict,Dataset Ctest) {
		
		if(Cpredict.getN_classes() != Ctest.getN_classes()) {

			 System.err.println("Testfile and Predicted File are different!.  Must have the same number of classes");
		     System.exit(1);
		}
			
		int idC = Cpredict.getInstance(0).len()-1;
		double correctC=0, incorrectC=0; //for accuracy measurement
		Nc = new int[Ctest.getN_classes()];
		confusionMatrix = new ArrayList<Integer[]>(Cpredict.getN_classes()); 
		for(int i=0; i<Cpredict.getN_classes();++i ) {
			
			confusionMatrix.add(new Integer[] {0,0,0,0}); //[FN FP TP  TN ]
		}
		
		for(int i =1 ; i<= Cpredict.getN_size(); ++i) {	// data set iterative loop 
				
				String val1_pred, val2_test;
				val1_pred = Cpredict.getInstance(i).get(idC);
				val2_test = Ctest.getInstance(i).get(idC);
				Nc[Integer.parseInt(val2_test)]++;
				
				if (val1_pred.equals(val2_test)) {
					++correctC;
					
					confusionMatrix.get(Integer.parseInt(val1_pred))[2]++; //TP
					
					for(int j =0; j<Cpredict.getN_classes();++j) {
						if(j== Integer.parseInt(val2_test)) {		
							continue;
						}	  
						confusionMatrix.get(j)[3]++; //TN
					}
				}
				else {
						
						++incorrectC;
						confusionMatrix.get(Integer.parseInt(val1_pred))[1]++; //FP
						confusionMatrix.get(Integer.parseInt(val2_test))[0]++; //FN

				}
			}
	
			this.accValue = correctC/(incorrectC + correctC);
			CalcSpecificity();
			CalcSensitivity();
			CalcF1_score();
	}
	
	/**
	 * Calculates sensitivity for each class and the weighted average
	 */
	protected void CalcSensitivity() { 
				
		this.sensitivity= new double[confusionMatrix.size() + 1]; // +1 Is to add weighted avg value
		
		int i=0;
		for(Integer[] counts : confusionMatrix) { 	//[FN FP TP  TN ]
			
			sensitivity[i]= (double)counts[2]/(double)(counts[2] + counts[0]);
			i++;
		}
		// Calculate weighted avg
		double num = 0, den = 0;
		for(int k=0; k < confusionMatrix.size(); k++) {
			num += Nc[k]*sensitivity[k];  // numerator
			den += Nc[k]; 	// denominator
		}
		sensitivity[i] = (double)(num/den);
	}
	
	/**
	 * Calculates specificity for each class and the weighted average
	 */
	protected void CalcSpecificity() { 
		
		this.specificity= new double[confusionMatrix.size() + 1]; // +1 Is to add weighted avg value
		
		int i=0;
		for(Integer[] counts : confusionMatrix) {	//[FN FP TP  TN ]
			
			specificity[i]= (double)counts[3]/(double)(counts[3] + counts[1]);
			i++;
		}
		// Calculate weighted avg
		double num = 0, den = 0;
		for(int k=0; k < confusionMatrix.size(); k++) {
			num += Nc[k]*specificity[k];  // numerator
			den += Nc[k]; 	// denominator
		}
		specificity[i] = (double)(num/den);
	}
	
	/**
	 * Calculates F1score for each class and the weighted average
	 */
	protected void CalcF1_score() { 
		
		this.F1_score = new double[confusionMatrix.size() + 1]; // +1 Is to add weighted avg value
		
		int i=0;
		for(Integer[] counts : confusionMatrix) { 	//[FN FP TP  TN ]
			
			F1_score[i]= (double)(2 * counts[2])/(double)(2 * counts[2] + counts[1] + counts[0]);
			i++;
		}
		// Calculate weighted avg
		double num = 0, den = 0;
		for(int k=0; k < confusionMatrix.size(); k++) {
			num += Nc[k]*F1_score[k];  // numerator
			den += Nc[k]; 	// denominator
		}
		F1_score[i] = (double)(num/den);
		
	}
	
	/**
	 * Returns accuracy value
	 * @return double accuracy value
	 */
	public double  accuracy() {
		return this.accValue; 
	}
	
	/**
	 * Returns array of specificity values
	 * @return double[] specificity values for each class and weighted average
	 */
	public double[] specificity() {
		
		// (True Negative)/(True Negative + False Positive)
		return this.specificity;		
	}
	
	/**
	 * Returns array of sensitivity values
	 * @return double[] sensitivity values for each class and weighted average
	 */
	public double[] sensitivity() {
		
		// (True Positive)/(True Positive + False Negative)
		return this.sensitivity;		
	}
	
	/**
	 * Returns array of F1score values
	 * @return double[] F1score values for each class and weighted average
	 */
	public double[] F1_score() {
		
		// (2 * True Positive)/(2 * True Positive + False Positive + False Negative)
		return this.F1_score;		
	}
	
	/**
	 * Method to print metrics in the terminal according to the specified format
	 */
	public void print() {
		
		System.out.printf("Resume:\nAccuracy: %.2f %%\n", this.accuracy() * 100);
		System.out.println("\t\tSpecificity\tSensitivity\tF1score \tClass");
		int avgID = confusionMatrix.size();  // Position for the weighted avg
		for(int i=0; i < avgID; i++) { //[FN FP TP  TN ]			
			System.out.printf("\t\t%.2f %%\t", this.specificity()[i] * 100);
			System.out.printf("\t%.2f %%\t", this.sensitivity()[i] * 100);
			System.out.printf("\t%.2f %%\t", this.F1_score()[i] * 100);
			System.out.println("\t" + i);
		}
		System.out.print("Weighted Avg.");
		System.out.printf("\t%.2f %%", this.specificity()[avgID] * 100);
		System.out.printf("\t\t%.2f %%", this.sensitivity()[avgID] * 100);
		System.out.printf("\t\t%.2f %%", this.F1_score()[avgID] * 100);
	}	
	
}
