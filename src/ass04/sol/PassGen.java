package ass04.sol;

/**
 * Naive password generator
 * 
 * @author aricci
 *
 */
public class PassGen {
	private int nDigits;
	
	public PassGen(int nDigits){
		this.nDigits = nDigits;
	}

	public char[] getPass(long n){
		char[] pass = new char[this.nDigits];
		for (int i = 0; i < this.nDigits; i++){
			pass[i] = (char) 32;
		}
		int i = 0;	
		long v = n;
		while (v > 0){
			pass[i] = (char) ((v % 96) + 32);
			v = v / 96;
			i++;
		}
		return pass;
	}

	
	public static String toStringVal(char[] pass){
		StringBuffer sb = new StringBuffer("[");
		for (char p: pass){
			sb.append((int)p+" ");
		}
		sb.append("]");
		return sb.toString();
	}
	
}
