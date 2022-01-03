package business.trial;

import business.player.Player;

import java.util.List;

public class Trial {
    private String name;
    private List<Player> players;

    public Trial(String name, List<Player> players) {
        this.name = name;
        this.players = players;
    }
/*
    public List listTrials () {

        return ;
    }

    public boolean deleteTrial () {

        return ;
    }
 */

    public void createTrial() {

    }

    public void incrementPoints() {

    }

    public void playerUpgrade(Player p) {

    }

    public String getInfo() {
        return name;
    }
}
