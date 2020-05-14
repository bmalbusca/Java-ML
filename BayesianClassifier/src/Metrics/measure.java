package Metrics;

import FileIO.Dataset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import DataStructures.Edge;
import DataStructures.Node;

public class  measure {

	/*
	 *  variables metrics
	 * 
	 */
	protected ArrayList<Integer[]> confusionMatrix ;
	
	protected double accValue = 0; 
	protected double [] sensitivity;
	protected double [] specificity;
	
	
	/*
		"TP of C1" is all C1 instances that are classified as C1.
		"TN of C1" is all non-C1 instances that are not classified as C1. N_C1 - N
		"FP of C1" is all non-C1 instances that are classified as C1.
		"FN of C1" is all C1 instances that are not classified as C1.
		
							 C1	test Predicted	 
							 *  c1     c1  TP	
							 *  c1	   c2  FN		
							 *  c3     c1  FP
							 *  c4     c4  TN
							
		
		
		
	*/
	
	public measure ( Dataset Cpredict,Dataset Ctest) {
		
		
		if(Cpredict.N_classes != Ctest.N_classes) {

			 System.err.println("Testfile and Predicted File are different!.  Must have the same number of classes");
		        System.exit(1);
		}
		
		int idC = Cpredict.getInstance(0).len()-1;
		double correctC=0, incorrectC=0; //for accuracy measurement

		confusionMatrix = new ArrayList<Integer[]>(Cpredict.N_classes);
		for(int i=0; i<Cpredict.N_classes;++i ) {
			
			confusionMatrix.add(new Integer[] {0,0,0,0}); //[FN FP TP  TN ]
		}
		
		
		for(int i =1 ; i<= Cpredict.N_size; ++i) {	// data set iterative loop 
			
				
				String val1_pred, val2_test;
				val1_pred = Cpredict.getInstance(i).get(idC);
				val2_test = Ctest.getInstance(i).get(idC);
				
				if (val1_pred.equals(val2_test)) {
					++correctC;
					
					confusionMatrix.get(Integer.parseInt(val1_pred))[2]++; //TP
					
					for(int j =0; j<Cpredict.N_classes;++j) {
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
			CalcSensitivitySpecificty();
	}
	
	
	
	
	
	
	public double  accuracy() {
		
		System.out.printf("accuracy: %.2f %%", this.accValue * 100);
		return this.accValue; 
	}
	
 
	
	public void F1_score() {
		
		
	}
	
	
	protected void CalcSensitivitySpecificty() { 
		
		
		this.sensitivity= new double[confusionMatrix.size()];
		this.specificity= new double[confusionMatrix.size()];
		
		int i=0; 
		
		for(Integer[] counts : confusionMatrix) {//[FN FP TP  TN ]
			
			sensitivity[i]= (double)counts[2]/(double)(counts[2] + counts[0]);
			specificity[i]= (double)counts[3]/(double)(counts[3] + counts[1]);
				i++;
		}
		
	
	}
	
	public double[] specificity() {
		
		// (True Negative)/(True Negative + False Positive)
		return this.specificity;
		
	}
	
	public double[] sensitivity() {
		
		//(True Positive)/(True Positive + False Negative)
		return this.sensitivity;
		
	}
	
	
	
}
