package business;

import business.player.Player;
import business.trial.Trial;

public class Work implements Runnable {
    private Player player;
    private Trial trial;


    public Work(Player player, Trial trial) {
        this.player = player;
        this.trial = trial;
    }

    @Override
    public void run() {
//instance of phdefense
    }
    public void stopWork(){

    }
}
