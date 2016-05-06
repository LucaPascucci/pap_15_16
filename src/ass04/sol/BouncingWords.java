package ass04.sol;

import ass04.TextLib;
import ass04.TextLibFactory;
import static ass04.TextLib.Color;
import java.util.Random;

public class BouncingWords {

	public static void main(String[] args) {
		Screen screen = new Screen(5,5,80,40);		
		TextLib lib = TextLibFactory.getInstance();
		lib.cls();	
		/*
		new BouncingWord("Hello",20,10, new V2d(1,0), 0.1f, Color.WHITE, screen).start();
		new BouncingWord("World",20,5, new V2d(0,1), 0.05f, Color.BLUE, screen).start();
		new BouncingWord("from Cesena",20,5, new V2d(Math.PI/3), 0.075f, Color.YELLOW, screen).start();
		*/		
		Random gen = new Random();
		for (int i = 0; i < 100; i++){
			new BouncingWord("Hello-"+i, 
								gen.nextInt(60)+5, 
								gen.nextInt(40)+5, 
								new V2d(gen.nextDouble()*Math.PI), 
								gen.nextFloat()/20, Color.GREEN, screen).start();
		}
	}

}
