package lab05.lost_updates;

public class UnsafeCounter implements Counter{  //non è Thread-safe

	private int cont;
	
	public UnsafeCounter(int base){
		this.cont = base;
	}
	
	public void inc(){
		this.cont++;
	}
	
	public int getValue(){
		return this.cont;
	}
}
