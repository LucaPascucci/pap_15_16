package ass06.seeds.service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by Luca on 23/05/16.
 */
public class ComputeTask implements Callable<List<Point>> {

    private boolean[][] world;
    private int start, stop;
    private Dimension worldSize;

    public ComputeTask (boolean[][] world,int start, int stop, Dimension worldSize){
        this.world = world;
        this.start = start;
        this.stop = stop;
        this.worldSize = worldSize;
    }

    @Override
    public List<Point> call() throws Exception {
        List<Point> points = new ArrayList<>();
        boolean[][] neighbours = new boolean[3][3];

        for (int r = this.start; r < this.stop; r++){
            for (int c = 0; c < this.worldSize.width; c++){

                //mondo toroidale
                int prevRow = r == 0 ? this.worldSize.height - 1 : r - 1;
                int nextRow = (r + 1) % this.worldSize.height;
                int prevCol = c == 0 ? this.worldSize.width - 1 : c - 1;
                int nextCol = (c + 1) % this.worldSize.width;

                neighbours[0][0] = this.world[prevRow][prevCol];
                neighbours[0][1] = this.world[prevRow][c];
                neighbours[0][2] = this.world[prevRow][nextCol];
                neighbours[1][0] = this.world[r][prevCol];
                //neighbours[1][1] = this.world[r][c];
                neighbours[1][2] = this.world[r][nextCol];
                neighbours[2][0] = this.world[nextRow][prevCol];
                neighbours[2][1] = this.world[nextRow][c];
                neighbours[2][2] = this.world[nextRow][nextCol];

                if (computePoint(neighbours)){
                    points.add(new Point(c,r));
                }

            }
        }

        return points;
    }

    private boolean computePoint(boolean[][] neighbours){

        //conto il numero di vicini vivi
        int countNeighbours = 0;

        if (neighbours[0][0]){
            countNeighbours++;
        }

        if (neighbours[0][1]){
            countNeighbours++;
        }

        if (neighbours[0][2]){
            countNeighbours++;
        }

        if (neighbours[1][0]){
            countNeighbours++;
        }

        if (neighbours[1][2]){
            countNeighbours++;
        }

        if (neighbours[2][0]){
            countNeighbours++;
        }

        if (neighbours[2][1]){
            countNeighbours++;
        }

        if (neighbours[2][2]){
            countNeighbours++;
        }

        if (countNeighbours == 2){
            return true;
        }else{
            return false;
        }
    }
}
