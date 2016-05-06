package test;

public class MeaningOfThis2 {
	  public final int value = 4;
	  public void doIt(){
	    int value = 6;
	    Runnable r = new Runnable(){
	      public final int value = 5;
	      public void run(){
	        int value = 10;
	        System.out.println(this.value);
	      }
	    };
	    r.run(); 
	  }

	  public static void main(String[] args) {
	    MeaningOfThis2 m = new MeaningOfThis2();
	    m.doIt(); 
	  }
	}
