package lab07.actors.ex;

import java.util.List;
import java.util.Random;

import akka.actor.*;

public class PeerActor extends UntypedActor {
	
	  private int nPeers;
	  private int nValuesReceived;
	  private int myValue;
	  private int max;
	  private ActorRef bootActor;
	  
	  public void preStart() {
		  this.nValuesReceived = 0;
		  Random rand = new Random(System.nanoTime());
		  this.myValue = Math.abs(rand.nextInt());
		  this.max = this.myValue;
		  log("booted - value: " + this.myValue);

	  }
	
	  @Override
	  public void onReceive(Object msg) {
		  if (msg instanceof PeersMsg){
			  this.bootActor = getContext().sender();
			  log("informed about peers");
			  List<ActorRef> peers = ((PeersMsg)msg).getPeers();
			  this.nPeers = peers.size();
			  for (ActorRef peer: peers){
				  peer.tell(new ValueMsg(this.myValue), getSelf());
			  }
			  log("value sent to peers.");
			  
		  } else {
			  ValueMsg vmsg = (ValueMsg) msg;
			  this.nValuesReceived++;
			  
			  int value = vmsg.getValue();
			  if (value > this.max){
				  this.max = value;
			  }
			  
			  if (this.nValuesReceived == this.nPeers){
				  if (this.max == this.myValue){
					System.out.println("Elected ("+this.getSelf()+" - value: " + this.myValue + ")");
				  }
				  this.bootActor.tell(new DoneMsg(),getSelf());
				  getContext().stop(getSelf());
			  }
		  }
	  }

	  private void log(String msg){
 		  System.out.println("[PeerActor-" + getSelf() + "] " + msg);
	  }
	  
}
