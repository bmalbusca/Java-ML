package dataStructures;
import java.util.Comparator;


/**
 * 	This class implements Comparator to make possible the comparison of two object of type edge by their weight value
 * 	Is used for sorting edge type elements 
 * @author Group 20
 *
 */
public class EdgeCompare implements Comparator<Edge> {

		  
		/**
		 * User method to compare two object of type edge by their weight. The comparison is made with two double variables 
		 * @return int positive values means e1 is greater than e2 and the reverse means e2 greater than e1 
		 */
		public int compare(Edge e1, Edge e2) {	//@Override
				
				return Double.compare(e2.weight(),e1.weight());
		   }
	
	
}
