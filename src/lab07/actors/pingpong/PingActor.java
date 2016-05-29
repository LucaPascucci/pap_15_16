package lab07.actors.pingpong;

import akka.actor.*;

public class PingActor extends UntypedActor {

	private long count;
	
	  public void preStart() {
		  this.count = 0;
		  final ActorRef ponger = getContext().actorOf(Props.create(PongActor.class), "ponger");
		  ponger.tell(new PingMsg(this.count), getSelf());
	  }
	
	  @Override
	  public void onReceive(Object msg) {
		  this.count++;
		  PongMsg mess = (PongMsg) msg;
		  System.out.println("PONG received: " + mess.getValue());
		  getSender().tell(new PingMsg(this.count), getSelf());
	  }
	
}
