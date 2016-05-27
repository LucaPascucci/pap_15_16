package lab07.actors.pingpong;

public class PingMsg {

	private long value;

	public PingMsg(long value){
		this.value = value;
	}

	public long getValue(){
		return value;
	}
}
