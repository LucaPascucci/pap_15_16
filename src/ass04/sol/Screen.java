package ass04.sol;

public class Screen {

	private int minX, minY, maxX, maxY;

	public Screen(int minX, int minY, int maxX, int maxY) {
		super();
		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
	}
	
	public int getMinX(){
		return this.minX;
	}
	public int getMinY(){
		return this.minY;
	}
	public int getMaxX(){
		return this.maxX;
	}
	public int getMaxY(){
		return this.maxY;
	}
}
