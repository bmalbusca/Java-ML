package DataStructures;
import java.util.Comparator;


public class edgeCompare implements Comparator<edge> {

			//@Override
		   public int compare(edge e1, edge e2) {
				
				return Double.compare(e2.weight(),e1.weight());
		   }
	
	
}
