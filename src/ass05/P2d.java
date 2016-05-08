package ass05;

import java.util.Random;

/**
 * Created by Luca on 06/05/16.
 */
public class P2d {

    private static final double MAX = 20.0;
    private static final double MIN = 0.0;

    private double x,y;

    public P2d (){
        Random r = new Random();
        this.x = MIN + (MAX - MIN) * r.nextDouble();
        this.y = MIN + (MAX - MIN) * r.nextDouble();
    }

    public P2d (double x, double y){
        this.x = x;
        this.y = y;
    }

    public synchronized void sum (P2d p){
        this.x += p.getX();
        this.y += p.getY();
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public static double distance(P2d p0, P2d p1){
        return new V2d(p0,p1).module();
    }
}
