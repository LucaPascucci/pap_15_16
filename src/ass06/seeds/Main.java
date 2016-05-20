package ass06.seeds;

/**
 * Created by Luca on 20/05/16.
 */
public class Main {

    public static void main(String[] args) {

        int width = 800;
        int height = 800;

        Model model = new Model();
        MainView view = new MainView(width,height);
        Controller controller = new Controller(view, model);

        view.setListener(controller);
        view.setVisible(true);

    }
}
