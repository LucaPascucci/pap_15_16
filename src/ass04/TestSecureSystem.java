package ass04;

import java.util.stream.IntStream;

public class TestSecureSystem {

    private static final int CORES = Runtime.getRuntime().availableProcessors(); //numero di core disponibili
    private static final int FIRST_CHAR = 32; //Primo carattere stampabile
    private static final double PRINTABLE_CHARS = 127 - FIRST_CHAR + 1; //96 caratteri stampabili

	public static void main(String[] args) {

        int pass_length;
        if (args.length == 1) {
            pass_length = Integer.parseInt(args[0]);
        }else {
            pass_length = 5;
        }
        SecureSystem sys = new SecureSystem(pass_length);

        double step = PRINTABLE_CHARS / CORES; //creo l'intervallo di caratteri che dovrà essere attribuito ad ogni thread

        long start_time = System.currentTimeMillis();
        //creo un thread per ogni processore
        IntStream.range(0, CORES).
                forEach(i -> new BruteForceWorker((i + 1), start_time, sys, step, pass_length).start());

	}
}
