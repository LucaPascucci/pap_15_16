package ass06.ex1;

import java.util.Optional;

public class BaseAgent extends Thread {

	private Blackboard bb;
	private String name;
	
	public BaseAgent(String name, Blackboard bb){
		this.bb = bb;
		this.name = name;
	}
	
	protected void log(String msg){
		synchronized(System.out){
			System.out.println("[" + this.name + "] " + msg);
		}
	}
	
	protected void post(String tag, Msg content){
		this.bb.post(tag,content);
	}
	
	protected Msg take(String tag){
		return this.bb.take(tag);
	}
	
	protected Msg read(String tag){
		return this.bb.read(tag);
	}

	protected Optional<Msg> takeIfPresent(String tag){
		return this.bb.takeIfPresent(tag);
	}
	
	protected Optional<Msg> readIfPresent(String tag){
		return this.bb.readIfPresent(tag);
	}
	
	protected void waitFor(long ms){
		try {
			Thread.sleep(ms);
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}

}
