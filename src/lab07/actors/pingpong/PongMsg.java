package lab07.actors.pingpong;

public class PongMsg {

	private long value;

	public PongMsg(long value){
		this.value = value;
	}

	public long getValue(){
		return value;
	}
}
