package lab05.factorizer;

import java.util.ArrayList;

public class FactorizerWithCache_bug implements FactorizerService {

	private CacheWithBug cache;

	FactorizerWithCache_bug(int cacheSize) {
		cache = new CacheWithBug(cacheSize);
	}

	public int[] getFactors(long n) {
		int[] factors = null;
		factors = cache.check(n);
		if (factors != null) {
			return factors;
		} else {
			ArrayList<Integer> factorList = factorize(n);
			factors = new int[factorList.size()];
			int index = 0;
			for (Integer x : factorList) {
				factors[index++] = x.intValue();
			}
			cache.put(n, factors);
			return factors;
		}
	}

	private ArrayList<Integer> factorize(long n) {
		long value = n;
		int factor = 2;
		ArrayList<Integer> factors = new ArrayList<Integer>();
		while (factor <= value) {
			if (value % factor == 0) {
				value /= factor;
				if (isPrime(factor)) {
					factors.add(factor);
				}
			}
			factor++;
		}
		return factors;
	}

	private boolean isPrime(long factor) {
		long max = Math.round(Math.sqrt(factor));
		for (int i = 2; i <= max; i++) {
			if (factor % i == 0) {
				return false;
			}
		}
		return true;
	}

}
