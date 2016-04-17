package ass03.sol;

import ass03.P2d;
import ass03.PointCloud;
import ass03.Region;
import ass03.V2d;

import java.util.List;
import java.util.Optional;

public class PointCloudTest {
	public static void main(String[] args) {
		PointCloud cloud = new MyPointCloud(
				new P2d(0,1),
				new P2d(0.5,1),
				new P2d(1,1),
				new P2d(1,0.5),
				new P2d(1,0),
				new P2d(1,-0.5),
				new P2d(1,-1),
				new P2d(0.5,-1),
				new P2d(0,-1),
				new P2d(-0.5,-1),
				new P2d(-1,-1),
				new P2d(-1,-0.5),
				new P2d(-1,0),
				new P2d(-1,0.5),
				new P2d(-1,1),
				new P2d(-0.5,1));
		
		/* testing toString */
		System.out.println(cloud.toString());
		
		/* testing getPointsInRegion */
		List<P2d> list = cloud.getPointsInRegion(new Region(new P2d(-2,2),new P2d(2,0.5)));
		System.out.println(MyPointCloud.toString(list));

		/* testing move */
		cloud.move(new V2d(2,0));
		System.out.println(cloud.toString());

		/* testing nearest */
		Optional<P2d> p = cloud.nearestPoint(new P2d(0,0));
		p.ifPresent(System.out::println);
	}
}
