package business.player;

import java.util.ArrayList;
import java.util.List;


public class Player{
    private String name;
    protected int pi;
    private boolean alive;
    private String type;
    private boolean[] upGraded;

    public Player(String name) {
        this.name = name;
        this.pi = 5;
        this.alive = true;
        this.type = "engineer";
        this.upGraded = new boolean[2];
    }

    public Player(String name, int pi, boolean alive, String type) {
        this.name = name;
        this.pi = pi;
        this.alive = alive;
        this.type = type;
    }

    public static ArrayList<Player> createNewPlayers(List<String> names) {
        ArrayList<Player> players = new ArrayList<>();
        for (String name : names) {
            players.add(new Player(name));
        }
        return players;
    }

    public void subtractPoints(int points) {
        switch (type) {
            case "engineer" -> this.pi -= points;
            case "master", "doctor" -> this.pi -= (points / 2);
        }
        if (pi < 0) pi = 0;
    }

    public void addPoints(int points) {
        switch (type) {
            case "engineer", "master" -> this.pi += points;
            case "doctor" -> this.pi += (points * 2);
        }
    }

    // Antes de preguntar: Continue the execution? [yes/no]:
    public String requestStringUpGrade() {
        return name + " is now a " + type + " (with " + pi + " PI). ";
    }

    public void upGrade() {
        this.pi = 5;
        switch (type) {
            case "engineer" -> {
                type = "master";
                upGraded[0] = true;
            }
            case "master" -> {
                type = "doctor";
                upGraded[1] = true;
            }
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

    public String getName() {
        return name;
    }

    public int getPi() {
        return pi;
    }

    public String getType() {
        return type;
    }

    public boolean isTypeMaster() {
        return type.equals("master");
    }

    public boolean isTypeEngineer() {
        return type.equals("engineer");
    }

    public boolean isTypeDoctor() {
        return type.equals("doctor");
    }

    public boolean isUpgradeable() {
        return pi >= 10;
    }

    public boolean isKillable() {
        return pi == 0;
    }

    public boolean successfulPhD(int summation) {
        return pi > summation;
    }
}
