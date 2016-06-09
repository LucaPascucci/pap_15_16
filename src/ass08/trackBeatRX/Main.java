package ass08.trackBeatRX;

import ass08.trackBeatRX.view.MainView;
import ass08.trackBeatRX.controller.Controller;

import java.awt.Dimension;

/**
 * Created by Luca on 09/06/16.
 */
public class Main {

    private static final Dimension DEFAULT_WINDOW_SIZE = new Dimension(750,800);

    public static void main(String[] args) {

        MainView view = new MainView(DEFAULT_WINDOW_SIZE);
        Controller controller = new Controller(view);
        view.setListener(controller);
        view.setVisible(true);

    }
}
