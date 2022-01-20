package presentation;

import business.*;
import business.trial.BudgetRequest;
import business.trial.Trial;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ConductorController {
    private EditionManager editionManager;
    private ExecutionManager executionManager;
    private TrialManager trialManager;
    private Menu menu;
    private final int YEAR = Year.now().getValue();

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

        if (editionManager.isCoincident(YEAR)) {
            boolean exist = executionManager.checkCurrentYear();
            menu.showMessage("--- The Trials " + YEAR + " ---");
            if (!exist) {
                addPlayers();
                execute();
            } else if (!executionManager.isFinished()) {
                execute();
            } else {
                //comprobar quien ha ganado, si algun player sigue vivo TODO
                menu.showMessage("THE TRIALS " + YEAR + " HAVE ENDED - PLAYERS WON");
            }

        } else {
            menu.createNewLine();
            menu.showError("No edition defined for the current year " + YEAR);
            menu.createNewLine();
        }
    }

    /**
     * We ask to {@link #enterPlayers(int num)} and we create them
     */
    private void addPlayers() {
        int numPlayers = editionManager.checkNumPlayers();
        enterPlayers(numPlayers);
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
     * @return
     */
    private List<Trial> getListTrialsExecution() {
        List<String> listNamesTrials = editionManager.listTrialsEdition(YEAR);

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
            menu.showMessage("Trial #" + (num + 1) + " - " + currentTrial.getName());
            listResults(executionManager.start(currentTrial));

        } else {
            startBudgetRequest((BudgetRequest) currentTrial);
        }


        if (!isLastTrial(num + 2)) {
            executionManager.addNumTrial();
            askToContinue();
        } else {
            executionManager.finishExecution();
            menu.showMessage("THE TRIALS " + YEAR + " HAVE ENDED");
        }

    }

    private void listResults(List<String> results) {
        for (String line : results) {
            menu.showMessage(line);
        }

    }

    /**
     * @param t
     * @return
     */
    public boolean isBudgedRequest(Trial t) {
        return t instanceof BudgetRequest;
    }

    /**
     * @param num
     * @return
     */
    public boolean isLastTrial(int num) {
        return getListTrialsExecution().size() <= num;
    }

    /**
     *
     */
    public void startBudgetRequest(BudgetRequest currentTrial) {
        int totalPI = executionManager.getTotalPI();
        boolean win = trialManager.executeBudgetRequest(totalPI, currentTrial);
        if (win) {
            menu.showMessage("The research group got the budget!");
            executionManager.addPiBudget();

        } else {
            executionManager.subtractPiBudget();
        }

    }


    private void askToContinue() {
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
    }

}
