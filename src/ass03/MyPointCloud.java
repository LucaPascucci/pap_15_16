package ass03;

import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * Created by Luca on 02/04/16.
 */

/* STREAM = A sequence of elements supporting sequential and parallel aggregate operations.*/
public class MyPointCloud implements PointCloud{

    private List<P2d> pointList;

    public MyPointCloud(List<P2d> pointList){
        this.pointList = pointList;
    }

    @Override
    public void move(V2d v) {
        //Applicazione della funzione "sum(v)", attraverso la funzione "map", ad ogni elemento (punto - P2D) dello stream
        this.pointList = this.pointList.stream().map(p -> p.sum(v)).collect(toList());
    }

    @Override
    public List<P2d> getPointsInRegion(Region r) {
        //Filtro utilizzando il metodo ad ogni elemento (punto - P2D) dello stream
        return this.pointList.stream()
                .filter(p -> isBetween(r.getUpperLeft().getX(),p.getX(),r.getBottomRight().getX()) && isBetween(r.getUpperLeft().getY(),p.getY(),r.getBottomRight().getX()))
                .collect(toList());
    }

    @Override
    public Optional<P2d> nearestPoint(P2d p) {
        return this.pointList.stream().min(comparing(point -> P2d.distance(point,p)));
    }

    /**
     * Va sistemato considerando la possibilità di una lista di P2d vuota!! Quindi utilizzando Optional - risolta in "toStringOptional"
     */
    @Override
    public String toString(){
        return toStringOptional();
        /*String strPointList = this.pointList.stream()
                .map(P2d::toString)
                .reduce((a,b) -> a + ", " + b).get();
        return "{ " + strPointList + " }";*/
    }

    private String toStringOptional(){
        Optional<String> strPointList = this.pointList.stream()
                .map(P2d::toString)
                .reduce((a,b) -> a + ", " + b);
        //return strPointList.isPresent()? "{ " + strPointList.get() + " }" : "";
        return strPointList.map(s -> "{ " + s + " }").orElse("");

    }

    private static boolean isBetween(double min, double value, double max){
        return (min <= value && value <= max);
    }
}
