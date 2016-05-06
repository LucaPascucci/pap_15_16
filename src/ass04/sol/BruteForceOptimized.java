package ass04.sol;
import ass04.SecureSystem;

public class BruteForceOptimized {
	public static void main(String[] args) {		
		int nDigits = 5; // Integer.parseInt(args[0]);
		SecureSystem sys = new SecureSystem(nDigits);		
		
		int nCores = Runtime.getRuntime().availableProcessors();	
		long startTime = System.currentTimeMillis();
		long tot = (long) Math.pow(96, nDigits);
		long delta = tot/nCores;
		long base = 0;
		long limit = base + delta;
		for (int i = 0; i < nCores - 1; i++){
			new AttackerOptimized(i,base,limit,nDigits,sys,startTime).start();
			base = limit;
			limit += delta;
		}
		new AttackerOptimized(nCores-1,base,tot,nDigits,sys,startTime).start();
	}

}
