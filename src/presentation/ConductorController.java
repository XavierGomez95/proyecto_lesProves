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
        //menu.createNewLine();
        if (editionManager.isCoincident(YEAR)) {
            boolean exist = executionManager.checkCurrentYear();
            menu.createNewLine();
            menu.showMessage("--- The Trials " + YEAR + " ---");
            //menu.createNewLine();
            if (!exist) {
                addPlayers();
                execute();
            } else if (!executionManager.isFinished()) {
                execute();
            } else {
                finishExecution();

            }

        } else {
            menu.createNewLine();
            menu.showError("No edition defined for the current year " + YEAR);
            menu.createNewLine();
        }
    }


    /**
     * Loop of {@link Menu#askString(String name)} to {@link ExecutionManager#createPlayers(List names)} of the Edition
     */
    private void addPlayers() {
        int numPlayers = editionManager.checkNumPlayers();
        List<String> names = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            String player = menu.askString("Enter the player's name (" + (i + 1) + "/" + numPlayers + "): ");
            names.add(player);
        }
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
        menu.createNewLine();
        menu.showMessage("Trial #" + (num + 1) + " - " + currentTrial.getName());
        menu.createNewLine();
        if (!isBudgedRequest(currentTrial)) {
            listResults(executionManager.start(currentTrial));
        } else {
            startBudgetRequest((BudgetRequest) currentTrial);
        }


        if (!isLastTrial(num + 1) && checkPlayersAlive()) {
            executionManager.addNumTrial();
            askToContinue();
        } else {
            executionManager.finishExecution();
            finishExecution();
        }

    }

    private void listResults(List<String> results) {
        for (String line : results) {
            menu.showMessage(line);
        }

    }

    private void askToContinue() {
        String answer;
        menu.createNewLine();
        do {
            answer = menu.askString(executionManager.checkUpgrade() + "Continue the execution? [yes/no]: ");

            if (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")) {
                menu.showError("Enter a correct value [yes/no].");
            }
        } while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no"));

        if (answer.equalsIgnoreCase("YES")) {
            execute();
        }
    }

    /**
     * @param num
     * @return
     */
    public boolean isLastTrial(int num) {
        return getListTrialsExecution().size() <= num;
    }

    private void finishExecution() {
        menu.createNewLine();
        if (checkPlayersAlive()) {
            menu.showMessage("THE TRIALS " + YEAR + " HAVE ENDED - PLAYERS WON");
        } else {
            menu.showMessage("THE TRIALS " + YEAR + " HAVE ENDED - PLAYERS LOST");
        }
    }

    private boolean checkPlayersAlive() {
        return executionManager.checkWin();
    }

    /**
     * @param t
     * @return
     */
    public boolean isBudgedRequest(Trial t) {
        return t instanceof BudgetRequest;
    }

    /**
     *
     */
    public void startBudgetRequest(BudgetRequest currentTrial) {
        int totalPI = executionManager.getTotalPI();
        boolean win = trialManager.executeBudgetRequest(totalPI, currentTrial);
        if (win) {
            menu.showMessage("The research group got the budget!");
            listResults(executionManager.addPiBudget());
        } else {
            menu.showMessage("The research group didn't get the budget...");
            listResults(executionManager.subtractPiBudget());
        }


    }

}
