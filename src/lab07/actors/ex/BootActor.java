package lab07.actors.ex;

import java.util.*;

import akka.actor.*;

public class BootActor extends UntypedActor {

	private int nPeersDone;
	private int nPeers;

	public void preStart() {
		log("running");
		List<ActorRef> peers = new ArrayList<>();
		this.nPeers = 3;
		for (int i = 0; i < this.nPeers; i++){
			ActorRef peer = getContext().actorOf(Props.create(PeerActor.class), "peer-" + i);
			peers.add(peer);
		}
		log("peers created.");
		for (ActorRef peer: peers){
			peer.tell(new PeersMsg(peers), getSelf());
		}
		log("peers informed.");
		this.nPeersDone = 0;
	}

	@Override
	public void onReceive(Object msg) {
		this.nPeersDone++;
		if (this.nPeersDone == this.nPeers){
			getContext().stop(getSelf());
		}
	}

	private void log(String msg){
		System.out.println("[BootActor] " + msg);
	}

}
