package ass04;

import java.util.stream.IntStream;

public class TextLibTest {

    private static final int MAX_X = 75;
    private static final int MAX_Y = 25;
    private static final String[] WORDS = {"Mare","Sole","Luna","Nuvole","vento"};

	public static void main(String[] args) throws Exception {

        //TODO commentare

		TextLib lib = TextLibFactory.getInstance();
        lib.cls();

        IntStream.rangeClosed(0,MAX_X).forEach(i -> {
            lib.writeAt(i,0,"-");
            lib.writeAt(i,MAX_Y,"-");
        });

        IntStream.rangeClosed(0,MAX_Y).forEach(i -> {
            lib.writeAt(1,i,"|");
            lib.writeAt(MAX_X,i,"|");
        });

        if (args.length != 0) {
            IntStream.range(0, args.length)
                    .forEach(i -> new WordAgent(args[i], lib, MAX_X, MAX_Y).start());
        } else {
            IntStream.range(0, WORDS.length)
                    .forEach(i -> new WordAgent(WORDS[i], lib, MAX_X, MAX_Y).start());
        }
	}

}
