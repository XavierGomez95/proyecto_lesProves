package business.trial;

import business.player.Player;

import java.util.ArrayList;
import java.util.List;

public class BudgedRequest extends Trial {
    private String entityName;
    private long amount;

    public BudgedRequest(String name, String entityName, long amount) {
        super(name);
        this.entityName = entityName;
        this.amount = amount;
    }

    @Override
    public List<String> listInfo() {
        List<String> list = new ArrayList<>();
        list.add("Trial: " + name + " (Budget request)");
        list.add("Entity: " + entityName);
        list.add("Budget: " + amount + " €");
        return list;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "," + entityName + "," + amount;
    }

    public static Trial fromLine(String line) {
        String name = line.split(",")[0];
        String entityName = line.split(",")[1];
        long amount = Long.parseLong(line.split(",")[2]);

        return new BudgedRequest(name, entityName, amount);
    }
}
