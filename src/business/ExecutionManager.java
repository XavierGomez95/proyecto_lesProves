package business;

import business.player.Player;
import business.trial.Trial;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class ExecutionManager {
    private List<Execution> executions;
    private Execution currentExecution;//la execution que estamos usando


    public ExecutionManager(List<Execution> executions) {
        this.executions = executions;
    }

    /**
     *
     * @return
     */
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


    /**
     *
     * @param names
     */
    public void createPlayers(List<String> names) {
        currentExecution.createPlayers(names);
    }

    /**
     *
     * @param t
     * @return
     */
    public List<String> start(Trial t) {
        List<Player> players = currentExecution.getPlayers();
        List<Work> list = new ArrayList<>();
        for (Player player : players) {
            Work w = new Work(player, t);
            Thread thread = new Thread(w);
            thread.start();
            list.add(w);
        }
        return getInfo(list);
    }

    /**
     *
     * @param works
     * @return
     */
    public List<String> getInfo(List<Work> works) {
        List<String> data = new ArrayList<>();
        for (Work w : works) {
            //data.add(w.getInfo());
        }
        return data;
    }

    /**
     *
     * @return
     */
    public int getNumTrial() {
        return currentExecution.getCurrentTrialExecution();
    }

    /**
     * Add +1 to {@link Execution} current trial
     */
    public void addNumTrial() {
        currentExecution.addCurrentTrialExecution();
    }
}
