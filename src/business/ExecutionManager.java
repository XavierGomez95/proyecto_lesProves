package business;

import business.player.Player;
import business.trial.Trial;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ExecutionManager {
    private List<Execution> executions;
    private Execution currentExecution;//la execution que estamos usando


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

    public List<String> start(Trial t) {
        List<Player> players = currentExecution.getPlayers();
        List<Work> list = new ArrayList<>();
        for (Player player : players) {
            if (player.isAlive()) {
                Work w = new Work(player, t);
                Thread thread = new Thread(w);
                thread.start();
                list.add(w);
            }
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return getInfo(list);
    }


    public List<String> getInfo(List<Work> works) {
        List<String> data = new ArrayList<>();
        for (Work w : works) {
            data.add(w.getInfo());
        }
        return data;
    }

    public int getNumTrial() {
        return currentExecution.getCurrentTrialExecution();
    }

    /**
     * Add +1 to {@link Execution} current trial
     */
    public void addNumTrial() {
        currentExecution.addCurrentTrialExecution();
    }

    /**
     * {@link Execution#isFinished()}
     *
     * @return if the execution is finished or not
     */
    public boolean isFinished() {
        return currentExecution.isFinished();
    }

    public void finishExecution() {
        currentExecution.finish();
    }


    /**
     * @return total PI of all players for {@link business.trial.BudgetRequest#calculateFormula(int totalPI)}
     */
    public int getTotalPI() {
        ArrayList<Player> players = currentExecution.getPlayers();
        int total = 0;
        for (Player p : players) {
            //total+=p.getPI();
        }
        return total;
    }

    /**
     * In case the players win we add half of their current points
     */
    public String addPiBudget() {
        ArrayList<Player> players = currentExecution.getPlayers();
        StringBuilder sb = new StringBuilder();
        int[] points = getPipPerPlayer(players);
        for (int i = 0; i < points.length; i++) {
            if (players.get(i).isAlive()) {
                players.get(i).addPoints(points[i] / 2);
                sb.append(System.lineSeparator());
                if (true) {//si el addpoint es true significa que hay upgrade
                    sb.append(players.get(i).getName()).append(", ").append(players.get(i).getType());
                    sb.append(" PI count: ").append(players.get(i).getPi());

                } else {
                    sb.append(players.get(i).getName()).append(". ");
                    sb.append(" PI count: ").append(players.get(i).getPi());
                }
            }

        }
        return sb.toString();
    }

    private int[] getPipPerPlayer(List<Player> players) {
        int[] points = new int[players.size()];
        for (int i = 0; i < points.length; i++) {
            points[i] = players.get(i).getPi();
        }
        return points;
    }

    public void subtractPiBudget() {
        ArrayList<Player> players = currentExecution.getPlayers();
        for (int i = 0; i < players.size(); i++) {
            players.get(i).subtractPoints(2);
        }
    }

}
