package ass05.sol.ex1;

public class Chrono {
	private long t;
	
	public void reset(){
		t = System.currentTimeMillis();
	}

	public long getTimeElapsed(){
		return System.currentTimeMillis() - t;
	}
}
