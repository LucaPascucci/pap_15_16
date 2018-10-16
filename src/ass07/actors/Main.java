package ass07.actors;

import akka.actor.ActorSystem;
import ass07.actors.service.OracleActor;

/**
 * Created by Luca on 27/05/16.
 */
public class Main {

    private static final int MAX = 100000000;
    private static final int MIN = -100000000;

    public static void main(String[] args) {

        int players;
        if (args.length == 1) {
            players = Integer.parseInt(args[0]);
        } else {
            players = 10;
        }

        //Creo il sistema(gioco) ed avvio l'oracolo
        ActorSystem system = ActorSystem.create("GuessTheNumber");

        /*  Props is a configuration class to specify options for the creation of actors
            creating an actor including associated deployment information
        */
        system.actorOf(OracleActor.props(players,MAX,MIN), "OracleActor");
    }
}
