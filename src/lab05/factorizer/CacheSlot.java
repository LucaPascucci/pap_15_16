package lab05.factorizer;

public class CacheSlot {

	private long value;
	private int[] factors;
	
	public CacheSlot(long v, int[] f){
		this.value = v;
		this.factors = f;
	}
	
	public long getValue(){
		return value;
	}
	
	public int[] getFactors(){
		return factors;
	}
	
	public void set(long value, int[] f){
		this.value = value;
		//try { Thread.sleep(1); } catch (Exception ex){}
		this.factors = f;
	}
}
