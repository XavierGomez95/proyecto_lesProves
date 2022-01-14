package business.player;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int pi;
    private boolean alive;
    private String type;

    public Player(String name, int pi) {
        this.name = name;
        this.pi = pi;
        this.alive = true;
        this.type = "engineer";
    }

    public Player(String name, int pi, boolean alive, String type) {
        this.name = name;
        this.pi = pi;
        this.alive = alive;
        this.type = type;
    }

    public void subtractPoints (int points) {
        switch (type) {
            case "engineer" -> this.pi -= points;
            case "master", "doctor" -> this.pi -= (points / 2);
        }
    }

    public void addPoints (int points) {
        switch (type) {
            case "engineer", "master" -> this.pi += points;
            case "doctor" -> this.pi += (points * 2);
        }
    }

    public void upGrade () {
        this.pi = 5;
        switch (type) {
            case "engineer" -> type = "master";
            case "master" -> type = "doctor";
        }
    }

    public void killPlayer () {
        this.alive = false;
    }

    /**
     * To read from csv
     *
     * @param s line with all Player Objects saved in csv
     * @return ArrayList of Players
     */
    public static ArrayList<Player> fromLine(String s) {
        String[] arrayPlayers = s.split("}");
        //String p = string.substring(string.indexOf("{"), string.indexOf("}"));
        ArrayList<Player> players = new ArrayList<>();
        for (String player : arrayPlayers) {
            //falta comprobar els punts per saber quin tipues de player és
            player = player.substring(1);
            players.add(new Player(player.split(",")[0], Integer.parseInt(player.split(",")[1])));
        }
        return players;
    }
}
