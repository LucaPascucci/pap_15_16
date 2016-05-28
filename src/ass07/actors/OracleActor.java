package ass07.actors;

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

    private OracleActor(int players, int max, int min){
        this.players = players;
        this.max = max;
        this.min = min;
        this.playersList = new ArrayList<>();
        this.turnAttempts = 0;
        this.globalTurn = 1;
        this.winner = false;
        //inizializzo numero da indovinare (estremo superiore escluso)
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
        return Props.create(new Creator<OracleActor>() {
            private static final long serialVersionUID = 1L;

            @Override
            public OracleActor create() throws Exception {
                return new OracleActor(players, max, min);
            }
        });
    }


    public void preStart() {

        System.out.println("Number: " + this.magicNumber);
        System.out.println("Turn: " + this.globalTurn);
        //Inizializzo i giocatori
        for (int i = 0; i < this.players; i++){
            ActorRef player = getContext().actorOf(PlayerActor.props(this.max,this.min),"Player-" + (i+1));
            this.playersList.add(player);
        }

        //avvio il turno per la ricerca del numero da parte dei giocatori
        for (ActorRef player : this.playersList){
            player.tell(new StartTurnMsg(),getSelf());
        }
    }

    @Override
    public void onReceive(Object message) throws Exception {

        if (message instanceof AttemptMsg) {

            this.turnAttempts++;
            if (!this.winner) {
                int playerMax = ((AttemptMsg) message).getMax();
                int playerMin = ((AttemptMsg) message).getMin();
                int attemptNumber = ((AttemptMsg) message).getNumber();
                System.out.println(getSender().toString() + " number: " + attemptNumber + " -> Range: " + playerMax + " / " + playerMin);
                int value = Integer.compare(this.magicNumber, attemptNumber);
                if (value == 0) { //il giocatore ha trovato il numero giusto
                    this.winner = true;
                    System.out.println("\nThere's a winner!!!");
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
                    //Tutti i giocatori hanno provato ad indovinare il numero ma non ci sono riusciti
                    this.turnAttempts = 0;
                    System.out.println("\nTurn: " + ++this.globalTurn);
                    for (ActorRef player : this.playersList) {
                        player.tell(new StartTurnMsg(), getSelf());
                    }
                }else{
                    //Tutti i messaggi sono stati ricevuti in coda sono stati ricevuti ed avvio la terminazione dell'applicazione
                    this.getContext().stop(getSelf());
                    this.getContext().system().shutdown();
                }
            }
        }

    }
}
