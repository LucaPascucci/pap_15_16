package ass04.sol;

public class V2d  {

    private final float x,y;

    public V2d(float x,float y){
        this.x = x;
        this.y = y;
    }

    public V2d(double rad){
        this.x = (float) Math.sin(rad);
        this.y = (float) Math.cos(rad);
    }

    public V2d(P2d p1, P2d p0){
    	this.x = p1.getX() - p0.getX();
    	this.y = p1.getY() - p0.getY();
    }
    
    public V2d sum(V2d v){
        return new V2d(this.x+v.x,this.y+v.y);
    }

    public V2d mul(float k){
        return new V2d(k*this.x,k*this.y);
    }

    public float getX(){
    	return this.x;
    }
    
    public float getY(){
    	return this.y;
    }
    
    public float module(){
        return (float)Math.sqrt(this.x*this.x+this.y*this.y);
    }

    public String toString(){
        return "V2d("+this.x+","+this.y+")";
    }
    
	public boolean equals(Object obj){
		if (obj instanceof P2d){
			V2d v = (V2d) obj;
			return v.x == this.x && v.y == this.y;
		} else {
			return false;
		}
	}
	
	public int hashCode() {
		return toString().hashCode();
	}
}
