package ass07.tasks.service;

import ass07.tasks.controller.WinnerFlag;
import ass07.tasks.model.Model;
import ass07.tasks.model.PlayerData;

import java.util.concurrent.Callable;

/**
 * Created by Luca on 28/05/16.
 */
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

        if (!this.winnerFlag.getValue()) {
            int hint = this.model.attemptNumber(this.playerData.getNextAttempt());
            if (hint == 0) {
                this.winnerFlag.setWinner(this.playerData.getPlayerNumber());
            } else {
                this.playerData.receiveHint(hint);
            }
        }
        return this.playerData;
    }
}
