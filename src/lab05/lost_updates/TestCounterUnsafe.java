package lab05.lost_updates;

public class TestCounterUnsafe { //caso di corse critiche

	public static void main(String[] args) throws Exception {
		int ntimes = 1000; //Integer.parseInt(args[0]);
		UnsafeCounter c = new UnsafeCounter(0);
		Worker w1 = new Worker(c,ntimes);
		Worker w2 = new Worker(c,ntimes);
		w1.start();
		w2.start();
		w1.join();
		w2.join();
		System.out.println("Counter final value: " + c.getValue());
		//a volte il risultato non è corretto perchè si crea corsa critica e si può perdere un valore
	}
}
