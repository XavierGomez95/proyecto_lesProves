package business.trial;

public class PhDefense extends Trial {
    private String studyField;
    private int difficulty;

    public PhDefense(String name, String studyField, int difficulty) {
        super(name);
        this.studyField = studyField;
        this.difficulty = difficulty;
    }


    /**
     * Calculate the summation of 2*1 - 1, N times.
     * @return the result of the summation.
     */
    public int calculateFormula() {
        int result = 0;
        for (int i = 0; i < difficulty; i++) {
            result++;
        }
        return result;
    }

    /**
     *
     * @return a String with all the information of a PhD Defense ready to be displayed on the screen.
     */
    @Override
    public String listInfo() {
        return new StringBuilder("Trial: ").append(name).append(getType()).append(System.lineSeparator())
                .append("Field: ").append(studyField).append(System.lineSeparator())
                .append("Difficulty: ").append(difficulty).append(System.lineSeparator()).toString();
    }

    /**
     *
     * @return a String with all the information of a PhD Defense ready to be persisted.
     */
    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        return sb.append(super.getInfo()).append(",").append(studyField).append(",").append(difficulty).toString();
    }

    /**
     * From a string it receives, it separates the information into variables and creates a new instance of the
     * class itself.
     * @param line String with information about the PhD Defense.
     * @return an instance of the PhDefense class.
     */
    public static Trial fromLine(String line) {
        String name = line.split(",")[0];
        String studyField = line.split(",")[1];
        int difficulty = Integer.parseInt(line.split(",")[2]);

        return new PhDefense(name, studyField, difficulty);
    }

    /**
     *
     * @return a String representing the trial type.
     */
    @Override
    public String getType() {
        return " (Doctoral thesis defense)";
    }


}
