package lab05.factorizer;

import java.util.*;
import java.util.concurrent.*;

public class CacheWithBug {

	private static final CacheSlot EMPTY = new CacheSlot(-1,null);
	private CacheSlot[] cache;
	private int firstSlot;
    private Semaphore mutex;
	
	public CacheWithBug(int size){
		cache = new CacheSlot[size];
		for (int i = 0; i < cache.length; i++){
			cache[i] = EMPTY;
		}
		firstSlot = 0;
		mutex = new Semaphore(1);
	}
	
	public int[] check(long n){
		try {
			mutex.acquire();
			for (CacheSlot v: cache){
				if (v.getValue() == n){
					return v.getFactors();
				}
			}
			return null;
		} catch (Exception ex){
			return null;
		} finally {
			mutex.release();
		}
	}
	
	public boolean put(long n, int[] factors){
		try {
			mutex.acquire();
			if (check(n)!=null){
				return false;
			}
			cache[firstSlot].set(n, factors);
			firstSlot = (firstSlot + 1) % cache.length;
			return true;
		} catch (InterruptedException ex){
			return false;
		} finally {
			mutex.release();
		}
	}

}
