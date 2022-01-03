package business.trial;

import business.player.Player;

import java.util.List;

public class BudgedRequest extends Trial {
    private String entityName;
    private long amount;

    public BudgedRequest(String name, List<Player> players, String entityName, long amount) {
        super(name, players);
        this.entityName = entityName;
        this.amount = amount;
    }
}
