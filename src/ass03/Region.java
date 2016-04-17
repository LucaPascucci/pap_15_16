package ass03;

public class Region {
	
	private P2d upperLeft,bottomRight;
	
	public Region(P2d upperLeft, P2d bottomRight){
		this.upperLeft = upperLeft; 
		this.bottomRight = bottomRight;
	}
	
	public P2d getUpperLeft(){
		return this.upperLeft;
	}
	
	public P2d getBottomRight(){
		return this.bottomRight;
	}

}