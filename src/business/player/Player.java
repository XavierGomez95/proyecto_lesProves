package business.player;

import java.util.ArrayList;


public abstract class Player {
    private String name;
    protected int pi;
    protected boolean alive;

    public Player(String name, int pi) {
        this.name = name;
        this.pi = pi;
        this.alive = true;
    }

    public abstract void subtractPoints(int points);

    public abstract void addPoints(int points);

    public abstract void upGrade();

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
            //falta comprobar els punts per saber quin tipus de player ésdependiendo del type
            player = player.substring(1);
            player = player.replace("{", "");
            players.add(new Engineer(player.split(",")[0], Integer.parseInt(player.split(",")[1])));
        }
        return players;
    }

    /**
     * Gets info to write in csv files
     *
     * @return String line with all Player information
     */
    public String getInfo() {
        return "{" + name + "," + pi + "," + alive + "}";
    }
}
