package lab06.syncsum;

/**
 * Created by Luca on 23/05/16.
 */
public class SyncAdderMonitor implements SyncAdder{

    private int dataA, dataB;
    private boolean avaiableA, avaiableB;

    public SyncAdderMonitor(){
        this.avaiableA = this.avaiableB = false;
    }

    @Override
    public synchronized void setDataA(int value) {

        try {
            while (this.avaiableA) {
                wait();
            }

            this.dataA = value;
            this.avaiableA = true;
            if (this.avaiableA && this.avaiableB) {
                notifyAll();
            }
        } catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public synchronized void setDataB(int value) {

        try {
            while (this.avaiableB) {
                wait();
            }

            this.dataB = value;
            this.avaiableB = true;
            if (this.avaiableA && this.avaiableB) {
                notifyAll();
            }
        } catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public synchronized int getSum() {

        try {
            while (!(this.avaiableA && this.avaiableB)) {
                wait();
            }
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }

        this.avaiableA = this.avaiableB = false;
        notifyAll();
        return (this.dataA + this.dataB);
    }
}
