package lab05.factorizer;

public class Cache {

	private static final CacheSlot EMPTY = new CacheSlot(-1,null);
	private CacheSlot[] cache;
	private int firstSlot;
    
	public Cache(int size){
		cache = new CacheSlot[size];
		for (int i = 0; i < cache.length; i++){
			cache[i] = EMPTY;
		}
		firstSlot = 0;
	}
	
	public int[] check(long n){
		for (CacheSlot v: cache){
			if (v.getValue() == n){
				return v.getFactors();
			}
		}
		return null;
	}
	
	public void put(long n, int[] factors){
		cache[firstSlot].set(n, factors);
		firstSlot = (firstSlot + 1) % cache.length;
	}

}
