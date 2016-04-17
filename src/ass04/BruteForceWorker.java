package ass04;

import java.util.stream.IntStream;

/**
 * Created by Luca on 16/04/16.
 */

public class BruteForceWorker extends Thread {

    private static final int CORES = Runtime.getRuntime().availableProcessors();
    private static final int FIRST_CHAR = 32;
    private final long start_time;
    private final SecureSystem system;
    private final int id;
    private final double step;
    private final StringBuilder sb;

    public BruteForceWorker(int n, long time, SecureSystem sys, double step, int length){
        this.start_time = time;
        this.system = sys;
        this.id = n;
        this.step = step;
        this.sb = new StringBuilder();
        this.sb.setLength(length);
    }

    @Override
    public void run() {
        super.run();
        System.out.println("Started worker: " + this.id);
        //calcolo l'indice del carattere di partenza e conclusivo
        int begin = (int) this.step * (this.id - 1);
        int end =  begin + (int) this.step;
        //se lo step contiene una parte decimale attriubisco il carico di lavoro più grande all'ultimo thread
        if (this.id == CORES && (this.step % 1 != 0)){
            end = (int) this.step * CORES;
        }

        //avvio uno stream dalla partenza alla fine - 1 visto che l'indice iniziale del thread seguente sarà
        IntStream.rangeClosed(begin,(end - 1))
                .forEach(i -> {
                    //inserisco nella prima posizione il carattere di partenza
                    this.sb.setCharAt(0, (char) (i + FIRST_CHAR)); //sommo 32 per ricavare il codice ascii corretto
                    check(this.sb, 1); //avvio la creazione del resto della stringa (dalla posizione 1 a length)
                });
    }

    private void check(StringBuilder loc_sb, int n) {

        System.out.println(n);
        if (n == loc_sb.length()) { //controllo con la password solo se ho riempito la stringa
            String attempt = loc_sb.toString();
            if (this.system.login(attempt)) {
                this.printResult(attempt);
                System.exit(0);
            }
            return;
        }

        //riempie ricorsivamente la stringa generando tutte le possibili combinazioni
        IntStream.rangeClosed(32,127).forEach(i -> {
            loc_sb.setCharAt(n, (char) i);
            check(loc_sb, n + 1);
        });
    }

    private void printResult(String password){
        System.out.println("Found password: " + password);
        System.out.println("Founded with worker " + this.id + " in " + (System.currentTimeMillis() - this.start_time) + " millis");
    }
}
