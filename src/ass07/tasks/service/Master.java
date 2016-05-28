package ass07.tasks.service;

import ass07.tasks.view.MainView;
import ass07.tasks.controller.TurnFlag;
import ass07.tasks.controller.WinnerFlag;
import ass07.tasks.model.Model;
import ass07.tasks.model.PlayerData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Luca on 28/05/16.
 */
public class Master extends Thread{

    private static final int CORES = Runtime.getRuntime().availableProcessors() + 1;
    private static final long SLEEP_TIME = 400;

    private MainView view;
    private Model model;
    private TurnFlag turnFlag;
    private WinnerFlag winnerFlag;

    public Master(Model model, MainView view, TurnFlag flag, WinnerFlag winnerflag){
        this.view = view;
        this.model = model;
        this.turnFlag = flag;
        this.winnerFlag = winnerflag;
    }

    @Override
    public void run() {
        super.run();

        try {
            //Cicla finchè non viene interrotta l'esecuzione oppure si trovi un vincitore
            while (this.turnFlag.getValue()){
                this.model.incTurn();
                List<Future<PlayerData>> futureList = new ArrayList<>();
                ExecutorService executor = Executors.newFixedThreadPool(CORES);
                long startTime = System.nanoTime();

                //crea tanti task quanti sono i players
                for (PlayerData playerData: this.model.getPlayers()){
                    futureList.add(executor.submit(new PlayerTask(playerData,this.model,this.winnerFlag)));
                }

                //Prelevo i risultati ottenuti dai vari task
                List<PlayerData> result = new ArrayList<>();
                for (Future<PlayerData> future : futureList) {
                    if (future.get() != null)
                        result.add(future.get());
                }
                executor.shutdown(); //blocca la possibilità di aggiungere nuovi task ed avvia la terminazione del ExecutorService
                executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS); // aspetto che tutti i task siano stati completati

                //Aggiorno model e view
                this.view.updateView(result,this.model.getGameTurn(),(System.nanoTime() - startTime)/1000);
                this.model.updatePlayers(result);

                //Se è stato trovato un vincitore
                if (this.winnerFlag.getValue()){
                    this.turnFlag.setValue(false);
                    this.view.updateWinner(this.winnerFlag.getWinner(),result.size());
                }

                Thread.sleep(SLEEP_TIME);
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
