package lab06.monitors;

public class TestSynchCell {
		
	public static void main(String args[]){
		SynchCell2 cell = new SynchCell2();
		new Getter(cell,0).start();
		new Getter(cell,1).start();
		new Getter(cell,2).start();
		new Setter(cell,303).start();
	}
}
