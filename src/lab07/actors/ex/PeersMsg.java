package lab07.actors.ex;

import java.util.List;

import akka.actor.ActorRef;

public class PeersMsg {
	List<ActorRef> peers;
	
	public PeersMsg(List<ActorRef> peers){
		this.peers = peers;
	}
	
	public List<ActorRef>  getPeers(){
		return peers;
	}
}
