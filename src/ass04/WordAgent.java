package ass04;

import static ass04.TextLib.Color;
import java.util.Random;

/**
 * Created by Luca on 19/04/16.
 */

//TODO commentare
public class WordAgent extends Thread {

    private static final Random RANDOM = new Random();
    private final String word;
    private final TextLib world;
    private final Color color;
    private final String cleaner;
    private final long wait_sleep;
    private final int max_X;
    private final int max_Y;
    private int pos_X;
    private int pos_Y;
    private int step_X;
    private int step_Y;

    public WordAgent (String text, TextLib world, int max_x, int max_y){
        this.wait_sleep = this.randomBetweenTwoNumbers(50,200); //valore random tra 50 e 200
        this.word = text;
        this.world = world;
        this.color = Color.values()[RANDOM.nextInt(Color.values().length)];
        this.max_X = max_x;
        this.max_Y = max_y;
        this.pos_X = this.randomBetweenTwoNumbers(2,this.max_X - this.word.length());
        this.pos_Y = this.randomBetweenTwoNumbers(2,this.max_Y - 1);
        this.step_X = Math.random() < 0.5? 1 : -1;
        this.step_Y = Math.random() < 0.5? 1 : -1;
        this.cleaner = this.word.replaceAll(".", " ");
    }

    @Override
    public void run() {
        super.run();
        try {
            while (true){
                this.world.writeAt(this.pos_X,this.pos_Y,this.word,this.color);
                sleep(this.wait_sleep);
                updatePos();
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private synchronized void updatePos(){
        this.world.writeAt(this.pos_X,this.pos_Y, this.cleaner);
        this.pos_X += this.step_X;
        this.pos_Y += this.step_Y;
        this.applyConstraints();
    }

    //TODO sistemare impatti con i bordi Y
    private void applyConstraints(){
        if (this.pos_X + this.word.length() > this.max_X){
            this.pos_X = this.max_X - this.word.length();
            this.step_X = -this.step_X;
        } else if (this.pos_X < 2){
            this.pos_X = 2;
            this.step_X = -this.step_X;
        } else if (this.pos_Y > this.max_Y - 2){
            this.pos_Y = this.max_Y - 2;
            this.step_Y = -this.step_Y;
        } else if (this.pos_Y < 2){
            this.pos_Y = 2;
            this.step_Y = -this.step_Y;
        }
    }

    private int randomBetweenTwoNumbers(int min, int max){
        return RANDOM.nextInt(max - min) + min;
    }
}
