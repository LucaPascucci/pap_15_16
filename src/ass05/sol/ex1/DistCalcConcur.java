package ass05.sol.ex1;

import java.util.ArrayList;
import java.util.List;

/**
 * Calc Min Dist - Concurrent version
 * 
 * @author aricci
 *
 */
public class DistCalcConcur extends DistCalc {
	
	public P2d getMinDistFromCentroid(P2d[] points) {
		int nWorkers = Runtime.getRuntime().availableProcessors()+1;
		int delta = points.length / nWorkers;
		int rem = points.length % nWorkers;
		
		/* compute the centroid */
		
		Sum sum = new Sum();
		
		int indexFrom = 0;
		int indexTo;
		List<Thread> list = new ArrayList<Thread>();
		for (int i = 0; i < nWorkers; i++){
			indexTo = indexFrom + delta;
			if (rem > 0){
				indexTo++;
				rem--;
			}
			Thread t = new CentrCalc(points,indexFrom,indexTo,sum);
			t.start();
			list.add(t);
			indexFrom = indexTo;
		}
		for (Thread t: list){
			try {
				t.join();
			} catch (Exception ex){
				ex.printStackTrace();
			}
		}
		
		P2d centroid = new P2d(sum.getX()/points.length, sum.getY()/points.length);
		// System.out.println("Centroid: "+centroid);
		
		/* compute the min point */
		Min min = new Min(centroid);
		list.clear();
		rem = points.length % nWorkers;
		indexFrom = 0;
		for (int i = 0; i < nWorkers; i++){
			indexTo = indexFrom + delta;
			if (rem > 0){
				indexTo++;
				rem--;
			}
			Thread t = new MinCalc(points,indexFrom,indexTo,min);
			t.start();
			list.add(t);
			indexFrom = indexTo;
		}
		for (Thread t: list){
			try {
				t.join();
			} catch (Exception ex){
				ex.printStackTrace();
			}
		}
		return min.getMin().get();
	}


}
