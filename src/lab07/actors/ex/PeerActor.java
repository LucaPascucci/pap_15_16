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
		  nValuesReceived = 0;
		  Random rand = new Random(System.nanoTime());
		  myValue = Math.abs(rand.nextInt());
		  max = myValue;
		  log("booted - value: "+myValue);

	  }
	
	  @Override
	  public void onReceive(Object msg) {
		  if (msg instanceof PeersMsg){
			  bootActor = getContext().sender();
			  log("informed about peers");
			  List<ActorRef> peers = ((PeersMsg)msg).getPeers();
			  nPeers = peers.size();
			  for (ActorRef peer: peers){
				  peer.tell(new ValueMsg(myValue), getSelf());
			  }
			  log("value sent to peers.");
			  
		  } else {
			  ValueMsg vmsg = (ValueMsg) msg;
			  nValuesReceived++;
			  
			  int value = vmsg.getValue();
			  if (value > max){
				  max = value;
			  }
			  
			  if (nValuesReceived == nPeers){
				  if (max == myValue){
					System.out.println("Elected ("+this.getSelf()+" - value: "+myValue+")");
				  } 
				  bootActor.tell(new DoneMsg(),getSelf());
				  getContext().stop(getSelf());
			  }
		  }
	  }

	  private void log(String msg){
 		  System.out.println("[PeerActor-"+getSelf()+"] "+msg);
	  }
	  
}
