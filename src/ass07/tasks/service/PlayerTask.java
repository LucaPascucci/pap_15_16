package ass07.tasks.service;

import ass07.tasks.monitor.WinnerFlag;
import ass07.tasks.model.Model;
import ass07.tasks.model.PlayerData;
import ass07.tasks.view.MainView;

import java.util.concurrent.Callable;

/**
 * Created by Luca on 28/05/16.
 */
//Task che rappresenta un player
public class PlayerTask implements Callable<PlayerData>{

    private PlayerData playerData;
    private Model model;
    private WinnerFlag winnerFlag;
    private MainView view;

    public PlayerTask (PlayerData data, Model model,WinnerFlag winnerFlag, MainView view){
        this.playerData = data;
        this.model = model;
        this.winnerFlag = winnerFlag;
        this.view = view;
    }

    @Override
    public PlayerData call() throws Exception {

        //Prelevo il nuovo numero random generato dal playet confrontandolo al numero da trovare
        int hint = this.model.attemptNumber(this.playerData.getNextAttempt());

        //Stampo a video il tentativo (creo un nuovo oggetto PlayerData per fare in modo di avere i margini di inzio turno
        //che altrimenti verrebbero subito modificati dal tasks corrente
        this.view.updatePlayerAttempt(new PlayerData(this.playerData));

        //invio al monitor il suggerimento per controllare se è il vincitore
        this.winnerFlag.isWinner(hint,this.playerData.getPlayerNumber());
        //Aggiorno i limiti del player in base al suggerimento
        this.playerData.receiveHint(hint);

        return this.playerData;
    }
}
