package ass08.trackBeatActors;

import akka.actor.ActorSystem;
import ass08.trackBeatActors.actors.ControllerActor;

import java.awt.*;

/**
 * Created by Luca on 25/06/16.
 */
public class Main {

    private static final Dimension DEFAULT_WINDOW_SIZE = new Dimension(850,750);
    private static final int HB_TH = 85;
    private static final int SEC_TH = 3;

    public static void main(String[] args) {

        //Creo il sistema(gioco) ed avvio l'oracolo
        ActorSystem system = ActorSystem.create("TrackBeat-Simulator");
        system.actorOf(ControllerActor.props(DEFAULT_WINDOW_SIZE,HB_TH,SEC_TH), "ControllerActor");
    }
}
