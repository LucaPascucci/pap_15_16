package ass07.threads.service;


import ass07.threads.model.Model;
import ass07.threads.model.PlayerData;
import ass07.threads.monitor.GameMonitor;
import ass07.threads.view.MainView;

/**
 * Created by Luca on 29/05/16.
 */

//Thread che rappresenta un giocatore
public class PlayerWorker extends Thread{

    private static final long SLEEP_TIME = 500;

    private GameMonitor gameMonitor;
    private PlayerData playerData;
    private Model model;
    private MainView view;

    public PlayerWorker(GameMonitor gameMonitor, PlayerData playerData, Model model, MainView view){
        this.gameMonitor = gameMonitor;
        this.playerData = playerData;
        this.model = model;
        this.view = view;
    }

    @Override
    public void run() {
        super.run();
        try {
            while (this.gameMonitor.isActiveGame()){
                Thread.sleep(SLEEP_TIME);

                //this.playerData.clone();
                int turn = this.gameMonitor.getTurn();
                long timeTurn = this.gameMonitor.getTurnTime();
                int hint = this.model.guessNumber(this.playerData.getNextAttempt());
                //Invio il tentativo alla view. Creato un nuovo oggetto di tipo PlayerData per fare in modo
                //di avere i range di inizio turno e non modificati subito dal thread in esecuzione
                this.view.updatePlayerAttempt(new PlayerData(this.playerData), turn, this.model.getPlayersNumber(),timeTurn);

                boolean winner = this.gameMonitor.isWinner(hint); //chiede al monitor se è il vincitore
                this.playerData.receiveHint(hint); //modifica i margini in base al suggerimento

                //aggiorno model
                this.model.updatePlayerData(this.playerData);

                this.gameMonitor.nextTurn(); //chiede al monitor se è concluso il turno (se non sono stati ricevuti tutti i tentativi -> aspetta il prossimo turno)

                //solo se è il thread(player) vincitore aggiorna la view
                if (winner) {
                    this.view.updateWinner(this.playerData.getPlayerNumber(), this.model.getPlayersNumber());
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
