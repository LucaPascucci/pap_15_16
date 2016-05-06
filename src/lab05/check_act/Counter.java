package lab05.check_act;

public class Counter {

	private int cont;
	private int min, max;
	
	public Counter(int min, int max){
		this.cont = this.min = min;
		this.max = max;
	}
	
	public synchronized void inc(){
		cont++;
		assert(cont <= max);
	}

	public synchronized void dec(){
		cont--;
		assert(cont >= min);
	}
	
	public synchronized int getValue(){
		return cont;
	}
}
