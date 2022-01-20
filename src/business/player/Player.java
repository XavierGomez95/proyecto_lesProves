package business.player;

import java.util.ArrayList;
import java.util.List;


public class Player {
    private String name;
    protected int pi;
    private boolean alive;
    private String type;
    private boolean upGraded;

    public Player(String name) {
        this.name = name;
        this.pi = 5;
        this.alive = true;
        this.type = "engineer";
        this.upGraded = false;
    }

    public Player(String name, int pi, boolean alive, String type) {
        this.name = name;
        this.pi = pi;
        this.alive = alive;
        this.type = type;
    }

    /**
     * @param names
     * @return
     */
    public static ArrayList<Player> createNewPlayers(List<String> names) {
        ArrayList<Player> players = new ArrayList<>();
        for (String name : names) {
            players.add(new Player(name));
        }
        return players;
    }

    /**
     * @param points numerical value with which the final increment value is to be calculated.
     */
    public void subtractPoints(int points) {
        switch (type) {
            case "engineer" -> this.pi -= points;
            case "master", "doctor" -> this.pi -= (points / 2);
        }
        if (pi < 0) pi = 0;
    }

    /**
     * @param points numerical value with which the final increment value is to be calculated.
     */
    public void addPoints(int points) {
        switch (type) {
            case "engineer", "master" -> this.pi += points;
            case "doctor" -> this.pi += (points * 2);
        }
    }

    // Antes de preguntar: Continue the execution? [yes/no]:

    /**
     * @return String with the message that will be displayed on the screen before asking the user
     * whether or not to continue with the program execution.
     */
    public String requestStringUpGrade() {
        return name + " is now a " + type + " (with " + pi + " PI). ";
    }

    /**
     * Set the Player's attribute pi to 5, and convert the Player's type to master (if it's an engineer),
     * or doctor (if it's a master).
     */
    public void upGrade() {
        this.pi = 5;
        switch (type) {
            case "engineer" -> {
                type = "master";
                upGraded = true;
            }
            case "master" -> {
                type = "doctor";
                upGraded = true;
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

    /**
     * @return the Player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return the Player's pi amount.
     */
    public int getPi() {
        return pi;
    }

    /**
     * @return the Player's type (engineer, master or doctor).
     */
    public String getType() {
        return type;
    }

    /**
     * @return true (if a String variable is equal to "master"), or false (if is not)
     */
    public boolean isTypeMaster() {
        return type.equals("master");
    }

    /**
     * @return true (if a String variable is equal to "engineer"), or false (if is not)
     */
    public boolean isTypeEngineer() {
        return type.equals("engineer");
    }

    /**
     * @return true (if a String variable is equal to "doctor"), or false (if is not)
     */
    public boolean isTypeDoctor() {
        return type.equals("doctor");
    }

    /**
     * @return true (if an integer variable is greater than or equal to 10), or false (if is not)
     */
    public boolean isUpgradeable() {
        return pi >= 10;
    }

    /**
     * @return true (if an integer attribute is equal to 0), or false (if is not)
     */
    public boolean isKillable() {
        return pi == 0;
    }

    /**
     * @param summation
     * @return true (if an integer attribute is greater than summation), or false (if is not)
     */
    public boolean successfulPhD(int summation) {
        return pi > summation;
    }

    public boolean isAlive() {
        return alive;
    }
}
