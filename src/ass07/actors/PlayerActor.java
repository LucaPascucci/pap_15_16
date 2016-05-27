package ass07.actors;

import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;
import ass07.actors.msgs.AttemptMsg;
import ass07.actors.msgs.EndGameMsg;
import ass07.actors.msgs.HintMsg;
import ass07.actors.msgs.StartTurnMsg;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Luca on 27/05/16.
 */
public class PlayerActor extends UntypedActor{

    private int min;
    private int max;
    private int lastNumber;

    private PlayerActor(int max, int min){
        this.max = max;
        this.min = min;
        this.lastNumber = this.nextNumber();
    }

    public static Props props(final int max, final int min) {
        return Props.create(new Creator<PlayerActor>() {
            private static final long serialVersionUID = 1L;

            @Override
            public PlayerActor create() throws Exception {
                return new PlayerActor(max, min);
            }
        });
    }

    @Override
    public void onReceive(Object message) throws Exception {

        if (message instanceof HintMsg){
            //Suggerimento ricevuto dall'oracolo
            int hint = ((HintMsg) message).getHint();
            if (hint < 0){
                this.max = this.lastNumber;
            }else{
                this.min = this.lastNumber;
            }
            this.lastNumber = this.nextNumber();

        } else if (message instanceof StartTurnMsg){
            //Avvio del gioco
            //tutti i player hanno tentato ma nessuno ha ancora vinto
            this.getSender().tell(new AttemptMsg(this.lastNumber),getSelf());

        } else if (message instanceof EndGameMsg){
            //fine del gioco un player ha vinto(true) gli altri hanno perso(false)
            if (((EndGameMsg) message).getResult()){
                System.out.println(this.getSelf() + " won!");
            }else{
                System.out.println(this.getSelf() + " sob!");
            }
            this.getContext().stop(getSelf());
        }
    }

    private int nextNumber(){
        return ThreadLocalRandom.current().nextInt(this.min, this.max + 1);
    }
}
