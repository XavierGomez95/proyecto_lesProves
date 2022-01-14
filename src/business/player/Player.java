package business.player;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    private String name;
    protected int pi;
    protected boolean alive;

    public Player(String name, int pi) {
        this.name = name;
        this.pi = pi;
        this.alive = true;
    }

    /**
     * To read from csv
     * @param s line with all Player Objects saved in csv
     * @return ArrayList of Players
     */
    public static ArrayList<Player> fromLine(String s) {//NO ACABAT
        String[] arrayPlayers = s.split("}");
        //String p = s.substring(s.indexOf("{"), s.indexOf("}"));
        ArrayList<Player> players = new ArrayList<>();
        for (String player : arrayPlayers) {
            //falta comprobar els punts per saber quin tipues de player ésdependiendo del type
            player = player.substring(1);
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
