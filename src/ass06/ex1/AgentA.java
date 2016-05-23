package ass06.ex1;

public class AgentA extends BaseAgent {

	public AgentA(String name, Blackboard bb){
		super(name,bb);
	}
	
	public void run(){
		waitFor(500);
		
		// test post and take
		
		log("Going to make a request... ");
		post("request", new Msg("This is a request"));
		Msg reply = take("reply");
		log("Got a reply: " + reply.getContent());
		
		// test read
		post("event-x", new Msg("This is an event"));
	}
}
