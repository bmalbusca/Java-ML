package Metrics;
import FileIO.Dataset; 

public class  measure {

	/*
	 *  variables metrics
	 * 
	 */
	protected long accValue = 0; 
	


	public measure (Dataset Ctest, Dataset Cpredict) {
		
		
		if(Cpredict.N_classes != Ctest.N_classes) {

			 System.err.println("Testfile and Predicted File are different!.  Must have the same number of classes");
		        System.exit(1);
		}
		
		int idC = Cpredict.N_classes;
		int correctC=0, incorrectC=0; //for accuracy measurement

			
		
		
		for(int i =1 ; i< Cpredict.N_size; ++i) {	// data set iterative loop 
			
				System.out.println(Cpredict.getInstance(i).get(idC) + "VS" +Ctest.getInstance(i).get(idC) );
				if (Cpredict.getInstance(i).get(idC).equals(Ctest.getInstance(i).get(idC))) {
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
	
	
	
	
	
	public long  accuracy() {
		
		System.out.println("accuracy: " + accValue);
		return accValue; 
	}
	
	
	public void F1_score() {
		
		
	}
	
	
	public void sensitivity() {
		
		
	}
	
	public void specificity() {
		
		
	}
	
	
	
}
