package ass05;

/**
 * Created by Luca on 06/05/16.
 */
public class V2d {

    private final double x,y;

    public V2d(P2d p1, P2d p0){
        this.x = p1.getX() - p0.getX();
        this.y = p1.getY() - p0.getY();
    }

    public double module(){
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }
}
