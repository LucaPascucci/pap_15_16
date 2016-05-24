package ass06.seeds.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luca on 20/05/16.
 */
public class SeedsSet {

    private Dimension worldSize;
    private List<Point> seeds;
    private int era;

    public SeedsSet(Dimension worldSize){
        this.worldSize = worldSize;
        this.seeds = new ArrayList<>();
        //this.seeds = new ArrayList<>(this.worldSize.height * this.worldSize.width);
        this.emptyWorld();
    }

    public Dimension getWorldSize(){
        return this.worldSize;
    }

    public List<Point> getSeeds(){
        return this.seeds;
    }

    public int getEra(){
        return this.era;
    }

    public void emptyWorld(){
        this.seeds.clear();
        this.era = 0;
    }

    public void addSeed(int r, int c){
        this.seeds.add(new Point(c,r));
        //System.out.println("Aggiunto -> R: " + r + " C: " + c + " Size: " + this.seeds.size());
    }

    public void removeSeed(int r, int c){
        int size = this.seeds.size();
        for (int i = 0; i < this.seeds.size(); i++){
            Point curr = this.seeds.get(i);
            if (curr.getX() == c && curr.getY() == r){
                this.seeds.remove(i);
                break;
            }
        }
        //System.out.println("Size: " + size + " -> " + this.seeds.size());
    }

    public void incEra(){
        this.era++;
    }

    public void setNewSeeds(List<Point> newSeeds){
        this.seeds = newSeeds;
    }
}
