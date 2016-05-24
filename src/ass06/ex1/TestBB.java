package ass06.ex1;

// import pap.ass06.ex1.sol.BlackboardImpl;

public class TestBB {

	public static void main(String[] args) {

		Blackboard bb = new BlackboardImpl();
		
		new AgentA("a1",bb).start();
		new AgentB1("b1a",bb).start();
		new AgentB1("b1b",bb).start();
		new AgentB1("b1c",bb).start();
		new AgentB2("b2",bb).start();
		new AgentC("c1",bb).start();
		new AgentC("c2",bb).start();
	}

}
