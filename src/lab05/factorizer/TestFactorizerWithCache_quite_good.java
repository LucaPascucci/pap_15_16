package lab05.factorizer;

import java.util.ArrayList;
import java.util.List;

public class TestFactorizerWithCache_quite_good {

	public static void main(String[] args) {

		int nUsers = 2;
		
		FactorizerService service = new FactorizerWithCache_quite_good(5);

		List<Thread> users = new ArrayList<Thread>();
		for (int i = 0; i < nUsers; i++){
			users.add(new ServiceUser(service,2000000));
		}

		long t0 = System.currentTimeMillis();
		for (Thread user: users){
			user.start();
		}
		for (Thread user: users){
			try {
				user.join();
			} catch (Exception ex){
				ex.printStackTrace();
			}
		}
		long t1 = System.currentTimeMillis();
		System.out.println("Total time: "+(t1-t0));

	}

}
