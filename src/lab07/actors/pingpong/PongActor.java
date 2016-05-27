package lab07.actors.pingpong;

import akka.actor.*;

public class PongActor extends UntypedActor {
	
	private long count;
	
	  public void preStart() {
		  count = 0;
	  }
	
	  @Override
	  public void onReceive(Object msg) {
		  count++;
		  PingMsg mess = (PingMsg) msg;
		  System.out.println("PING received: "+mess.getValue());
		  getSender().tell(new PongMsg(count), getSelf());
	  }

}
