package ass03;

public class WordLen {

	final private String word;
	final private int len;
	
	public WordLen(String w, int l){
		this.word = w;
		this.len = l;
	}
	
	public String getWord(){
		return this.word;
	}
	
	public int getLength(){
		return this.len;
	}
}