package business.trial;

import business.player.Player;

import java.util.List;

public class StudyMaster extends Trial {
    private String masterName;
    private int numCredits;
    private int probability;

    public StudyMaster(String name, List<Player> players, String masterName, int numCredits, int probability) {
        super(name, players);
        this.masterName = masterName;
        this.numCredits = numCredits;
        this.probability = probability;
    }
}
