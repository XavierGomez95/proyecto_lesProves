package business.trial;

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

    @Override
    public String getInfo() {
        return super.getInfo() + "," + masterName + "," + numCredits + "," + probability;
    }

    public static Trial fromLine(String line) {
        String name = line.split(",")[0];
        String masterName = line.split(",")[1];
        int numCredits = Integer.parseInt(line.split(",")[2]);
        int probability = Integer.parseInt(line.split(",")[3]);

        return new StudyMaster(name, masterName, numCredits, probability);
    }
}
