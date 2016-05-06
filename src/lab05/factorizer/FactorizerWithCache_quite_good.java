package lab05.factorizer;

import java.util.ArrayList;

public class FactorizerWithCache_quite_good implements FactorizerService {

	private Cache cache;

	FactorizerWithCache_quite_good(int cacheSize) {
		cache = new Cache(cacheSize);
	}

	public int[] getFactors(long n) {
		int[] factors = null;
		synchronized (this) {
			factors = cache.check(n);
		}
		if (factors != null) {
			return factors;
		} else {
			ArrayList<Integer> factorList = factorize(n);
			factors = new int[factorList.size()];
			int index = 0;
			for (Integer x : factorList) {
				factors[index++] = x.intValue();
			}
			synchronized (this) {
				cache.put(n, factors);
			}
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
