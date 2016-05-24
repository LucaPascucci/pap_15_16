package ass06.seeds;

import ass06.seeds.controller.Controller;
import ass06.seeds.model.SeedsSet;
import ass06.seeds.view.MainView;

import java.awt.*;

/**
 * Created by Luca on 20/05/16.
 */
public class Main {

    private static final Dimension DEFAULT_WINDOW_SIZE = new Dimension(800,765);
    private static final Dimension DEFAULT_WORLD_SIZE = new Dimension(75,65);

    public static void main(String[] args) {

        SeedsSet seedsSet = new SeedsSet(DEFAULT_WORLD_SIZE);
        MainView view = new MainView(DEFAULT_WINDOW_SIZE,DEFAULT_WORLD_SIZE);
        Controller controller = new Controller(view, seedsSet);
        view.setListener(controller);
        view.setVisible(true);


        //TODO java.util.ConcurrentModificationException

    }
}
