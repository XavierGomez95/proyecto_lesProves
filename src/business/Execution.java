package business;

import business.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Execution {
    private int year;
    private int currentExecution;
    private ArrayList<Player> players;

    public Execution(int year, int currentExecution, ArrayList<Player> players) {
        this.year = year;
        this.currentExecution = currentExecution;
        this.players = players;
    }

    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(year).append(",").append(currentExecution).append(",[");
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

    public static Execution fromLine(String line) {
        int year = Integer.parseInt(line.split(",")[0]);
        int currentExecution = Integer.parseInt(line.split(",")[1]);
        String stringPlayers = line.substring(line.indexOf("["), line.indexOf("]")).substring(1);

        return new Execution(year, currentExecution, Player.fromLine(stringPlayers));
    }


}
