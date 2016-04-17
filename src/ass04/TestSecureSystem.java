package ass04;

import java.util.stream.IntStream;

public class TestSecureSystem {

    private static final int PASS_LENGTH = 3;
    private static final int CORES = Runtime.getRuntime().availableProcessors(); //numero di core disponibili
    private static final SecureSystem SYS = new SecureSystem(PASS_LENGTH);
    private static final int FIRST_CHAR = 32; //Primo carattere stampabile
    private static final double PRINTABLE_CHARS = 127 - FIRST_CHAR + 1; //96 caratteri stampabili

	public static void main(String[] args) {

        double step = PRINTABLE_CHARS / CORES; //creo l'intervallo di caratteri che dovrà essere attribuito ad ogni thread

        long start_time = System.currentTimeMillis();
        //creo un thread per ogni processore
        IntStream.rangeClosed(0, CORES - 1).
                forEach(i -> new BruteForceWorker((i + 1), start_time, SYS, step, PASS_LENGTH).start());

	}
}
