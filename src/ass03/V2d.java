package ass03;

public class V2d  {

    private final double x,y;

    public V2d(double x,double y){
        this.x = x;
        this.y = y;
    }

    public V2d(P2d p1, P2d p0){
    	x = p1.getX() - p0.getX();
    	y = p1.getY() - p0.getY();
    }
    
    public V2d sum(V2d v){
        return new V2d(x+v.x,y+v.y);
    }

    public double getX(){
    	return x;
    }
    
    public double getY(){
    	return y;
    }
    
    public double module(){
        return Math.sqrt(x*x+y*y);
    }

    public String toString(){
        return "V2d("+x+","+y+")";
    }
    
	public boolean equals(Object obj){
		if (obj instanceof P2d){
			V2d v = (V2d) obj;
			return v.x == x && v.y == y;
		} else {
			return false;
		}
	}
	
	public int hashCode() {
		return toString().hashCode();
	}
}