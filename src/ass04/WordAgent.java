package ass04;

import static ass04.TextLib.Color;
import java.util.Random;

/**
 * Created by Luca on 19/04/16.
 */

public class WordAgent extends Thread {

    private static final Random RANDOM = new Random();
    private final String word;
    private final TextLib world;
    private final Color color;
    private final String cleaner;
    private final long wait_sleep; //tempo in millisec di attesa del thread
    private final int max_X;
    private final int max_Y;
    private int pos_X;
    private int pos_Y;
    private int step_X;
    private int step_Y;

    public WordAgent (String text, TextLib world, int max_x, int max_y){
        this.wait_sleep = this.randomBetweenTwoNumbers(50,200);
        this.word = text;
        this.world = world;
        this.color = Color.values()[RANDOM.nextInt(Color.values().length)]; //genera un valore random incluso tra 0 e length-1
        this.max_X = max_x;
        this.max_Y = max_y;
        //genero la posizione della parola
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
                //scrivo la parola su console
                this.world.writeAt(this.pos_X,this.pos_Y,this.word,this.color);
                sleep(this.wait_sleep);
                //dopo un tot di millisecondi copre la parola con una stringa vuota (Evita l'effetto scia)
                this.world.writeAt(this.pos_X,this.pos_Y, this.cleaner);
                //sposto la posizione per il ciclo successivo
                updatePos();
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * Non andava utilizzato il synchronized perchè ogni parola contiene la propria posizione e non c'è condivisione
     */
    /*private synchronized void updatePos(){
        this.pos_X += this.step_X;
        this.pos_Y += this.step_Y;
        this.applyConstraints();
    }*/

    //aggiorna la posizione aumentadola dello step
    private void updatePos(){
        this.pos_X += this.step_X;
        this.pos_Y += this.step_Y;
        this.applyConstraints();
    }

    //controllo per il rimbalzo su ogni lato della console
    private void applyConstraints(){
        if (this.pos_X + word.length() == this.max_X){
            this.step_X = -this.step_X;
        }
        if (this.pos_X == 2){
            this.step_X = -this.step_X;
        }
        if (this.pos_Y == this.max_Y - 2){
            this.step_Y = -this.step_Y;
        }
        if (this.pos_Y == 2){
            this.step_Y = -this.step_Y;
        }
    }


    //genera un valore random compreso tra i due valori
    private int randomBetweenTwoNumbers(int min, int max){
        return RANDOM.nextInt(max - min) + min;
    }
}
