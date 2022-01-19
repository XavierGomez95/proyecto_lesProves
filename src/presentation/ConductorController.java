package presentation;

import business.*;
import business.trial.BudgedRequest;
import business.trial.Trial;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class ConductorController {
    EditionManager editionManager;
    ExecutionManager executionManager;
    TrialManager trialManager;
    private Menu menu;

    public ConductorController(Menu menu, List<Trial> trials, List<Edition> editions, List<Execution> executions) {
        this.editionManager = new EditionManager(editions);
        this.executionManager = new ExecutionManager(executions);
        this.trialManager = new TrialManager(trials);
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
            if (!newExecution) {
                execute();
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

    /**
     *
     * @return
     */
    private List<Trial> getListTrialsExecution() {
        List<String> listNamesTrials = editionManager.listTrialsEdition(Year.now().getValue());
        List<Trial> listTrialsExecution = new ArrayList<>();
        for (String s : listNamesTrials) {
            listTrialsExecution.add(trialManager.getByName(s));
        }

        return listTrialsExecution;
    }

    /**
     *
     */
    private void execute() {
        int num = executionManager.getNumTrial();
        Trial currentTrial = getListTrialsExecution().get(num);
        if (!isBudgedRequest(currentTrial)) {
            menu.showMessage("Trial #" + num + 1 + " - " + currentTrial.getName());
            executionManager.start(currentTrial);
        } else {
            startBudgetRequest();
            menu.showMessage("Budget request hacer cosis");
        }
        executionManager.addNumTrial();
        //comprobar que no sea el ultimo tial
        if (isLastTrial(num))
        askToContinue();
    }

    /**
     *
     * @param t
     * @return
     */
    public boolean isBudgedRequest(Trial t) {
        return t instanceof BudgedRequest;
    }

    /**
     *
     * @param num
     * @return
     */
    public boolean isLastTrial(int num) {
        return getListTrialsExecution().size() > num;
    }

    /**
     *
     */
    public void startBudgetRequest() {
        //sumar todos los PI de todos los Players en execution Manager
        //llamar a manager trial execute budgetRequest para calgular el logaritmo

    }

    /**
     *
     * @return
     */
    private String askToContinue() {
        String answer;
        do {
            answer = menu.askString("Continue the execution? [yes/no]:");
            if (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")) {
                menu.showError("Enter a correct value [yes/no].");
                menu.createNewLine();
            }
        } while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no"));

        if (answer.equalsIgnoreCase("YES")) {
            execute();
        }
        return answer;
    }


}
