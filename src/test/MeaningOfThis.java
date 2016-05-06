package test;

public class MeaningOfThis {
	 
	  public static Runnable m(){
		  int v = 0;
		
		  Runnable r = new Runnable(){
			  public void run(){
				  System.out.println(v);
			  }
		  };

		  return r;
	  }

	  public static void main(String[] args) {
		  Runnable r = m();
		  r.run();
	  }
	}
