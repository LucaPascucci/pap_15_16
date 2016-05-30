package ass07.tasks.service;

import ass07.tasks.monitor.WinnerFlag;
import ass07.tasks.model.Model;
import ass07.tasks.model.PlayerData;

import java.util.concurrent.Callable;

/**
 * Created by Luca on 28/05/16.
 */
//Task che rappresenta un player
public class PlayerTask implements Callable<PlayerData>{

    private PlayerData playerData;
    private Model model;
    private WinnerFlag winnerFlag;

    public PlayerTask (PlayerData data, Model model,WinnerFlag winnerFlag){
        this.playerData = data;
        this.model = model;
        this.winnerFlag = winnerFlag;
    }

    @Override
    public PlayerData call() throws Exception {

        //Prelevo il nuovo numero random generato dal playet confrontandolo al numero da trovare
        int hint = this.model.attemptNumber(this.playerData.getNextAttempt());

        //invio al monitor il suggerimento per controllare se è il vincitore
        this.winnerFlag.isWinner(hint,this.playerData.getPlayerNumber());
        //Aggiorno i limiti del player in base al suggerimento
        this.playerData.receiveHint(hint);

        return this.playerData;
    }
}
