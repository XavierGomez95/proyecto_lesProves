package business.trial;

import business.player.Player;

import java.util.List;

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

    public abstract String listInfo();

    public String getName() {
        return name;
    }


    public boolean checkName(String name) {
        return name.equals(this.name);
    }

    public abstract String getType();

}
