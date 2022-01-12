package business.trial;

import business.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public abstract class Trial {
    protected String name;

    public Trial(String name) {
        this.name = name;
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

    public abstract List<String> listInfo();

    public String getName() {
        return name;
    }
}
