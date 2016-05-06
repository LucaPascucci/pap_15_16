package lab05.check_act;

public class Counter {

	private int cont;
	private int min, max;
	
	public Counter(int min, int max){
		this.cont = this.min = min;
		this.max = max;
	}

	//con synchronized operazioni eseguite in sezione critica in mutua esclusione ed atomicamente (come se fossero un unica operazione non divisibile)
	public synchronized void inc(){
		this.cont++;
		assert(this.cont <= this.max);
	}

	public synchronized void dec(){
		this.cont--;
		assert(this.cont >= this.min);
	}
	
	public synchronized int getValue(){
		return this.cont;
	}
}
