package business;

import business.player.Player;
import business.trial.Trial;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class ExecutionManager {
    private List<Execution> executions;
    private Execution currentExecution; // The execution we are using


    public ExecutionManager(List<Execution> executions) {
        this.executions = executions;
    }

    /**
     * @return if it exists true or false.
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
     * {@link Execution#createPlayers(List names)}.
     *
     * @param names of all players
     */
    public void createPlayers(List<String> names) {
        currentExecution.createPlayers(names);
    }

    /**
     * @param t current Trial
     * @return list {@link #getData(List works)}
     */
    public List<String> start(Trial t) {
        List<Player> players = currentExecution.getPlayers();
        List<Work> works = new ArrayList<>();
        Thread[] threads = new Thread[players.size()];

        int i = 0;
        for (Player player : players) {
            if (player.isAlive()) {
                Work w = new Work(player, t);
                threads[i] = new Thread(w);
                threads[i++].start();
                works.add(w);
            }
        }

        i = 0;
        for (Player player : players) {
            if (player.isAlive()) {
                if (threads[i] != null) {
                    try {
                        threads[i++].join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return getData(works);
    }

    /**
     * @return String with all players that are upgraded {@link Player#isUpGraded()}
     */
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

    /**
     * @param works list to get the information of each thread.
     * @return list of all the information
     */
    public List<String> getData(List<Work> works) {
        List<String> data = new ArrayList<>();
        for (Work w : works) {
            data.add(w.getInfo());
        }
        return data;
    }

    /**
     * @return {@link Execution#getCurrentTrialExecution()}
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

    /**
     * {@link Execution#isFinished()}
     *
     * @return if the execution is finished or not
     */
    public boolean isFinished() {
        return currentExecution.isFinished();
    }

    /**
     * {@link Execution#finish()}
     */
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
            total += p.getPi();
        }
        return total;
    }

    /**
     * In case the players lose we subtract 2 points to each player.
     *
     * @return list with all information of each player.
     */
    public List<String> subtractPiBudget() {
        ArrayList<Player> players = currentExecution.getPlayers();
        List<String> results = new ArrayList<>();
        for (Player player : players) {
            player.subtractPoints(2);
        }

        for (Player p : players) {
            String info;
            if (p.isTypeEngineer()) {
                info = "\t" + p.getName() + ". " + "PI count: " + p.getPi();
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
     * In case the players win we add half of their current points.
     *
     * @return list with all information of each player.
     */
    public List<String> addPiBudget() {
        ArrayList<Player> players = currentExecution.getPlayers();
        List<String> results = new ArrayList<>();
        int[] points = getPiPerPlayer(players);
        for (int i = 0; i < points.length; i++) {
            if (players.get(i).isAlive()) {
                players.get(i).addPoints(points[i] / 2);
                if (players.get(i).isTypeEngineer()) {
                    results.add("\t" + players.get(i).getName() + ". " + "PI count: " + players.get(i).getPi());
                } else {
                    results.add("\t" + players.get(i).getName() + ", " + players.get(i).getType() + ". PI count: " + players.get(i).getPi());
                }
                upgradeBudgetPlayer(players.get(i));
            }
        }
        return results;
    }

    /**
     * For BudgetRequest Trial, checks if the {@link Player#isUpgradeable()} and {@link Player#upGrade()} it.
     *
     * @param p player
     */
    private void upgradeBudgetPlayer(Player p) {
        if (p.isUpgradeable()) {
            p.upGrade();
        }
    }

    /**
     * @param players list
     * @return points of each player in order
     */
    private int[] getPiPerPlayer(List<Player> players) {
        int[] points = new int[players.size()];
        for (int i = 0; i < points.length; i++) {
            points[i] = players.get(i).getPi();
        }
        return points;
    }

    /**
     * @return true if there are players alive.
     */
    public boolean checkWin() {
        ArrayList<Player> players = currentExecution.getPlayers();
        boolean playersAlive = false;
        for (Player p : players) {
            if (p.isAlive()) {
                playersAlive = true;
                break;
            }
        }
        return playersAlive;
    }

    /**
     * Interrupt the threads.
     */
    public void finishThreads() {
        List<Player> players = currentExecution.getPlayers();
        int size = players.size();
        for (int i = 0; i < size; i++) {
            if (!Thread.currentThread().isInterrupted()) Thread.currentThread().interrupt();
        }
    }
}
