package ass05.MinDistance;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;

/**
 * Created by Luca on 07/05/16.
 */
public class Sequential {

    public static void main(String args[]){

        long startTotalTime = System.currentTimeMillis();

        int n_points;
        if (args.length == 1) {
            n_points = Integer.parseInt(args[0]);
        }else {
            n_points = 10000000;
        }

        P2d centroid = new P2d(0.0,0.0);

        //creazione punti
        List<P2d> points = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < n_points; i++){
            P2d curr = new P2d();
            centroid.sum(curr);
            points.add(curr);
        }
        System.out.println("Created FOR " + points.size() + " points in millis: " + (System.currentTimeMillis() - startTime) + '\n');

        //Definizione del baricentro
        centroid.setX(centroid.getX() / (double) n_points);
        centroid.setY(centroid.getY() / (double) n_points);
        System.out.println("Centroid Pos: " + centroid.toString() + '\n');

        //ricerca del punto più vicino al baricentro
        startTime = System.currentTimeMillis();
        Optional<P2d> result = points.stream().min(comparing(p -> P2d.distance(p,centroid)));
        if (result.isPresent()){
            P2d closer_point = result.get();
            System.out.println("Found closer Point: " + closer_point.toString() + " in " + (System.currentTimeMillis() - startTime) + " Millis");
            System.out.println("Distance from centroid = " + P2d.distance(closer_point,centroid));
        }else{
            System.out.println("Didn't found any point!");
        }

        System.out.println("\nExecution time: " + (System.currentTimeMillis() - startTotalTime) + " millis");
    }
}
