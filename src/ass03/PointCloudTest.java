package ass03;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by Luca on 02/04/16.
 */
public class PointCloudTest {

    public static void main(String[] args) {

        P2d point_0 = new P2d(10.0,10.0);
        P2d point_1 = new P2d(8.9,8.9);
        P2d point_2 = new P2d(7.0,3.0);
        P2d point_3 = new P2d(9.0,1.0);
        P2d point_4 = new P2d(4.0,8.0);
        P2d point_5 = new P2d(2.0,2.0);
        List<P2d> pointList = Arrays.asList(point_0,point_1,point_2,point_3,point_4,point_5);
        V2d vector = new V2d(1.0, 1.0);
        Region region = new Region(new P2d(5.0,5.0),new P2d(9.0,9.0));

        MyPointCloud pointCloud = new MyPointCloud(pointList);
        System.out.println("Points: " + pointCloud.toString());

        pointCloud.move(vector);
        System.out.println("Points after move: " + pointCloud.toString());

        System.out.println("\nPoints in region: ");
        List<P2d> pointInRegion = pointCloud.getPointsInRegion(region);
        pointInRegion.stream().forEach(p -> System.out.println(p.toString()));

        Optional<P2d> nearest_point = pointCloud.nearestPoint(point_2);
        System.out.println("\nNearest Point: " + (nearest_point.isPresent()? nearest_point.get().toString() : "Nothing"));

    }
}
