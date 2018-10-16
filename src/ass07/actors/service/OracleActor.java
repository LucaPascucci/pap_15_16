package ass07.actors.service;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;
import ass07.actors.msgs.AttemptMsg;
import ass07.actors.msgs.EndGameMsg;
import ass07.actors.msgs.HintMsg;
import ass07.actors.msgs.StartTurnMsg;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * Created by Luca on 27/05/16.
 */
public class OracleActor extends UntypedActor {

    private int magicNumber;
    private int max;
    private int min;
    private int players;
    private List<ActorRef> playersList;
    private int turnAttempts;
    private int globalTurn;
    private boolean winner;
    private long turnTime;

    private OracleActor(int players, int max, int min){
        this.players = players;
        this.max = max;
        this.min = min;
        this.playersList = new ArrayList<>(players);
        this.turnAttempts = 0; //numero di tentativi ricevuti per turno (Al massimo saranno uguali al numero dei giocatori)
        this.globalTurn = 1;
        this.winner = false;
        //inizializzo numero da indovinare (+1 perché estremo superiore escluso)
        this.magicNumber = ThreadLocalRandom.current().nextInt(this.min, this.max + 1);
    }

    /**
     * Crea Props per l'attore OracleActor.
     * @param players numero di giocatori
     * @param max range massimo del numero random da indovinare
     * @param min range minimo del numero random da indovinare
     * @return Props per la crezione di questo attore
     */
    public static Props props(final int players, final int max, final int min) {
        return Props.create(OracleActor.class, () -> new OracleActor(players, max, min));
    }

    @Override
    public void preStart() {

        this.log("Number: " + this.magicNumber);
        this.logln("Turn: " + this.globalTurn);

        //Creazione dei giocatori (PlayerActor)
        IntStream.range(0,this.players).forEach(i -> this.playersList.add(getContext().actorOf(PlayerActor.props(this.max,this.min),"Player-" + (i+1))));

        //Avvio del gioco
        this.playersList.forEach(p -> p.tell(new StartTurnMsg(),getSelf()));
    }

    @Override
    public void onReceive(Object message) throws Exception {

        /*
           Con questa configurazione, anche se nello stesso turno due player indovinano il numero, solo il primo attore che lo sottopone vincerà il gioco
         */
        if (message instanceof AttemptMsg) {    //Ricezione del tentativo da parte di un player

            if (this.turnAttempts == 0){
                this.turnTime = System.nanoTime();
            }
            this.turnAttempts++;
            if (!this.winner) {
                int playerMax = ((AttemptMsg) message).getMax();
                int playerMin = ((AttemptMsg) message).getMin();
                int attemptNumber = ((AttemptMsg) message).getNumber();
                this.log(getSender().toString() + " number: " + attemptNumber + " -> Range: " + playerMax + " / " + playerMin);
                int value = Integer.compare(this.magicNumber, attemptNumber); //comparo il numero da indovinare con il risultato
                if (value == 0) { //il giocatore ha trovato il numero giusto
                    this.winner = true;
                    this.logln("There's a winner!!!");
                    //Notifico a tutti i giocatori che il gioco è concluso
                    for (ActorRef player : this.playersList) {
                        if (player.equals(getSender())) {
                            player.tell(new EndGameMsg(true), getSelf());
                        } else {
                            player.tell(new EndGameMsg(false), getSelf());
                        }
                    }
                } else {
                    //Invio il suggerimento al player
                    this.getSender().tell(new HintMsg(value), getSelf());
                }
            }

            if (this.turnAttempts == this.players){
                if (!this.winner) {
                    //Tutti i giocatori hanno effettuato il tentativo in questo turno ma nessuno ha indovinato il numero
                    this.turnAttempts = 0;
                    this.log("Computation turn time (nano seconds): " + (System.nanoTime() - this.turnTime)); //calcolato dal primo all'ultimo tentativo arrivato
                    this.logln("Turn: " + ++this.globalTurn);
                    this.playersList.forEach(p -> p.tell(new StartTurnMsg(),getSelf())); //Avvio del nuovo turno
                } else {
                    //Tutti i messaggi sono stati ricevuti in coda sono stati ricevuti ed avvio la terminazione di ogni giocatore e del sistema
                    this.playersList.forEach(p -> this.getContext().stop(p));
                    this.getContext().system().shutdown();
                }
            }
        }
    }

    private void log(String msg){
        System.out.println("[ORACLE] - " + msg);
    }

    private void logln(String msg){
        System.out.println("\n[ORACLE] - " + msg);
    }
}
