package lab07.actors.ex;

public class ValueMsg {
	
	private int value;
	
	public ValueMsg(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}

}
