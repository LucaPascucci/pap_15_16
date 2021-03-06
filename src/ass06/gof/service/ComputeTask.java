package ass06.gof.service;

import java.awt.Point;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by Luca on 23/05/16.
 */

/* The Callable} interface is similar to Runnable, in that both are designed for classes whose
 * instances are potentially executed by another thread.A Runnable , however, does not return a result and cannot
 * throw a checked exception.
 */
public class ComputeTask implements Callable<List<Point>> {

    private boolean[][] world;
    private int start, stop;
    private Dimension worldSize;
    private boolean rules;
    private boolean[][] neighbours;

    public ComputeTask (boolean[][] world, int start, int stop, Dimension worldSize, boolean rules){
        this.world = world;
        this.start = start;
        this.stop = stop;
        this.worldSize = worldSize;
        this.rules = rules;
        this.neighbours = new boolean[3][3];
    }

    //Lavora per righe, per ogni punto crea la matrice di punti vicini (mondo toroidale) e se passa i requisiti di sopravvivenza viene aggiunto il nuovo punto alla lista
    @Override
    public List<Point> call() {
        List<Point> points = new ArrayList<>();

        //cicla per righe
        for (int r = this.start; r < this.stop; r++){
            for (int c = 0; c < this.worldSize.width; c++){

                //mondo toroidale (quindi per collegare i bordi esterni del mondo)
                int prevRow = r == 0 ? this.worldSize.height - 1 : r - 1;
                int nextRow = (r + 1) % this.worldSize.height;
                int prevCol = c == 0 ? this.worldSize.width - 1 : c - 1;
                int nextCol = (c + 1) % this.worldSize.width;

                //creo la matrice di vicini del punto(r,c) corrente
                this.neighbours[0][0] = this.world[prevRow][prevCol];
                this.neighbours[0][1] = this.world[prevRow][c];
                this.neighbours[0][2] = this.world[prevRow][nextCol];
                this.neighbours[1][0] = this.world[r][prevCol];
                this.neighbours[1][1] = this.world[r][c];
                this.neighbours[1][2] = this.world[r][nextCol];
                this.neighbours[2][0] = this.world[nextRow][prevCol];
                this.neighbours[2][1] = this.world[nextRow][c];
                this.neighbours[2][2] = this.world[nextRow][nextCol];

                if (computePoint()){
                    points.add(new Point(c,r));
                }
            }
        }

        return points;
    }

    //metodo che conta i vicini di un punto ed in base al set di resgole decreta se il punto vivr?? o morir??
    private boolean computePoint(){

        //conto il numero di vicini vivi
        int countNeighbours = 0;

        if (this.neighbours[0][0]){
            countNeighbours++;
        }

        if (this.neighbours[0][1]){
            countNeighbours++;
        }

        if (this.neighbours[0][2]){
            countNeighbours++;
        }

        if (this.neighbours[1][0]){
            countNeighbours++;
        }

        if (this.neighbours[1][2]){
            countNeighbours++;
        }

        if (this.neighbours[2][0]){
            countNeighbours++;
        }

        if (this.neighbours[2][1]){
            countNeighbours++;
        }

        if (this.neighbours[2][2]){
            countNeighbours++;
        }

        if (this.rules){
            //Conway Rules
            if (this.neighbours[1][1]){
                return countNeighbours == 2 || countNeighbours == 3;
            }else{
                return countNeighbours == 3;
            }
        }else{
            //Ricci rules
            return countNeighbours == 2;
        }
    }
}
