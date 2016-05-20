package lab06.monitors;

public class TestSynchCell {
		
	public static void main(String args[]){
		SynchCell cell = new SynchCell();
		new Getter(cell).start();
		new Getter(cell).start();
		new Getter(cell).start();
		new Setter(cell,303).start();
	}
}
