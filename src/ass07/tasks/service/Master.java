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
    private static final long SLEEP_TIME = 500;

    private MainView view;
    private Model model;
    private TurnFlag turnFlag;
    private WinnerFlag winnerFlag;
    private ExecutorService executor;
    private List<Future<PlayerData>> futureList;

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
            while (this.turnFlag.getValue()){
                this.model.incTurn();
                this.futureList = new ArrayList<>();
                this.executor = Executors.newFixedThreadPool(CORES);
                long startTime = System.nanoTime();
                for (PlayerData playerData: this.model.getPlayers()){
                    this.futureList.add(this.executor.submit(new PlayerTask(playerData,this.model,this.winnerFlag)));
                }

                List<PlayerData> result = new ArrayList<>();
                for (Future<PlayerData> future : futureList) {
                    if (future.get() != null)
                        result.add(future.get());
                }
                this.executor.shutdown(); //blocca la possibilità di aggiungere nuovi task ed avvia la terminazione del ExecutorService
                this.executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS); // aspetto che tutti i task siano stati completati

                this.view.updateView(result,this.model.getGameTurn(),(System.nanoTime() - startTime)/1000);
                this.model.updatePlayers(result);

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
