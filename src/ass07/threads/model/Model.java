package ass07.threads.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Luca on 27/05/16.
 */

public class Model {

    private int playersNumber;
    private int magicNumber;
    private int max;
    private int min;
    private Map<Integer, PlayerData> playerDataMap;

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
        for (int i = 1; i <= this.playersNumber; i++){
            this.playerDataMap.put(i,new PlayerData(i,this.max,this.min));
        }
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

    //aggiorna i dati di un giocatore
    public void updatePlayerData(int playerNumber, PlayerData playerData){
        this.playerDataMap.put(playerNumber,playerData);
    }

}

/*
ConcurrentHashMap

- You should use ConcurrentHashMap when you need very high concurrency in your project.
- It is thread safe without synchronizing the whole map.
- Reads can happen very fast while write is done with a lock.
- There is no locking at the object level.
- The locking is at a much finer granularity at a hashmap bucket level.
- ConcurrentHashMap doesn’t throw a ConcurrentModificationException if one thread tries to modify it while another is iterating over it.
- ConcurrentHashMap uses multitude of locks.

SynchronizedHashMap

- Synchronization at Object level.
- Every read/write operation needs to acquire lock.
- Locking the entire collection is a performance overhead.
- This essentially gives access to only one thread to the entire map & blocks all the other threads.
- It may cause contention.
- SynchronizedHashMap returns Iterator, which fails-fast on concurrent modification.
 */