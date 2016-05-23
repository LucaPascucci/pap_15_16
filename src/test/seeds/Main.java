package test.seeds;

import test.seeds.controller.Controller;
import test.seeds.model.SeedsSet;
import test.seeds.view.MainView;

import java.awt.*;

/**
 * Created by Luca on 20/05/16.
 */
public class Main {

    private static final Dimension DEFAULT_WINDOW_SIZE = new Dimension(800,765);
    private static final Dimension DEFAULT_WORLD_SIZE = new Dimension(75,65);

    public static void main(String[] args) {

        int viewWidth = 800;
        int viewHeight = 765;
        int worldColumns = 75;
        int worldRows= 65;

        SeedsSet seedsSet = new SeedsSet(DEFAULT_WORLD_SIZE);
        MainView view = new MainView(viewWidth,viewHeight,worldRows,worldColumns);
        Controller controller = new Controller(view, seedsSet);

        view.setListener(controller);
        view.setVisible(true);

    }
}
