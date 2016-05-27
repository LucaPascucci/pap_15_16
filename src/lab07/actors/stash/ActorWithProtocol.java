package lab07.actors.stash;

import akka.actor.UntypedActorWithStash;

public class ActorWithProtocol extends UntypedActorWithStash {
	public void onReceive(Object msg) {
		if (msg.equals("open")) {
			System.out.println("opening...");
			unstashAll();
			getContext().become((Object m) -> {
				if (m.equals("write")) {
					System.out.println("writing...");
				} else if (m.equals("close")) {
					System.out.println("closing...");
					getContext().unbecome();
				}				
			});
		} else {
			System.out.println("stashing "+msg);
			stash();
		}
	}
}