package ass03;

import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * Created by Luca on 02/04/16.
 */
public class MyPointCloud implements PointCloud{

    private List<P2d> pointList;

    public MyPointCloud(List<P2d> pointList){
        this.pointList = pointList;
    }

    @Override
    public void move(V2d v) {
        this.pointList = this.pointList.stream().map(p -> p.sum(v)).collect(toList());
    }

    @Override
    public List<P2d> getPointsInRegion(Region r) {

        return this.pointList.stream()
                .filter(p -> isBetween(r.getUpperLeft().getX(),p.getX(),r.getBottomRight().getX()) && isBetween(r.getUpperLeft().getY(),p.getY(),r.getBottomRight().getX()))
                .collect(toList());
    }

    @Override
    public Optional<P2d> nearestPoint(P2d p) {
        return this.pointList.stream().min(comparing(point -> P2d.distance(point,p)));
    }

    @Override
    public String toString(){

        String strPointList = this.pointList.stream()
                .map(P2d::toString)
                .reduce((a,b) -> a + ", " + b).get();
        return "{ " + strPointList + " }";
    }

    private static boolean isBetween(double min, double value, double max){
        return (min <= value && value <= max);
    }
}
