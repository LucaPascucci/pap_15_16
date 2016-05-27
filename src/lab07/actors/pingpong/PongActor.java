package lab07.actors.pingpong;

import akka.actor.*;

public class PongActor extends UntypedActor {
	
	private long count;
	
	  public void preStart() {
		  this.count = 0;
	  }
	
	  @Override
	  public void onReceive(Object msg) {
		  this.count++;
		  PingMsg mess = (PingMsg) msg;
		  System.out.println("PING received: " + mess.getValue());
		  getSender().tell(new PongMsg(this.count), getSelf());
	  }

}
