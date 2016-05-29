package ass07.threads.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Luca on 27/05/16.
 */

public class Model {

    private int playersNumber;
    private int gameTurn;
    private int magicNumber;
    private int max;
    private int min;
    private List<PlayerData> playerDataList;

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
        //Genero il numero da indovinare
        this.magicNumber = ThreadLocalRandom.current().nextInt(this.min, this.max + 1);
        //creo i giocatori
        this.playerDataList = new ArrayList<>();
        for (int i = 1; i <= this.playersNumber; i++){
            this.playerDataList.add(new PlayerData(i,this.max,this.min));
        }
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
    public int attemptNumber(int number){
        return Integer.compare(this.magicNumber,number);
    }

    public void incTurn(){
        this.gameTurn++;
    }


}
