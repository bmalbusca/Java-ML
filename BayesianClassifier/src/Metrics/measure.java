package Metrics;
import FileIO.Dataset; 

public class  measure {

	/*
	 *  variables metrics
	 * 
	 */
	protected double accValue = 0; 
	


	public measure ( Dataset Cpredict,Dataset Ctest) {
		
		
		if(Cpredict.N_classes != Ctest.N_classes) {

			 System.err.println("Testfile and Predicted File are different!.  Must have the same number of classes");
		        System.exit(1);
		}
		
		int idC = Cpredict.getInstance(0).len()-1;
		double correctC=0, incorrectC=0; //for accuracy measurement

			
		
		
		for(int i =1 ; i<= Cpredict.N_size; ++i) {	// data set iterative loop 
			
				//System.out.println(Cpredict.getInstance(i).get(idC) + "VS" +Ctest.getInstance(i).get(idC) );
				String val1, val2;
				val1 = Cpredict.getInstance(i).get(idC);
				val2 = Ctest.getInstance(i).get(idC);
				if (val1.equals(val2)) {
					++correctC;
				}
				else {
					++incorrectC;
				}
				
				/*
				 *  do your math here
				 */
				
				
			}
	
			this.accValue = correctC/(incorrectC + correctC);
			

	}
	
	
	
	
	
	public double  accuracy() {
		
		System.out.printf("Accuracy: %.2f %%", this.accValue * 100);
		return this.accValue; 
	}
	
	
	public void F1_score() {
		
		
	}
	
	
	public void sensitivity() {
		
		
	}
	
	public void specificity() {
		
		
	}
	
	
	
}
