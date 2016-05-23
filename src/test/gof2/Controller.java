package test.gof2;

public class Controller {

	private View view;
	private Model model;
	private Flag flag;
	
	public Controller(Model model,View view){
		this.view=view;
		this.model=model;
		flag=new Flag();
	}
	
	public void start(int speed){
		flag.set(false);
		new Master(view,model,speed,flag).start();
	}
	
	public void stop(){
		flag.set(true);
		view.clear();
	}
	
	public void pause(){
		flag.set(true);
	}
}
