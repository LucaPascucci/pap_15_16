package ass07.tasks.service;

import ass07.tasks.controller.WinnerFlag;
import ass07.tasks.model.Model;
import ass07.tasks.model.PlayerData;

import java.util.concurrent.Callable;

/**
 * Created by Luca on 28/05/16.
 */
//Taks che rappresenta un player
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
        /*
         * se il suggerimento è 0 allora ha trovato il numero esatto
         * se non è gia stato decretato un vincitore imposto il numero del player corrispondente a questo task
         */
        if (hint == 0 && !this.winnerFlag.getValue()) {
            this.winnerFlag.setWinner(this.playerData.getPlayerNumber());
        } else {
            //altrimenti utilizzo il suggerimento per modificare margine superiore o inferiore
            this.playerData.receiveHint(hint);
        }
        return this.playerData;
    }
}
