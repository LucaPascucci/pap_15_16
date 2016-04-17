package ass04;

public class TextLibTest {

	public static void main(String[] args) throws Exception {		
		TextLib lib = TextLibFactory.getInstance();
		lib.cls();
		lib.writeAt(10, 5, "*");
		Thread.sleep(1000);
		lib.writeAt(10, 5, " ");
		lib.writeAt(11, 5, "*");
		Thread.sleep(1000);
		lib.writeAt(11, 5, " ");
		lib.writeAt(12, 5, "!", TextLib.Color.RED);
		Thread.sleep(1000);
		System.out.println();
	}

}
