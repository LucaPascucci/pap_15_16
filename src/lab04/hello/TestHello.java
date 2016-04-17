package lab04.hello;


public class TestHello {

	public static void main(String[] args) throws Exception {
		
		MyThread myThread = new MyThread("Pippo");
		myThread.start();
		//myThread.run(); //con run il main thread passa il controllo a myThread ed aspetta la sua conclusione
		
		String myName = Thread.currentThread().getName();
		//Thread.currentThread().sleep(2000);
		System.out.println("Thread spawned - I'm " + myName);
		
	}
}
