package ass08;

import pap.ass08.pos.Flag;
import rx.Observable;

/**
 * Created by Luca on 22/06/16.
 */
public class HeartbeatStream {

    private int period;
    private Flag flag;

    public HeartbeatStream(int period, Flag flag){
        this.period = period;
        this.flag = flag;
    }

    //implementazione observable
    public Observable<Integer> makeObservable() {

        HeartbeatSensor heartbeatSensor = new HeartbeatSensor();
        return Observable.create((s) -> {
            (new Thread(() -> {
                while(!this.flag.isSet()) {
                    s.onNext(heartbeatSensor.getCurrentValue());
                    try {
                        Thread.sleep((long)this.period);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                s.onCompleted();
            })).start();
        });
    }
}
