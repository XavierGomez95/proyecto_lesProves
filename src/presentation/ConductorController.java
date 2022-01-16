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

    public ConductorController(Menu menu, /*List<Trial> trials,*/ List<Edition> editions, List<Execution> executions) {
        this.editionManager = new EditionManager(editions);
        this.executionManager = new ExecutionManager(executions);
        this.menu = menu;
    }

    /**
     * Runs the execution mode if the current year has an edition
     */
    public void run() {
        menu.createNewLine();
        menu.showMessage("Entering execution mode...");

        if (editionManager.checkCurrentYearEdition()) {
            // menu.showMessage("Existing edition for " + Year.now().getValue()); // TEMPORAL
            menu.showMessage("--- The Trials " + Year.now().getValue() + " ---");
            boolean newExecution = checkPlayers();
            if (newExecution) {
                execute(1);//hacer cosas y pasar el num del trial?
            }

        } else {
            menu.createNewLine();
            menu.showMessage("No edition defined for the current year " + Year.now().getValue());
            menu.createNewLine();
        }
    }

    /**
     * {@link ExecutionManager#checkCurrentYear()} to know if there is information about this year Execution to execute
     * the trials, otherwise we ask to {@link #enterPlayers(int num)} and we create them
     */
    private boolean checkPlayers() {
        boolean newExecution = false;
        if (!executionManager.checkCurrentYear()) {
            newExecution = true;
            int numPlayers = editionManager.checkNumPlayers();
            enterPlayers(numPlayers);
        }
        return newExecution;
    }

    /**
     * Loop of {@link Menu#askString(String name)} to {@link #createPlayers(List names)} of the Edition
     *
     * @param numPlayers to know how many names of players we need
     */
    private void enterPlayers(int numPlayers) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            String player = menu.askString("Enter the player's name (" + (i + 1) + "/" + numPlayers + "): ");
            list.add(player);
        }
        createPlayers(list);
    }

    /**
     * it calls {@link ExecutionManager#createPlayers(List names)}
     *
     * @param names of players
     */
    private void createPlayers(List<String> names) {
        executionManager.createPlayers(names);
    }


    private Trial selectTrialExecution() {
        return null;
    }

    private void execute(int num) {//param: num del trial o Objeto trial
        //executionManager.start();

    }


}
