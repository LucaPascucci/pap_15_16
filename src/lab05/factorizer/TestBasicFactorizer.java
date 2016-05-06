package lab05.factorizer;

public class TestBasicFactorizer {

	public static void main(String[] args) {

		FactorizerService service = new StatelessFactorizer();
		for (int i = 0; i < 2; i++){
			new ServiceUser(service,1000000).start();
		}
		
	}
	
	/*
	private static void test(){
		FactorizerService service = new StatelessFactorizerService();
		int[] factors = service.getFactors(5060600);
		for (int x: factors){
			System.out.print(x+" ");
		}
		System.out.println();
	}
	*/

}
