package business.player;

import java.util.ArrayList;


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

    public void subtractPoints(int points) {
        switch (type) {
            case "engineer" -> this.pi -= points;
            case "master", "doctor" -> this.pi -= (points / 2);
        }
    }

    public void addPoints(int points) {
        switch (type) {
            case "engineer", "master" -> this.pi += points;
            case "doctor" -> this.pi += (points * 2);
        }
    }

    public void upGrade() {
        this.pi = 5;
        switch (type) {
            case "engineer" -> type = "master";
            case "master" -> type = "doctor";
        }
    }

    public void killPlayer() {
        this.alive = false;
    }

    /**
     * To read from csv
     *
     * @param line line with all Player Objects saved in csv
     * @return ArrayList of Players
     */
    public static ArrayList<Player> fromLine(String line) {
        String[] arrayPlayers = line.split("}");
        ArrayList<Player> players = new ArrayList<>();

        for (String player : arrayPlayers) {
            player = player.substring(1);
            player = player.replace("{", "");
            String name = player.split(",")[0];
            int pi = Integer.parseInt(player.split(",")[1]);
            boolean alive = Boolean.parseBoolean(player.split(",")[2]);
            String type = player.split(",")[3];

            players.add(new Player(name, pi, alive, type));
        }
        return players;
    }


    /**
     * Gets info to write in csv files
     *
     * @return String line with all Player information
     */
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        return sb.append("{").append(name).append(",").append(pi).append(",").append(alive).append(",").append(type).append("}").toString();
    }

}
