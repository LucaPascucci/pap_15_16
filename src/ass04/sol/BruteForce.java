package ass04.sol;
import ass04.SecureSystem;

public class BruteForce {
	public static void main(String[] args) {		
		int nDigits = 3; // Integer.parseInt(args[0]);
		SecureSystem sys = new SecureSystem(nDigits);		
		PassGen pgen = new PassGen(nDigits);
		
		int nCores = Runtime.getRuntime().availableProcessors();	
		long startTime = System.currentTimeMillis();
		long tot = (long) Math.pow(96, nDigits);
		long delta = tot/nCores;
		long base = 0;
		long limit = base + delta;
		for (int i = 0; i < nCores - 1; i++){
			new Attacker(i,base,limit,pgen,sys,startTime).start();
			base = limit;
			limit += delta;
		}
		new Attacker(nCores-1,base,tot,pgen,sys,startTime).start();
	}

}
