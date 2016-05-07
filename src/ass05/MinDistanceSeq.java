package ass05;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.Comparator.comparing;

/**
 * Created by Luca on 07/05/16.
 */
public class MinDistanceSeq {

    public static void main(String args[]){

        long startTotalTime = System.currentTimeMillis();

        int n_points;
        if (args.length == 1) {
            n_points = Integer.parseInt(args[0]);
        }else {
            n_points = 10000000;
        }

        P2d total = new P2d(0.0,0.0);

        //creazione punti
        List<P2d> points = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        IntStream.range(0,n_points).forEach(i -> {
            P2d curr = new P2d();
            total.sum(curr);  //il metodo sum -> synchronized
            points.add(curr);
        });
        System.out.println("Created points in millis: " + (System.currentTimeMillis() - startTime) + " - list size: "  + points.size() + '\n');

        //Definizione del baricentro
        P2d centroid = new P2d(total.getX() / n_points,total.getY() / n_points);
        System.out.println("Centroid Pos: x = " + centroid.getX() + " y = " + centroid.getY() + '\n');

        startTime = System.currentTimeMillis();
        Optional<P2d> result = points.stream().min(comparing(p -> P2d.distance(p,centroid)));
        if (result.isPresent()){
            P2d closer_point = result.get();
            System.out.println("Found closer Point: x = " + closer_point.getX() + " y = " + closer_point.getY() + " in " + (System.currentTimeMillis() - startTime) + " Millis");
            System.out.println("Distance from centroid = " + P2d.distance(closer_point,centroid));
        }else{
            System.out.println("Didn't found any point!");
        }

        System.out.println("\nExecution time: " + (System.currentTimeMillis() - startTotalTime) + " millis");
    }
}
