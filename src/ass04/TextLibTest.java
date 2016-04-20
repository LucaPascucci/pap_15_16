package ass04;

import java.util.stream.IntStream;

public class TextLibTest {

    private static final int MAX_X = 75;
    private static final int MAX_Y = 25;
    private static final String[] WORDS = {"Mare","Sole","Luna","Nuvole","Vento","Sabbia","Prato","Albero"};

	public static void main(String[] args) throws Exception {

		TextLib lib = TextLibFactory.getInstance();
        lib.cls();

        //creazione dei bordi della console
        IntStream.rangeClosed(1,MAX_X).forEach(i -> {
            lib.writeAt(i,0,"-");
            lib.writeAt(i,MAX_Y,"-");
        });

        IntStream.rangeClosed(1,MAX_Y).forEach(i -> {
            lib.writeAt(1,i,"|");
            lib.writeAt(MAX_X,i,"|");
        });

        //creazione di un thread per ogni parola immessa o presente
        if (args.length != 0) {
            IntStream.range(0, args.length)
                    .forEach(i -> new WordAgent(args[i], lib, MAX_X, MAX_Y).start());
        } else {
            IntStream.range(0, WORDS.length)
                    .forEach(i -> new WordAgent(WORDS[i], lib, MAX_X, MAX_Y).start());
        }
	}

}
