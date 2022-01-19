package business;

import business.player.Player;
import business.trial.Trial;

import java.util.ArrayList;
import java.util.List;

public class Execution {
    private int year;
    private int currentTrialExecution;
    private ArrayList<Player> players;

    public Execution(int year, int currentTrialExecution, ArrayList<Player> players) {
        this.year = year;
        this.currentTrialExecution = currentTrialExecution;
        this.players = players;
    }

    public Execution(int year) {
        this.year = year;
    }


    public void createPlayers(List<String> names) {
        players = Player.createNewPlayers(names);
    }

    /**
     *
     * @return the year of the current Execution.
     */
    public int getYear() {
        return year;
    }

    /**
     *
     * @return
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     *
     * @return
     */
    public int getCurrentTrialExecution() {
        return currentTrialExecution;
    }

    /**
     *
     */
    public void addCurrentTrialExecution() {
        this.currentTrialExecution++;
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
