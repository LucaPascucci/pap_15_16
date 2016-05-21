package ass06.seeds.model;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

/**
 * Created by Luca on 20/05/16.
 */
public class SeedsSet {

    private ArrayList<Point> points = new ArrayList<>(0);
    private Dimension worldSize;

    public SeedsSet(Dimension worldSize){
        this.worldSize = worldSize;
    }

    public int getSizeX(){
        return this.worldSize.width;
    }

    public int getSizeY(){
        return this.worldSize.height;
    }

    public ArrayList<Point> getPoints() {
        return this.points;
    }

    public void setPoints(ArrayList<Point> points){
        this.points = points;
    }

    //funzione che deduce la sorte di un determinato punto
    public Point computePoint(boolean[][] neighbors, int i, int j){

        //conto il numero di vicini vivi
        int countNeighbors = 0;
        if (neighbors[0][0]){
            countNeighbors++;
        }
        if (neighbors[0][1]){
            countNeighbors++;
        }
        if (neighbors[0][2]){
            countNeighbors++;
        }
        if (neighbors[1][0]){
            countNeighbors++;
        }
        if (neighbors[1][2]){
            countNeighbors++;
        }
        if (neighbors[2][0]){
            countNeighbors++;
        }
        if (neighbors[2][1]){
            countNeighbors++;
        }
        if (neighbors[2][2]){
            countNeighbors++;
        }

        //se il numero di vicini vivi è due allora il punto è rimane vivo oppure nascerà
        if (countNeighbors == 2){
            return new Point(i - 1, j -1); //TODO capire perchè inidici diminuiti
        }

        return null;
    }
}
