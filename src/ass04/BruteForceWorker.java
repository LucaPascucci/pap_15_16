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
        super();
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

        /*
        calcolo l'indice del carattere di partenza e conclusivo
        nel caso lo step contenga una parte decimale approssimo per eccesso lo step (9.6 -> 10)
        altrimenti utilizzo il normale valore dello step
        */
        int begin = (this.id - 1) * (int)(this.step % 1 != 0 ? this.step + 1 : this.step);
        int end = begin + (int)(this.step % 1 != 0 ? this.step + 1 : this.step);

        //Ultimo thread = nel caso lo step contenga una parte decimale avrà un intervallo minore dei precedenti
        if (this.id == CORES && (this.step % 1 != 0)){
            end = (int) (this.step * 10);
        }

        System.out.println("Started worker: " + this.id + " Start: " + begin + " end " + (end - 1));

        //avvio uno stream dalla partenza alla fine - 1 dato che l'indice iniziale del thread seguente sarà
        IntStream.range(begin,end)
                .forEach(i -> {
                    //inserisco nella prima posizione il carattere di partenza
                    this.sb.setCharAt(0, (char) (i + FIRST_CHAR)); //aggiungo 32 per ricavare il codice ascii corrispondente
                    this.checkStrnGen(1); //avvio la creazione del resto della stringa (dalla posizione 1 a length) e la verifica se la password corrisponde
                });

    }

    private void checkStrnGen(int n) {

        if (n == this.sb.length()) { //controllo con la password solo se ho riempito la stringa
            String attempt = this.sb.toString();
            if (this.system.login(attempt)) {
                this.printResult(attempt);
                System.exit(0);
            }
            return;
        }

        //riempie ricorsivamente la stringa generando tutte le possibili combinazioni
        IntStream.rangeClosed(32,127).forEach(i -> {
            this.sb.setCharAt(n, (char) i);
            checkStrnGen(n + 1);
        });
    }

    private void printResult(String password){
        System.out.println("Found password: " + password);
        System.out.println("Founded with worker " + this.id + " in " + (System.currentTimeMillis() - this.start_time) + " millis");
    }
}
