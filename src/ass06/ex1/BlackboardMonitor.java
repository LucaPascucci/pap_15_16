package ass06.ex1;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
/**
 * Created by Luca on 23/05/16.
 */
public class BlackboardMonitor  implements Blackboard{

    private Map<String,List<Msg>> blackboardContent;

    public BlackboardMonitor(){
        this.blackboardContent = new HashMap<>();
    }

    @Override
    public void post(String tag, Msg msg) {

    }

    @Override
    public Msg take(String tag) {
        return null;
    }

    @Override
    public Optional<Msg> takeIfPresent(String tag) {
        return null;
    }

    @Override
    public Msg read(String tag) {
        return null;
    }

    @Override
    public Optional<Msg> readIfPresent(String tag) {
        return null;
    }
}
