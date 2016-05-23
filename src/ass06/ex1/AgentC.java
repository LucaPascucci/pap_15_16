package ass06.ex1;

public class AgentC extends BaseAgent {

	public AgentC(String name, Blackboard bb){
		super(name,bb);
	}
	
	public void run(){
		Msg notification = read("event-x");
		log("Observed an event: " + notification.getContent());
	}
}
