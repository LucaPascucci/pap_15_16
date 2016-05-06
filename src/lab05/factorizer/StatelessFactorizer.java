package lab05.factorizer;

import java.util.ArrayList;

/**
 * Basic Factorizer Service - stateless, so thread-safe by definition
 * 
 * @author aricci
 *
 */
public class StatelessFactorizer implements FactorizerService {
	
	public int[] getFactors(long n){
		ArrayList<Integer> factors = factorize(n);
		int[] vect = new int[factors.size()];
		int index = 0;
		for (Integer x: factors){
			vect[index++] = x.intValue();
		}
		return vect;
	}
	
	private ArrayList<Integer> factorize(long n){
		long value = n;
		int factor = 2;
		ArrayList<Integer> factors = new ArrayList<Integer>();
		while (factor <= value){
			if (value % factor == 0){
				value /= factor;
				if (isPrime(factor)){
					factors.add(factor);
				}
			}
			factor++;
		}
		return factors;
	}
	
	private boolean isPrime(long factor){
		long max = Math.round(Math.sqrt(factor));
		for (int i = 2; i <= max; i++){
			if (factor % i == 0){
				return false;
			}
		}
		return true;
	}
	
}
