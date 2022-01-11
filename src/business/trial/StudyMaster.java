package business.trial;

import business.player.Player;

import java.util.ArrayList;
import java.util.List;

public class StudyMaster extends Trial {
    private String masterName;
    private int numCredits;
    private int probability;

    public StudyMaster(String name, String masterName, int numCredits, int probability) {
        super(name);
        this.masterName = masterName;
        this.numCredits = numCredits;
        this.probability = probability;
    }

    @Override
    public List<String> listInfo() {
        List<String> list = new ArrayList<>();
        list.add("Trial: " + name + " (Master Studies)");
        list.add("Master: Master in " + masterName);
        list.add("ECTS: " + numCredits + " with a " + probability + "% chance to pass each one");
        return list;
    }
}
