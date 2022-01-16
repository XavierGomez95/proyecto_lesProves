package business.trial;

public class BudgedRequest extends Trial {
    private String entityName;
    private long amount;

    public BudgedRequest(String name, String entityName, long amount) {
        super(name);
        this.entityName = entityName;
        this.amount = amount;
    }


    public void execute() {
        //pag 9 logaritmo en base de todos los PI de los players sumados
    }

    @Override
    public String listInfo() {
        return new StringBuilder("Trial: ").append(name).append(getType()).append(System.lineSeparator())
                .append("Entity: ").append(entityName).append(System.lineSeparator())
                .append("Budget: ").append(amount).append(" €").append(System.lineSeparator()).toString();
    }

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        return sb.append(super.getInfo()).append(",").append(entityName).append(",").append(amount).toString();
    }

    public static Trial fromLine(String line) {
        String name = line.split(",")[0];
        String entityName = line.split(",")[1];
        long amount = Long.parseLong(line.split(",")[2]);

        return new BudgedRequest(name, entityName, amount);
    }

    @Override
    public String getType() {
        return " (Budget request)";
    }
}
