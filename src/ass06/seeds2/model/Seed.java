package ass06.seeds2.model;

import java.util.stream.Collector;

/**
 * Created by Luca on 21/05/16.
 */
public class Seed{

    private boolean alive = false;
    private boolean willAlive;
    private int row;
    private int col;

    public Seed (int row, int col){
        this.row = row;
        this.col = col;
        this.alive = false;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public boolean getAlive(){
        return this.alive;
    }

    public void setAlive(boolean value){
        this.alive = value;
    }

    public void setWillAlive (boolean value){
        this.willAlive = value;
    }

    public void applyChange(){
        this.alive = this.willAlive;

    }
}
