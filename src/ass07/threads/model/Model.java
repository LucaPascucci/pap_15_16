package ass07.threads.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * Created by Luca on 27/05/16.
 */

public class Model {

    private int playersNumber;
    private int magicNumber;
    private int max;
    private int min;
    private Map<Integer, PlayerData> playerDataMap; //<NumeroGiocatore,DatiGiocatore> mappa che memorizza per ogni giocatore il tentativo del turno corrente

    public Model (int nPlayers, int max, int min){
        this.playersNumber = nPlayers;
        this.max = max;
        this.min = min;
        this.prepareGame();
    }

    public int getPlayersNumber(){
        return this.playersNumber;
    }

    public int getMagicNumber(){
        return this.magicNumber;
    }

    public Map<Integer,PlayerData> getPlayerDataMap(){
        return this.playerDataMap;
    }

    public void prepareGame(){
        //Genero il numero da indovinare
        this.magicNumber = ThreadLocalRandom.current().nextInt(this.min, this.max + 1);

        //creo i giocatori
        this.playerDataMap = new ConcurrentHashMap<>();
        IntStream.range(0,this.playersNumber).forEach(i -> this.playerDataMap.put(i+1,new PlayerData(i+1,this.max,this.min)));
        /*for (int i = 1; i <= this.playersNumber; i++){
            this.playerDataMap.put(i,new PlayerData(i,this.max,this.min));
        }*/
    }

    /*
     * Effettua la verifica del tentativo di un player di indovinare il numero
     * -1 numero da indovinare minore
     *  0 stesso numero
     *  1 numero da indovinare maggiore
     */
    public int guessNumber(int number){
        return Integer.compare(this.magicNumber,number);
    }

    //Aggiorna i dati (tentativo del turno) di un giocatore
    public void updatePlayerData(PlayerData playerData){
        this.playerDataMap.put(playerData.getPlayerNumber(),playerData);
    }

}

/**
 * ConcurrentHashMap
 *
 * - You should use ConcurrentHashMap when you need very high concurrency in your project.
 * - Reads can happen very fast while write is done with a lock.
 * - There is no locking at the object level.
 * - The locking is at a much finer granularity at a hashmap bucket level.
 * - ConcurrentHashMap doesn’t throw a ConcurrentModificationException if one thread tries to modify it while another is iterating over it.
 * - ConcurrentHashMap uses multitude of locks.
 *
 *
 * SynchronizedHashMap
 *
 * - Synchronization at Object level.
 * - Every read/write operation needs to acquire lock.
 * - Locking the entire collection is a performance overhead.
 * - This essentially gives access to only one thread to the entire map & blocks all the other threads.
 * - It may cause contention.
 * - SynchronizedHashMap returns Iterator, which fails-fast on concurrent modification.
 */
