package business.trial;

public class BudgetRequest extends Trial {
    private String entityName;
    private long amount;

    public BudgetRequest(String name, String entityName, long amount) {
        super(name);
        this.entityName = entityName;
        this.amount = amount;
    }

    public boolean calculateFormula(int pi) {
        int result = (int) (Math.log(amount) / Math.log(2));
        return result < pi;
    }

    /**
     * @return a String representing the trial type.
     */
    @Override
    public String getType() {
        return " (Budget request)";
    }

    /**
     * @return a String with all the information of a budget request ready to be displayed on the screen.
     */
    @Override
    public String listInfo() {
        return "Trial: " + name + getType() + System.lineSeparator() +
                "Entity: " + entityName + System.lineSeparator() +
                "Budget: " + amount + " €" + System.lineSeparator();
    }

    /**
     * @return a String with all the information of a budget request ready to be persisted.
     */
    @Override
    public String getInfo() {
        return super.getInfo() + "," + entityName + "," + amount;
    }

    /**
     * From a string it receives, it separates the information into variables and creates a new instance of the
     * class itself.
     *
     * @param line String with information about the budget request.
     * @return an instance of the BudgedRequest class.
     */
    public static Trial fromLine(String line) {
        String name = line.split(",")[0];
        String entityName = line.split(",")[1];
        long amount = Long.parseLong(line.split(",")[2]);

        return new BudgetRequest(name, entityName, amount);
    }
}
