package lab05.factorizer;

public class TestFactorizerWithCache_bug {

	public static void main(String[] args) {

		int nUsers = 2;
		
		FactorizerService service = new FactorizerWithCache_bug(5);
		ServiceUser[] users = new ServiceUser[nUsers];
		for (int i = 0; i < nUsers; i++){
			users[i] = new ServiceUser(service,2000000);
		}

		long t0 = System.currentTimeMillis();
		for (int i = 0; i < nUsers; i++){
			users[i].start();
		}
		for (int i = 0; i < nUsers; i++){
			try {
				users[i].join();
			} catch (Exception ex){
				ex.printStackTrace();
			}
		}
		long t1 = System.currentTimeMillis();
		System.out.println("Total time: "+(t1-t0));
	}

}
