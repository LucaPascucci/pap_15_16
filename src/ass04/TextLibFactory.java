package ass04;

public class TextLibFactory implements TextLib {
	
	private static TextLibFactory instance;
	
	/**
	 * Get the instance of the factory
	 *   
	 * @return
	 */
	public synchronized static TextLib getInstance(){
		if (instance == null){
			instance = new TextLibFactory();
			return instance;
		} else {
			return instance;
		}
	}
	
	public synchronized void cls(){
		System.out.print("\u001b[2J");
		System.out.flush();
		
	}
	public synchronized void writeAt(int x, int y, String st){
		System.out.print("\u001b["+y+";"+x+"H");
		System.out.print(st);
		System.out.flush();
	}

	public synchronized void writeAt(int x, int y, String st, Color color){
		String c = "";
		switch (color){
		case BLACK: c = "30"; break;
		case RED: c = "31"; break;
		case GREEN: c = "32"; break;
		case YELLOW: c = "33"; break;
		case BLUE: c = "34"; break;
		case MAGENTA: c = "35"; break;
		case CYAN: c = "36"; break;
		case WHITE: c = "37"; break;
		}
		System.out.print("\u001b["+c+"m");
		System.out.print("\u001b["+y+";"+x+"H");
		System.out.print(st);
		System.out.flush();
		System.out.print("\u001b[37m");
	}
	 
	private TextLibFactory(){}
	

}
