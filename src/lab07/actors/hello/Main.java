package lab07.actors.hello;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {

    public static void main(String[] args) {

        ActorSystem system = ActorSystem.create("MySystem");

        ActorRef act = system.actorOf(Props.create(HappyActor.class)); //rappresenta il riferimento ad un solo attore
        //Props serve per configurare il sistema

        act.tell(new HelloMsg("World"), ActorRef.noSender()); //manda un messaggio all'attore a cui fa riferimento con messaggio e mittente

        //Un attore può essere creato anche da un'altro attore
    }
}
