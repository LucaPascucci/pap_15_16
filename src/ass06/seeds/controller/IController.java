package ass06.seeds.controller;


/**
 * Created by Luca on 20/05/16.
 */
public interface IController {

    void started();

    void stopped();

    void reset();

    void manageSeed(int r, int c, boolean value);
}
