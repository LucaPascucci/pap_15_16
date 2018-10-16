package ass08;

import pap.ass08.pos.Flag;
import pap.ass08.pos.P2d;
import pap.ass08.pos.PosSensor;
import rx.Observable;

public class TestPosAndHBObservable {

    public static void main(String[] args) {

        System.out.println("Creating POS sensor.");
        PosSensor posSensor = new PosSensor();
        Flag flag = new Flag();

        System.out.println("Creating POS and HB observable.");
        /* making a stream generating a value every 100 ms */

        Observable<Integer> HBobs = new HeartbeatStream(100,flag).makeObservable();
        Observable<P2d> posObs = posSensor.makeObservable(100, flag);

        System.out.println("Creating zipped observable.");

        Observable<TrackBeatData> zippedObs = Observable.zip(HBobs,posObs, (hb_data,pos_data) -> new TrackBeatData(hb_data,pos_data));

        System.out.println("Observing for 10 secs.");

        /* observing the stream for 10000 ms */

        long t0 = System.currentTimeMillis();
        zippedObs.subscribe((p) -> {
                    System.out.println("> " + p);
                    if (System.currentTimeMillis() - t0 > 10000){
                        flag.set();
                    }
                },(Throwable t) -> {},
                () -> {
                    System.out.println("done.");
                });

        try {
            flag.waitSet();
        } catch (InterruptedException ignored){}

        //System.out.println("Done.");
        System.exit(0);

    }
}
