package lab07.actors.stash;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {
    public static void main(String[] args) throws Exception {

        ActorSystem system = ActorSystem.create("MySystem");
        ActorRef act = system.actorOf(Props.create(ActorWithProtocol.class));
        act.tell("write", ActorRef.noSender());	// this will be stashed
        act.tell("write", ActorRef.noSender()); // this will be stashed
        Thread.sleep(1000);
        act.tell("open", ActorRef.noSender());	// this will change the behaviour
        act.tell("write", ActorRef.noSender()); // this will be immediately received
        act.tell("open", ActorRef.noSender());	// this will be ignored/lost
        Thread.sleep(1000);
        act.tell("close", ActorRef.noSender());	// this will change the behaviour
        Thread.sleep(500);
        act.tell("open", ActorRef.noSender());	// this will be accepted
    }
}
