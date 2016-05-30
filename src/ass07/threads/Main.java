package ass07.threads;

import ass07.threads.controller.Controller;
import ass07.threads.model.Model;
import ass07.threads.view.MainView;

import java.awt.Dimension;

/**
 * Created by Luca on 29/05/16.
 */
public class Main {

    private static final Dimension DEFAULT_WINDOW_SIZE = new Dimension(650,900);
    private static final int MAX = 1000000;
    private static final int MIN = -1000000;

    public static void main(String[] args) {

        int players;
        if (args.length == 1) {
            players = Integer.parseInt(args[0]);
        }else {
            players = 10;
        }

        Model model = new Model(players,MAX,MIN);
        MainView view = new MainView(DEFAULT_WINDOW_SIZE);
        Controller controller = new Controller(view,model);
        view.setListener(controller);
        view.setVisible(true);
    }
}