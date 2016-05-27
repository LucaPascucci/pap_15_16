package ass05.sol.ex1;

public class Sum {
	private double cx;
	private double cy;
	
	public Sum(){
		cx = 0;
		cy = 0;
	}
	
	public void add(double x, double y){
		synchronized(this){
			cx += x;
			cy += y;
		}
	}
	
	public double getX(){
		return cx;
	}

	public double getY(){
		return cy;
	}
}
