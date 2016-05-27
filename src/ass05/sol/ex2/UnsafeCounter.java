package ass05.sol.ex2;

public class UnsafeCounter {

	private int cont;
	
	public UnsafeCounter(){
		this.cont = 0;
	}
	
	public void inc(){
		cont++;
	}

	public int getValue(){
		return cont;
	}
}
