package lab05.factorizer;

public class TestFactorizerWithCache_unsafe {

	public static void main(String[] args) {

		int nUsers = 2;
		FactorizerService service = new FactorizerWithCache_unsafe(5);
		for (int i = 0; i < nUsers; i++){
			new ServiceUser(service,1000000).start();
		}
	}

}
