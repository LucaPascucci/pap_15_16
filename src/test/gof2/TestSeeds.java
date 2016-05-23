package test.gof2;

public class TestSeeds {

	public static void main(String[] args) {
		int row=40,column=60;
		
		Model model= new Model(row,column);
		View view = new View(650,600,row,column);
		Controller controller = new Controller(model, view);
		view.addController(controller);
		view.setVisible(true);
	}

}
