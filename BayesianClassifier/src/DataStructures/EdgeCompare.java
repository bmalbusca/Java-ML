package DataStructures;
import java.util.Comparator;


public class EdgeCompare implements Comparator<Edge> {

			//@Override
		   public int compare(Edge e1, Edge e2) {
				
				return Double.compare(e2.weight(),e1.weight());
		   }
	
	
}
