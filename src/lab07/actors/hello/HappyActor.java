package lab07.actors.hello;
import akka.actor.UntypedActor;

public class HappyActor extends UntypedActor {


    public void onReceive(Object msg) {

        //Controlla la classe del messaggio
        if (msg instanceof HelloMsg) {

            HelloMsg hello = (HelloMsg) msg;
            System.out.println("Hello " + hello.getContent());
        } else {
            unhandled(msg);
        }
        getContext().stop(getSelf());
    }
}
