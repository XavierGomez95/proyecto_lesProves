package business.trial;

import business.player.Player;

import java.util.ArrayList;
import java.util.List;

public class PhDefense extends Trial {
    private ArrayList<String> studyFields;
    private int difficulty;

    public PhDefense(String name, List<Player> players, ArrayList<String> studyFields, int difficulty) {
        super(name, players);
        this.studyFields = studyFields;
        this.difficulty = difficulty;
    }
}
