package ass06.ex1;


import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

/**
 * Created by Luca on 23/05/16.
 */
public class BlackboardImpl implements Blackboard{

    private Map<String,LinkedList<Msg>> blackboardContent;

    public BlackboardImpl(){
        this.blackboardContent = new HashMap<>();
    }

    @Override
    public synchronized void post(String tag, Msg msg) {
        LinkedList<Msg> tag_msgs = this.blackboardContent.get(tag);
        if (tag_msgs == null){ //Tag non presente quindi lista di messaggi vuota
            tag_msgs = new LinkedList<>();
        }
        tag_msgs.addLast(msg);
        this.blackboardContent.put(tag,tag_msgs);
        notifyAll(); //notifico a tutti i thread l'inserimento di un nuovo messaggio
    }

    @Override
    public synchronized Msg take(String tag) {

        //controllo se la lavagna contiene almeno un messaggio corrispondente al tag
        while (!this.blackboardContent.containsKey(tag)){
            try {
                wait(); //nel caso non contenga il tag metto il thread in wait
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //rimuovo il primo messaggio in lista
        Msg msg = this.blackboardContent.get(tag).removeFirst();
        this.cleanContent(tag);
        return msg;
    }

    @Override
    public synchronized Optional<Msg> takeIfPresent(String tag) {
        //controllo se la lavagna contiene almeno un messaggio corrispondente al tag
        if (this.blackboardContent.containsKey(tag)){
            Msg msg = this.blackboardContent.get(tag).removeFirst();
            this.cleanContent(tag);
            return Optional.of(msg);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public synchronized Msg read(String tag) {
        while (!this.blackboardContent.containsKey(tag)){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Msg msg = this.blackboardContent.get(tag).getFirst();
        this.cleanContent(tag);
        return msg;
    }

    @Override
    public synchronized Optional<Msg> readIfPresent(String tag) {
        if (this.blackboardContent.containsKey(tag)){
            Msg msg = this.blackboardContent.get(tag).getFirst();
            this.cleanContent(tag);
            return Optional.of(msg);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Non è necessario utilizzare synchronized dato che viene richiamato in un metodo a sua volta synchronized
     */
    //Rimuove il tag se la lista dei messaggi è vuota
    private synchronized void cleanContent(String tag){
        if (this.blackboardContent.get(tag).isEmpty()){
            this.blackboardContent.remove(tag);
        }
    }
}
