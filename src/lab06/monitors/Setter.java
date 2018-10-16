package lab06.monitors;

public class Setter extends Worker {
	
	private ISynchCell cell;
	private int value;
	
	public Setter(ISynchCell cell, int value){
		super("setter");
		this.cell = cell;
		this.value = value;
	}
	
	public void run(){
		wasteRandomTime(2000,5000);
		log("before setting");
		cell.set(value);
		log("after setting");
	}
}
