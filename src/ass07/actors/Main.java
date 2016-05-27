package ass07.actors;

import akka.actor.ActorSystem;

/**
 * Created by Luca on 27/05/16.
 */
public class Main {

    private static final int MAX = 2000;
    private static final int MIN = -2000;

    public static void main(String[] args) {

        int players;
        if (args.length == 1) {
            players = Integer.parseInt(args[0]);
        }else {
            players = 5;
        }

        //Creo il sistema(gioco) ed avvio l'oracolo
        ActorSystem system = ActorSystem.create("GuessTheNumber");
        system.actorOf(OracleActor.props(players,MAX,MIN), "OracleActor");

        //TODO chiedere dei messaggi finali che si mandano gli stessi actor e non sono stati consegnati
    }
}
