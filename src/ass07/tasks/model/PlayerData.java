package ass07.tasks.model;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Luca on 28/05/16.
 */
public class PlayerData {

    private int playerNumber;
    private int max;
    private int min;
    private int lastNumber;

    public PlayerData(int playerNumber, int max, int min){
        this.playerNumber = playerNumber;
        this.max = max;
        this.min = min;
    }

    public int getPlayerNumber(){
        return this.playerNumber;
    }

    public int getNextAttempt(){
        this.lastNumber = ThreadLocalRandom.current().nextInt(this.min, this.max + 1);
        return this.lastNumber;
    }

    public void receiveHint(int hint){
        if (hint < 0){
            this.max = this.lastNumber;
        } else {
            this.min = this.lastNumber;
        }
    }

    @Override
    public String toString() {
        return "Player-" + this.playerNumber + " attempt: " + this.lastNumber + " -> Range: " + this.max + " / " + this.min;

    }
}
