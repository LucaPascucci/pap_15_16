package ass05.sol.ex1;

public class P2d {

    private final double x,y;

    public P2d(double x,double y){
        this.x=x;
        this.y=y;
    }

    public double getX(){
    	return x;
    }
    
    public double getY(){
    	return y;
    }

    public String toString(){
        return "P2d("+x+","+y+")";
    }

	public boolean equals(Object obj){
		if (obj instanceof P2d){
			P2d p = (P2d) obj;
			return p.x == x && p.y == y;
		} else {
			return false;
		}
	}
	
	public static double computeDistance(P2d p1, P2d p2){
		double dx = p1.x - p2.x;
		double dy = p1.y - p2.y;
		return Math.sqrt(dx*dx+dy*dy);
	}
	
	public int hashCode() {
		return (toString()).hashCode();
	}
}
