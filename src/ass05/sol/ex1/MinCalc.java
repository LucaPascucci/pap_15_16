package ass05.sol.ex1;


import static ass05.sol.ex1.P2d.computeDistance;

public class MinCalc extends Thread {

	private P2d[] points;
	private int indexFrom, indexTo;
	private Min min;
	
	public MinCalc(P2d[] points, int indexFrom, int indexTo, Min min){
		this.points = points;
		this.indexFrom = indexFrom;
		this.indexTo = indexTo;
		this.min = min;
	}
	
	public void run(){
		P2d localMin = points[indexFrom];
		P2d centroid = min.getCentroid();
		
		double minDist = computeDistance(localMin,centroid);
		// log("from "+indexFrom+" to "+indexTo);
		for (int i = indexFrom+1; i < indexTo; i++){
			P2d p = points[i];
			double d = computeDistance(p,centroid);
			if (d < minDist){
				minDist = d;
				localMin = p;
			}
		}
		min.checkAndSet(localMin);
	}
	
	private void log(String s){
		System.out.println("[MIN-CALC] "+s);
	}
}
