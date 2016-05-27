package ass05.sol.ex1;

public class CentrCalc extends Thread {

	private P2d[] points;
	private int indexFrom, indexTo;
	private Sum sum;
	
	public CentrCalc(P2d[] points, int indexFrom, int indexTo, Sum sum){
		this.points = points;
		this.indexFrom = indexFrom;
		this.indexTo = indexTo;
		this.sum = sum;
	}
	
	public void run(){
		double cx = points[indexFrom].getX();
		double cy = points[indexFrom].getY();
		// log("from "+indexFrom+" to "+indexTo);
		for (int i = indexFrom+1; i < indexTo; i++){
			P2d p = points[i];
			cx = cx + p.getX();
			cy = cy + p.getY();
		}
		sum.add(cx, cy);
	}
	
	private void log(String s){
		System.out.println("[CENTR-CALC] "+s);
	}
	
}
