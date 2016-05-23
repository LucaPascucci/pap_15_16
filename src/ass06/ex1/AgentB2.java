package ass06.ex1;

import java.util.Optional;

public class AgentB2 extends BaseAgent {
	
	public AgentB2(String name, Blackboard bb){
		super(name,bb);
	}
	
	public void run(){
		log("Going to check for a request");
		Optional<Msg> req = readIfPresent("request");
		int nTimes = 0;
		while (!req.isPresent() && nTimes < 10){
			log("Not found.");
			waitFor(100);			
			req = readIfPresent("request");
			nTimes++;
		}
		if (req.isPresent()){
			log("Found: " + req.get().getContent());
		}
	}
}
