package business;

import business.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Execution {
    private int year;
    private int currentTrialExecution;
    private ArrayList<Player> players;

    /**
     * Constructor to create saved Executions.
     *
     * @param year                  to identify the edition
     * @param currentTrialExecution to identify the next trial execution
     * @param players               list
     */
    public Execution(int year, int currentTrialExecution, ArrayList<Player> players) {
        this.year = year;
        this.currentTrialExecution = currentTrialExecution;
        this.players = players;
    }

    public Execution(int year) {
        this.year = year;
    }

    /**
     * {@link Player#createNewPlayers(List names)}
     *
     * @param names of all players
     */
    public void createPlayers(List<String> names) {
        players = Player.createNewPlayers(names);
    }

    /**
     * @return the year of the current Execution.
     */
    public int getYear() {
        return year;
    }

    /**
     * @return players list
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * @return currentTrialExecution
     */
    public int getCurrentTrialExecution() {
        return currentTrialExecution;
    }

    /**
     * add +1 to currentTrialExecution
     */
    public void addCurrentTrialExecution() {
        this.currentTrialExecution++;
    }


    /**
     * Sets the currentTrialExecution to -1
     */
    public void finish() {
        currentTrialExecution = -1;
    }

    /**
     * if the currentTrialExecution is -1  the execution has finished.
     *
     * @return if the execution has finished.
     */
    public boolean isFinished() {
        return currentTrialExecution == -1;
    }

    /**
     * @return String line to save in csv
     */
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(year).append(",").append(currentTrialExecution).append(",[");
        for (int i = 0; i < players.size(); i++) {
            if (i == 0) {
                sb.append(players.get(i).getInfo());
            } else {
                sb.append(",").append(players.get(i).getInfo());
            }
        }
        sb.append("]");

        return sb.toString();
    }

    /**
     * @param line from csv file
     * @return Object Execution to save it in the List.
     */
    public static Execution fromLine(String line) {
        int year = Integer.parseInt(line.split(",")[0]);
        int currentExecution = Integer.parseInt(line.split(",")[1]);
        String stringPlayers = line.substring(line.indexOf("["), line.indexOf("]")).substring(1);

        return new Execution(year, currentExecution, Player.fromLine(stringPlayers));
    }
}
