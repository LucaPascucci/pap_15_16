package ass05.sol.ex1;


import static ass05.sol.ex1.P2d.computeDistance;

/**
 * Calc Min Dist - Sequential version
 * 
 * @author aricci
 *
 */
public class DistCalcSeq extends DistCalc {
	
	public P2d getMinDistFromCentroid(P2d[] points) {		
		double cx = points[0].getX();
		double cy = points[0].getY();
		for (int i = 1; i < points.length; i++){
			P2d p = points[i];
			cx = cx + p.getX();
			cy = cy + p.getY();
		}
		P2d centroid = new P2d(cx/points.length, cy/points.length);
		P2d localMin = points[0];
		
		double minDist = computeDistance(localMin,centroid);
		for (int i = 1; i < points.length; i++){
			P2d p = points[i];
			double d = computeDistance(p,centroid);
			if (d < minDist){
				minDist = d;
				localMin = p;
			}
		}
		return localMin;
	}
}
