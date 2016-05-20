package lab06.mandel01_exec;

/**
 * Controller part of the application - passive part.
 * 
 * @author aricci
 *
 */
public class Controller implements InputListener {

	private MandelbrotView view;
	private MandelbrotSet set;
	private Flag 	stopFlag;
	
	public Controller(MandelbrotSet set, MandelbrotView view){
		this.set = set;
		this.view = view;
	}
	
	public void started(Complex c0, double diam){
		stopFlag = new Flag();
		new Master(c0,diam,set,view,stopFlag).start();
	}

	public void stopped() {
		stopFlag.set();
	}

}
