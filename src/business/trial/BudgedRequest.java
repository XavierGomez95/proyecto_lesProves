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
}
