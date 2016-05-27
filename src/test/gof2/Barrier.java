package test.gof2;

public class Barrier {

	private int n,tmp;
	
	public Barrier(int n){
		this.n=n;
		tmp=n;
	}
	
	public synchronized void dec(){
		n--;
		if(n==0){
			notify();
		}
	}
		
	
	public synchronized void go(){
		
		if(n!=0){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		n=tmp;
	}
	
}
