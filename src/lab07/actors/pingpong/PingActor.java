package lab07.actors.pingpong;

import akka.actor.*;

public class PingActor extends UntypedActor {

	private long count;
	
	  public void preStart() {
		  count = 0;
		  final ActorRef ponger = getContext().actorOf(Props.create(PongActor.class), "ponger");
		  ponger.tell(new PingMsg(count), getSelf());
	  }
	
	  @Override
	  public void onReceive(Object msg) {
		  count++;
		  PongMsg mess = (PongMsg) msg;
		  System.out.println("PONG received: "+mess.getValue());
		  getSender().tell(new PingMsg(count), getSelf());
	  }
	
}
