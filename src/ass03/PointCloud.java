package ass03;

import java.util.List;
import java.util.Optional;

public interface PointCloud {

	/**
	 * Translate the point cloud of the specified vector.
	 * 
	 * @param v
	 */
	void move(V2d v);
	
	/**
	 * Gets the points that fall inside the specified region
	 * 
	 * @param r
	 * @return
	 */
	List<P2d> getPointsInRegion(Region r);
	
	/**
	 * Gets the point (if exists) nearest to the one specified 
	 * @param p
	 * @return
	 */
	Optional<P2d> nearestPoint(P2d p);
		
	
	/**
	 * Gets a textual representation in the format: [ p1, p2, .. ]
	 */
	String toString();
}