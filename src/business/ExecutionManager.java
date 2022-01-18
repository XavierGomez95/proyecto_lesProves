package business;

import business.player.Player;
import business.trial.BudgedRequest;
import business.trial.Trial;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class ExecutionManager {
    private List<Execution> executions;
    private Execution currentExecution;//la execution que estamos usando
    private List<Trial> trialsExecution;

    public ExecutionManager(List<Execution> executions) {
        this.executions = executions;
    }

    public boolean checkCurrentYear() {
        boolean exists = false;
        for (Execution execution : executions) {
            if (execution.getYear() == Year.now().getValue()) {
                currentExecution = execution;
                exists = true;
            }
        }
        if (!exists) {
            currentExecution = new Execution(Year.now().getValue());
            executions.add(currentExecution);
        }

        return exists;
    }


    public void createPlayers(List<String> names) {
        currentExecution.createPlayers(names);
    }

    public void start(List<Player> players, Trial t) {
        //llama a x threads dependiendo de los players , falta saber como averigua que player es cada uno para puntuar
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            System.out.println(i + "num players:" + players.size()); // TEMPORAL
            Thread thread = new Thread(new Work(players.get(i), t));
            list.add(thread);
            thread.start();
        }
    }

    public boolean isBudgedRequest(Trial t) {
        return t instanceof BudgedRequest;
    }


}
