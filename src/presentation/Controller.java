package presentation;

import business.Edition;
import business.Execution;
import business.trial.Trial;
import persistence.editiondao.CsvEditionDAO;
import persistence.editiondao.EditionDAO;
import persistence.editiondao.JsonEditionDAO;
import persistence.executionDAO.CsvExecutionDAO;
import persistence.executionDAO.ExecutionDAO;
import persistence.executionDAO.JsonExecutionDAO;
import persistence.trialdao.CsvTrialDAO;
import persistence.trialdao.JsonTrialDAO;
import persistence.trialdao.TrialDAO;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private Menu menu;
    private TrialDAO trialDAO;
    private EditionDAO editionDAO;
    private ExecutionDAO executionDAO;
    private List<Trial> trials;
    private List<Edition> editions = new ArrayList<>();
    private List<Execution> executions = new ArrayList<>();

    public Controller(Menu menu) {
        this.menu = menu;
    }


    public void run() {
        chooseFormat();
        chooseRole();
    }

    /**
     * The user choose the format to persist the information.
     */
    private void chooseFormat() {
        String format;

        menu.showMessage("The IEEE needs to know where your allegiance lies.");
        menu.showFormatMenu();

        do {
            format = menu.askString("Pick a faction: ");
            if (!format.equals("I") && !format.equals("II")) {
                menu.createNewLine();
                menu.showError("Error entry."); // Poner el mensaje que quieras.
                menu.createNewLine();
            }
        } while (!format.equals("I") && !format.equals("II"));

        loadData(format);
    }

    /**
     * @param format a String to detect whether the user has chosen to persist the project in Json or in Csv.
     */
    private void loadData(String format) {
        switch (format) {
            case "I" -> {
                trialDAO = new CsvTrialDAO();
                editionDAO = new CsvEditionDAO();
                executionDAO = new CsvExecutionDAO();

                trials = trialDAO.readAll();
                editions = editionDAO.readAll();
                executions = executionDAO.readAll();
                menu.createNewLine();
                menu.showMessage("Loading data from CSV files...");
                menu.showTittle();
            }
            case "II" -> {
                trialDAO = new JsonTrialDAO();
                editionDAO = new JsonEditionDAO();
                executionDAO = new JsonExecutionDAO();

                trials = trialDAO.readAll();
                editions = editionDAO.readAll();
                executions = executionDAO.readAll();
                menu.createNewLine();
                menu.showMessage("Loading data from JSON files...");
                menu.showTittle();
            }
        }
    }

    /**
     * The user decides whether to access the management menu or the execution menu.
     *
     */
    private void chooseRole() {
        String format;
        menu.showMessage("Welcome to The Trials. Who are you?");
        menu.showRoleMenu();
        do {
            format = menu.askString("Enter a role: ");
            if (!format.equals("A") && !format.equals("B")) {
                menu.createNewLine();
                menu.showError("Error entry.");
                menu.createNewLine();
            }
        } while (!format.equals("A") && !format.equals("B"));
        selectRole(format);
    }

    /**
     * Starts the operation of the menu selected by the user.
     *
     * @param roleFormat String representing the user's choice of menu type (management or execution).
     */
    private void selectRole(String roleFormat) {
        switch (roleFormat) {
            case "A" -> {
                CompositorController compositorController = new CompositorController(this.menu, trials, editions);
                compositorController.run();
                exit();
            }
            case "B" -> {
                ConductorController conductorController = new ConductorController(this.menu, trials, editions, executions);
                conductorController.run();
                exit();
            }
        }
    }

    /**
     * When the program stops, the information is saved.
     */
    private void exit() {
        menu.createNewLine();
        menu.showSuccess("Shutting down...");
        trialDAO.writeAll(trials);
        editionDAO.writeAll(editions);
        executionDAO.writeAll(executions);
    }
}
