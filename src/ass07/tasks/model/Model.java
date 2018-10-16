package ass07.tasks.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * Created by Luca on 27/05/16.
 */

public class Model {

    private int playersNumber;
    private int gameTurn;
    private int magicNumber;
    private int max;
    private int min;
    private List<PlayerData> playerDataList; //Lista tentativi (di un solo turno) dei giocatori. Li devo memorizzare per dare consistenza in caso di start e stop del gioco

    public Model (int nPlayers, int max, int min){

        this.playersNumber = nPlayers;
        this.max = max;
        this.min = min;
        this.prepareGame();
    }

    public List<PlayerData> getPlayers(){
        return this.playerDataList;
    }

    public int getGameTurn(){
        return this.gameTurn;
    }

    public int getMagicNumber(){
        return this.magicNumber;
    }

    public void prepareGame(){
        this.gameTurn = 0;
        this.magicNumber = ThreadLocalRandom.current().nextInt(this.min, this.max + 1); //Genero il numero da indovinare

        //Creazione dei giocatori
        this.playerDataList = new ArrayList<>();
        IntStream.range(0,this.playersNumber).forEach(i -> this.playerDataList.add(new PlayerData(i+1,this.max,this.min)));
    }

    //Aggiorna la lista di giocatori
    public void updatePlayers(List<PlayerData> updateList){
        this.playerDataList = updateList;
    }

    /*
     * Effettua la verifica del tentativo di un player di indovinare il numero
     * -1 numero da indovinare minore
     *  0 stesso numero
     *  1 numero maggiore
     */
    public int guessNumber(int number){
        return Integer.compare(this.magicNumber,number);
    }

    public void incTurn(){
        this.gameTurn++;
    }


}
