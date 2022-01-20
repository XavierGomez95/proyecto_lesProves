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
        List<Work> works = new ArrayList<>();
        for (Player player : players) {
            if (player.isAlive()) {
                Work w = new Work(player, t);
                Thread thread = new Thread(w);
                thread.start();
                works.add(w);
            }
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return getInfo(works);
    }

    public String checkUpgrade() {
        List<Player> players = currentExecution.getPlayers();
        StringBuilder lastData = new StringBuilder();
        for (Player p : players) {
            if (p.isUpGraded()) {
                lastData.append(p.getName()).append(" is now a ").append(p.getType()).append(" (with 5 PI). ");
            }
        }
        return lastData.toString();
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

    public List<String> subtractPiBudget() {
        ArrayList<Player> players = currentExecution.getPlayers();
        List<String> results = new ArrayList<>();
        for (Player player : players) {
            player.subtractPoints(2);
        }

        for (Player p : players) {
            String info = "";
            if (p.getType().equals("engineer")) {
                info = "\t" + p.getName() + ". " + " PI count: " + p.getPi();
            } else {
                info = "\t" + p.getName() + ", " + p.getType() + ". PI count: " + p.getPi();
            }
            if (p.isKillable()) {
                p.killPlayer();
                info += " - Disqualified!";
            }
            results.add(info);
        }

        return results;
    }

    /**
     * In case the players win we add half of their current points
     */
    public List<String> addPiBudget() {
        ArrayList<Player> players = currentExecution.getPlayers();
        List<String> results = new ArrayList<>();
        StringBuilder lastString = new StringBuilder();
        int[] points = getPiPerPlayer(players);
        for (int i = 0; i < points.length; i++) {
            if (players.get(i).isAlive()) {
                players.get(i).addPoints(points[i] / 2);
                results.add(System.lineSeparator());
                if (players.get(i).getType().equals("engineer")) {
                    results.add("\t" + players.get(i).getName() + ". " + " PI count: " + players.get(i).getPi());
                } else {
                    results.add("\t" + players.get(i).getName() + ", " + players.get(i).getType() + ". PI count: " + players.get(i).getPi());
                }

                lastString.append(checkUpgradeBudgetRequest(players.get(i)));
            }
            results.add(lastString.toString());

        }
        return results;
    }

    /**
     * For BudgetRequest Trial, checks if the {@link Player#isUpgradeable()} and {@link Player#upGrade()} it.
     *
     * @param p player
     * @return data of the player upgraded
     */
    private String checkUpgradeBudgetRequest(Player p) {
        if (p.isUpgradeable()) {
            p.upGrade();
            return p.getName() + "is now a " + p.getType() + " (with 5 PI)";
        }
        return "";

    }

    private int[] getPiPerPlayer(List<Player> players) {
        int[] points = new int[players.size()];
        for (int i = 0; i < points.length; i++) {
            points[i] = players.get(i).getPi();
        }
        return points;
    }


    public boolean checkWin() {
        ArrayList<Player> players = currentExecution.getPlayers();
        boolean playersAlive = false;
        for (Player p : players) {
            if (p.isAlive()) {
                playersAlive = true;
            }
        }
        return playersAlive;
    }
}
