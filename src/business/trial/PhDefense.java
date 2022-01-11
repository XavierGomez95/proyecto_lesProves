package business.trial;

import business.player.Player;

import java.util.ArrayList;
import java.util.List;

public class PhDefense extends Trial {
    private String studyField;
    private int difficulty;

    public PhDefense(String name, String studyField, int difficulty) {
        super(name);
        this.studyField = studyField;
        this.difficulty = difficulty;
    }

    @Override
    public List<String> listInfo() {
        List<String> list = new ArrayList<>();
        list.add("Trial: " + name + " (Doctoral thesis defense)");
        list.add("Field: " + studyField);
        list.add("Difficulty: " + difficulty);
        return list;
    }


}
