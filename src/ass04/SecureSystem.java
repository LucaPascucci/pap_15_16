package ass04;

import java.util.Random;

/**
 * Class representing a (fake) protected system to be violated
 * 
 * @author aricci
 *
 */
public class SecureSystem {

	private final String password;
	/**
	 * Create the system protected by password composed by 
	 * the specified number of printable characters.
	 * 
	 * A printable characters is from ASCII code 32 to 127
	 * 
	 * 
	 * @param passwordSize
	 */
	public SecureSystem(int passwordSize){
		StringBuffer passw = new StringBuffer("");
		Random rand = new Random(System.nanoTime());
		for (int i = 0; i < passwordSize; i++){
			passw.append((char) (32 + rand.nextInt(96)));
		}
		//this.password = passw.toString();
		this.password = "yyyyy";

		System.out.println("Password generated: " + this.password);
	}

	/**
	 * Try to log in, specifying the password
	 * 
	 * @param password
	 * @return true if the password is correct
	 */
	public boolean login(String password){
		return this.password.equals(password);
	}
	
}
