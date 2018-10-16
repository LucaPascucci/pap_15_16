package lab06.monitors;

public class Getter extends Worker {
	
	private ISynchCell cell;
	
	public Getter(ISynchCell cell,int id){
		super("getter-" + id);
		this.cell = cell;
	}
	
	public void run(){
		wasteRandomTime(2000,5000);
		log("before getting");
		int value = cell.get();
		log("got value:"+value);
	}
}
