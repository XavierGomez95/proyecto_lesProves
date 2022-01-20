package business.trial;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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

    /**
     * Generates randomly (within a probability) approver credits (within a maximum)
     *
     * @return amount of credits approved
     */
    public int approvedCredits() {
        int approved = 0;
        for (int i = 0; i < numCredits; i++) {
            if (new Random().nextInt(100) <= probability) approved++;
        }
        return approved;
    }

    /**
     * Check if more than half of the credits of the master have been passed.
     *
     * @param approvedCredits amount of credits approved.
     * @return true (if the statement is met) or false (if is not).
     */
    public boolean isMasterPassed(int approvedCredits) {
        return approvedCredits > (numCredits / 2);
    }

    /**
     * @return the number of credits.
     */
    public int getNumCredits() {
        return numCredits;
    }

    /**
     * @return a String with all the information of a master ready to be displayed on the screen.
     */
    @Override
    public String listInfo() {
        return "Trial: " + name + getType() + System.lineSeparator() +
                "Master: Master in " + masterName + System.lineSeparator() +
                "ECTS: " + numCredits + " with a " + probability +
                "% chance to pass each one" + System.lineSeparator();
    }

    /**
     * @return a String representing the trial type.
     */
    @Override
    public String getType() {
        return " (Master Studies)";
    }

    /**
     * @return a String with all the information of a master ready to be persisted.
     */
    @Override
    public String getInfo() {
        return super.getInfo() + "," + masterName + "," + numCredits + "," + probability;
    }

    /**
     * From a string it receives, it separates the information into variables and creates a new instance of the
     * class itself.
     *
     * @param line String with information about the master.
     * @return an instance of the StudyMaster class.
     */
    public static Trial fromLine(String line) {
        String name = line.split(",")[0];
        String masterName = line.split(",")[1];
        int numCredits = Integer.parseInt(line.split(",")[2]);
        int probability = Integer.parseInt(line.split(",")[3]);

        return new StudyMaster(name, masterName, numCredits, probability);
    }

}
