package presentation;

import business.Edition;
import business.Execution;
import business.trial.Trial;
import persistence.editiondao.CsvEditionDAO;
import persistence.editiondao.EditionDAO;
import persistence.editiondao.JsonEditionDAO;
import persistence.executionDAO.CsvExecutionDAO;
import persistence.executionDAO.ExecutionDAO;
import persistence.trialdao.CsvTrialDAO;
import persistence.trialdao.JsonTrialDAO;
import persistence.trialdao.TrialDAO;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    protected Menu menu;
    private TrialDAO trialDAO;
    private EditionDAO editionDAO;
    private ExecutionDAO executionDAO;
    protected List<Trial> trials;
    protected List<Edition> editions = new ArrayList<>();
    protected List<Execution> executions = new ArrayList<>();

    public Controller(Menu menu) {
        this.menu = menu;
    }

    /**
     *
     */
    public void run() {
        String format = chooseFormat();
        chooseRole(format);

    }


    private String chooseFormat() {
        String format;

        menu.showMessage("The IEEE needs to know where your allegiance lies.");
        menu.showFormatMenu();

        do {
            format = menu.askString("Pick a faction: ");
            if (!format.equals("I") && !format.equals("II")) {
                menu.showError("Error entry."); // Poner el mensaje que quieras.
                menu.createNewLine();
            }
        } while (!format.equals("I") && !format.equals("II"));

        loadData(format);

        return format;
    }

    /**
     * @param format
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

                trials = trialDAO.readAll();
                editions = editionDAO.readAll();
                menu.createNewLine();
                menu.showMessage("Loading data from JSON files...");
                menu.showTittle();
            }
        }
    }

    /**
     * @param fileFormat
     */
    private void chooseRole(String fileFormat) {
        String format;
        menu.showMessage("Welcome to The Trials. Who are you?");
        menu.showRoleMenu();
        do {
            format = menu.askString("Enter a role: ");
            if (!format.equals("A") && !format.equals("B")) {
                menu.showError("Error entry.");
                menu.createNewLine();
            }
        } while (!format.equals("A") && !format.equals("B"));
        selectRole(format);
    }

    /**
     * @param roleFormat
     */
    private void selectRole(String roleFormat) {
        switch (roleFormat) {
            case "A" -> {//porque pasas por param los lists y menu si los tienes en protected?
                CompositorController compositorController = new CompositorController(this.menu, trials, editions);
                compositorController.run();
                exit();
            }
            case "B" -> {//porque pasas por param los lists y menu si los tienes en protected?
                ConductorController conductorController = new ConductorController(this.menu, trials, editions);
                conductorController.run();
                exit();
            }
        }
    }

    /**
     *
     */
    private void exit() {
        if (!trials.isEmpty()) trialDAO.writeAll(trials);

        if (!editions.isEmpty()) editionDAO.writeAll(editions);
        executionDAO.writeAll(executions);

    }
}
