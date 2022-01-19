package business.trial;

public class BudgedRequest extends Trial {
    private String entityName;
    private long amount;

    public BudgedRequest(String name, String entityName, long amount) {
        super(name);
        this.entityName = entityName;
        this.amount = amount;
    }



    public int calculateFormula(int pi) {
        return (int)(Math.log(pi) / Math.log(2));
    }


    /**
     *
     * @return a String with all the information of a budget request ready to be displayed on the screen.
     */
    @Override
    public String listInfo() {
        return new StringBuilder("Trial: ").append(name).append(getType()).append(System.lineSeparator())
                .append("Entity: ").append(entityName).append(System.lineSeparator())
                .append("Budget: ").append(amount).append(" €").append(System.lineSeparator()).toString();
    }

    /**
     *
     * @return a String with all the information of a budget request ready to be persisted.
     */
    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        return sb.append(super.getInfo()).append(",").append(entityName).append(",").append(amount).toString();
    }

    /**
     * From a string it receives, it separates the information into variables and creates a new instance of the
     * class itself.
     * @param line String with information about the budget request.
     * @return an instance of the BudgedRequest class.
     */
    public static Trial fromLine(String line) {
        String name = line.split(",")[0];
        String entityName = line.split(",")[1];
        long amount = Long.parseLong(line.split(",")[2]);

        return new BudgedRequest(name, entityName, amount);
    }

    /**
     *
     * @return a String representing the trial type.
     */
    @Override
    public String getType() {
        return " (Budget request)";
    }
}
