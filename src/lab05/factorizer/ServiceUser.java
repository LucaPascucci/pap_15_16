package lab05.factorizer;

public class ServiceUser extends Thread {
	
	private FactorizerService service;
	private long[] valuesToFactorize = { 100, 33, 78, 46, 10000, 13450 };
	private int ntimes;
	
	// to verify correctness 
	private FactorizerService localService;
	
	public ServiceUser(FactorizerService service, int ntimes){
		this.service = service;
		localService = new StatelessFactorizer();
		this.ntimes = ntimes;
	}

	public ServiceUser(FactorizerService service, int ntimes, long[] values){
		this(service,ntimes);
		this.valuesToFactorize = values;		
	}
	
	public void run(){
		log("start.");
		java.util.Random rand = new java.util.Random(System.currentTimeMillis());
		int i = 0;
		int nErrors = 0;
		while (i < ntimes){
			int index = rand.nextInt(valuesToFactorize.length);
			int[] factors = service.getFactors(valuesToFactorize[index]);
			// logFactors(valuesToFactorize[index],factors);
			
			// get factors from a local, not shared service
			if (!sameContent(factors,localService.getFactors(valuesToFactorize[index]))){
				//log("Error found: ");
				//logFactors(valuesToFactorize[index],factors);
				nErrors++;
			}
			i++;
		}
		log("done: "+nErrors+" errors occurred.");
	}

	private void logFactors(long n, int[] factors){
		System.out.print("  factor "+n+": ");
		for (int x: factors){
			System.out.print(x+" ");
		}
		System.out.println();
	}
	
	private boolean sameContent(int[] v1, int[] v2){
		if (v1.length != v2.length){
			return false;
		} else {
			for (int i = 0; i < v1.length; i++){
				if (v1[i] != v2[i]){
					return false;
				}
			}
		}
		return true;
	}

	protected void log(String msg){
		System.out.println("["+getName()+"] "+msg);
	}
}
