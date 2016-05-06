package lab05.cs;

public class TestCSWithRawSynchBlocks {

	public static void main(String[] args) {
		Object lock = new Object();
		new MyWorkerB("Eugenio",lock).start();
		new MyWorkerA("Alda",lock).start();		
	}

}
