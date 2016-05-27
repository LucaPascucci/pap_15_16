package lab07.actors.hello;

public final class HelloMsg {

	private final String content;

	public HelloMsg(String content){
		this.content = content;
	}

	public String getContent(){
		return this.content;
	}
}
