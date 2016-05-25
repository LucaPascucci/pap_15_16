package ass06.gof.model;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luca on 20/05/16.
 */
public class SeedsSet {

    private Dimension worldSize;
    private List<Point> seeds;
    private int era;
    private boolean rules;

    public SeedsSet(Dimension worldSize){
        this.worldSize = worldSize;
        this.seeds = new ArrayList<>();
        this.emptyWorld();
        this.rules = false;
    }

    public boolean getRules(){
        return this.rules;
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
    }

    public void removeSeed(int r, int c){
        for (int i = 0; i < this.seeds.size(); i++){
            Point curr = this.seeds.get(i);
            if (curr.getX() == c && curr.getY() == r){
                this.seeds.remove(i);
                break;
            }
        }
    }

    public void incEra(){
        this.era++;
    }

    public void changeRules(){
        this.rules = !this.rules;
    }

    public void setNewSeeds(List<Point> newSeeds){
        this.seeds = newSeeds;
    }
}
