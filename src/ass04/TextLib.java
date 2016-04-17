package ass04;

public interface TextLib {

	enum Color { BLACK, RED, GREEN, YELLOW, BLUE, MAGENTA, CYAN, WHITE }
	
	/**
	 * Clear the console screen
	 */
	void cls();
	
	/**
	 * Write a text message on the console screen at the specified position
	 */
	void writeAt(int x, int y, String msg);
	
	/**
	 * Write a text message on the console screen at the specified position and color.
	 */
	void writeAt(int x, int y, String st, Color color);

}
