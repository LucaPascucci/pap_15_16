package ass05.sol.ex1;

import java.util.Arrays;
import java.util.Optional;

/**
 * Calc Min Dist - Stream par version
 * 
 * @author aricci
 *
 */

public class DistCalcStreamPar extends DistCalc {
	
	public P2d getMinDistFromCentroid(P2d[] points) {		
		P2d result = Arrays.stream(points).parallel()
			.reduce(new P2d(0,0), (p1,p2) -> {
				return new P2d(p1.getX()+p2.getX(), p1.getY()+p2.getY());
			});
		P2d centroid = new P2d(result.getX()/points.length, result.getY()/points.length);
		Optional<DistInfo> info = Arrays.stream(points)
				.map((P2d p) -> {
					return new DistInfo(p,P2d.computeDistance(p, centroid));
				})
				.reduce((i1,i2) -> i1.getDist() < i2.getDist() ? i1 : i2);

		return info.get().getPoint();
	}

	class DistInfo {
		private P2d p;
		private double dist;
		
		DistInfo(P2d p, double dist){
			this.p = p;
			this.dist = dist;
		}
		
		public P2d getPoint(){
			return p;
		}
		
		public double getDist(){
			return dist;
		}
	}
}
