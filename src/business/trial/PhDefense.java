package business.trial;

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
    public String listInfo() {
        return new StringBuilder("Trial: ").append(name).append(getType()).append(System.lineSeparator())
                .append("Field: ").append(studyField).append(System.lineSeparator())
                .append("Difficulty: ").append(difficulty).append(System.lineSeparator()).toString();
    }

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        return sb.append(super.getInfo()).append(",").append(studyField).append(",").append(difficulty).toString();
    }

    public static Trial fromLine(String line) {
        String name = line.split(",")[0];
        String studyField = line.split(",")[1];
        int difficulty = Integer.parseInt(line.split(",")[2]);

        return new PhDefense(name, studyField, difficulty);
    }

    @Override
    public String getType() {
        return " (Doctoral thesis defense)";
    }


}
