package ass05.sol.ex1;

import java.util.Optional;

public class Min {
	private P2d centroid;
	private Optional<P2d> currMin = Optional.empty();
	private double dist = Double.MAX_VALUE;
	
	public Min(P2d centroid){
		this.centroid = centroid;
	}
	
	public void checkAndSet(P2d newMin){
		double d = P2d.computeDistance(centroid,newMin);
		synchronized(this){
			if (d < dist){
				dist = d;
				currMin = Optional.of(newMin);
			}
		}
	}
	
	public P2d getCentroid(){
		return centroid;
	}
	
	public Optional<P2d> getMin(){
		return currMin;
	}

}
