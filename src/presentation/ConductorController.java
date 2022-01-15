package presentation;

import business.EditionManager;
import business.Edition;
import business.Execution;
import business.ExecutionManager;
import business.trial.Trial;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class ConductorController {
    EditionManager editionManager;
    ExecutionManager executionManager;
    private Menu menu;

    public ConductorController(Menu menu, List<Trial> trials, List<Edition> editions, List<Execution> executions) {
        this.editionManager = new EditionManager(editions);
        this.executionManager = new ExecutionManager(executions);
        this.menu = menu;
    }

    /**
     *
     */
    public void run() {
        menu.createNewLine();
        menu.showMessage("Entering execution mode...");

        if (editionManager.checkCurrentYearEdition()) {
            // menu.showMessage("Existing edition for " + Year.now().getValue()); // TEMPORAL
            menu.showMessage("--- The Trials " + Year.now().getValue() + " ---");
            checkPlayers();
        } else {
            menu.createNewLine();
            menu.showMessage("No edition defined for the current year " + Year.now().getValue());
            menu.createNewLine();
        }
    }

    private void checkPlayers() {
        if (!executionManager.checkCurrentYear()) {
            int numPlayers = editionManager.checkNumPlayers();
            enterPlayers(numPlayers);
        } else {
            execute();
        }

    }


    private void enterPlayers(int numPlayers) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            String player = menu.askString("Enter the player's name (" + (i + 1) + "/" + numPlayers + "): ");
            list.add(player);
        }
        createPlayers(list);
    }

    private void createPlayers(List<String> names) {

        executionManager.createPlayers(names);
    }

    private void execute() {

    }


}
