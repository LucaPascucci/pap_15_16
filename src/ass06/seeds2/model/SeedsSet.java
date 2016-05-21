package ass06.seeds2.model;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luca on 20/05/16.
 */
public class SeedsSet {

    private Seed[][] world;
    private Dimension worldSize;
    private List<Seed> seeds;

    public SeedsSet(Dimension worldSize){
        this.worldSize = worldSize;
        this.seeds = new ArrayList<>(this.worldSize.height * this.worldSize.width);
        this.world = new Seed[this.worldSize.height][this.worldSize.width];
        this.emptyWorld();
    }

    public int getSizeX(){
        return this.worldSize.width;
    }

    public int getSizeY(){
        return this.worldSize.height;
    }

    //funzione che deduce la sorte di un determinato punto
    public void computePoint(int r, int c){

        //conto il numero di vicini vivi
        int countNeighbors = 0;

        //TODO controllare i vicini magari anche collegando il mondo (sfera)

        if (countNeighbors == 2){
            this.world[r][c].setWillAlive(true);
        }else{
            this.world[r][c].setWillAlive(false);
        }
    }

    public void emptyWorld(){
        this.seeds.clear();
    }

    public void addSeed(int r, int c){
        this.seeds.add(new Seed(r,c));
        System.out.println("Aggiunto -> R: " + r + " C: " + c + " Size: " + this.seeds.size());
    }

    public void removeSeed(int r, int c){
        int size = this.seeds.size();
        System.out.println("Size: " + this.seeds.size());
        for (int i = 0; i < this.seeds.size(); i++){
            Seed curr = this.seeds.get(i);
            if (curr.getRow() == r && curr.getCol() == c){
                this.seeds.remove(i);
            }
        }
        System.out.println("Size: " + size + " -> " + this.seeds.size());
    }
}
