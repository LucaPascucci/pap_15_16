package ass06.ex1;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlackboardMonitor implements Blackboard {

    private Map<String, LinkedList<Msg>> blackboard;
    private Lock mutex;
    private Condition tagNotPresent;

    public BlackboardMonitor (){
        this.blackboard = new HashMap<>();
        this.mutex = new ReentrantLock();
        this.tagNotPresent = this.mutex.newCondition();
    }

    @Override
    public void post(String tag, Msg msg) {
        try {
            this.mutex.lock();
            LinkedList<Msg> messages;
            if (this.checkTagOnBlackboard(tag)){
                messages = this.blackboard.get(tag);
            } else {
                messages = new LinkedList<>();
            }
            messages.addLast(msg);
            this.blackboard.put(tag,messages);
            this.tagNotPresent.signalAll();
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public Msg take(String tag) {
        Msg msg = null;
        try {
            this.mutex.lock();
            while (!this.checkTagOnBlackboard(tag)){
                this.tagNotPresent.await();
            }
            msg = this.substantialRemoval(tag);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.mutex.unlock();
        }
        return msg;
    }

    @Override
    public Optional<Msg> takeIfPresent(String tag) {
        Msg msg = null;
        try {
            this.mutex.lock();
            if (this.checkTagOnBlackboard(tag)){
                msg = this.substantialRemoval(tag);
            }
        } finally {
            this.mutex.unlock();
        }
        return msg == null? Optional.empty() : Optional.of(msg);
    }

    @Override
    public Msg read(String tag) {
        Msg msg = null;
        try {
            this.mutex.lock();
            while (!this.checkTagOnBlackboard(tag)){
                this.tagNotPresent.await();
            }
            msg = this.blackboard.get(tag).getFirst();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.mutex.unlock();
        }
        return msg;
    }

    @Override
    public Optional<Msg> readIfPresent(String tag) {
        Msg msg = null;
        try {
            this.mutex.lock();
            if (this.checkTagOnBlackboard(tag)){
                msg = this.blackboard.get(tag).getFirst();
            }
        } finally {
            this.mutex.unlock();
        }
        return msg == null? Optional.empty() : Optional.of(msg);
    }

    private boolean checkTagOnBlackboard (String tag){
        return this.blackboard.containsKey(tag);
    }

    private Msg substantialRemoval (String tag) {
        Msg msg = this.blackboard.get(tag).removeFirst();
        if (this.blackboard.get(tag).isEmpty()){
            this.blackboard.remove(tag);
        }
        return msg;
    }
}
