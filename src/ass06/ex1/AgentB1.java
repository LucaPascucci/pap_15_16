package ass06.ex1;

public class AgentB1 extends BaseAgent {
	
	public AgentB1(String name, Blackboard bb){
		super(name,bb);
	}
	
	public void run(){
		waitFor(1000);
		log("going to wait for a request");
		Msg req = take("request");
		log("new request: "+req.getContent());
		post("reply",new Msg("this is a reply to: "+req.getContent()));		
	}
}
