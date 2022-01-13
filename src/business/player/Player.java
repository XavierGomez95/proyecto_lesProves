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





    public static List<Player> fromLine(String string) {
        String[] arrayPlayers = string.split("}");
        String p = string.substring(string.indexOf("{"), string.indexOf("}"));
        List<Player> players = new ArrayList<>();
        for (String player : arrayPlayers) {
            //falta comprobar els punts per saber quin tipues de player és
            player = player.substring(1);
            players.add(new Engineer(player.split(",")[0], Integer.parseInt(player.split(",")[1])));
        }
        return players;
    }
}
