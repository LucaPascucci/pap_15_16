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
			System.out.println("["+name+"] "+msg);
		}
	}
	
	protected void post(String tag, Msg content){
		bb.post(tag,content);
	}
	
	protected Msg take(String tag){
		return bb.take(tag);
	}
	
	protected Msg read(String tag){
		return bb.read(tag);
	}

	protected Optional<Msg> takeIfPresent(String tag){
		return bb.takeIfPresent(tag);
	}
	
	protected Optional<Msg> readIfPresent(String tag){
		return bb.readIfPresent(tag);
	}
	
	protected void waitFor(long ms){
		try {
			Thread.sleep(ms);
		} catch (Exception ex){}
	}

}
