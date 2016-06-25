package ass08.trackBeatRX;

import ass08.trackBeatRX.model.Model;
import ass08.trackBeatRX.view.MainView;
import ass08.trackBeatRX.controller.Controller;

import java.awt.Dimension;

/**
 * Created by Luca on 09/06/16.
 */
public class Main {

    private static final Dimension DEFAULT_WINDOW_SIZE = new Dimension(850,750);
    private static final int HB_TH = 100;
    private static final int SEC_TH = 5;

    public static void main(String[] args) {

        MainView view = new MainView(DEFAULT_WINDOW_SIZE,HB_TH,SEC_TH);
        Model model = new Model(HB_TH,SEC_TH);
        Controller controller = new Controller(view,model);
        view.setListener(controller);
        view.setVisible(true);

    }
}
