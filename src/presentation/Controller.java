package presentation;

import business.Edition;
import business.trial.Trial;
import persistence.editiondao.CsvEditionDAO;
import persistence.editiondao.EditionDAO;
import persistence.editiondao.JsonEditionDAO;
import persistence.trialdao.CsvTrialDAO;
import persistence.trialdao.JsonTrialDAO;
import persistence.trialdao.TrialDAO;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    protected Menu menu;
    protected TrialDAO trialDAO;
    protected EditionDAO editionDAO;
    protected List<Trial> trials;
    protected List<Edition> editions = new ArrayList<>();

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
            if (!format.equals("I") && !format.equals("II"))
                menu.errorInput("Error entry."); // Poner el mensaje que quieras.
        } while (!format.equals("I") && !format.equals("II"));

        loadData(format);

        return format;
    }

    /**
     *
     * @param format
     */
    private void loadData(String format) {
        switch (format) {
            case "I" -> {
                trialDAO = new CsvTrialDAO();
                editionDAO = new CsvEditionDAO();
                trials = trialDAO.readAll();
                editions = editionDAO.readAll();
                menu.createNewLine();
                menu.showMessage("Loading data from CSV files...");
                menu.showTittle();
            }
            case "II" -> {
                trialDAO = new JsonTrialDAO();
                editionDAO = new JsonEditionDAO();
                // editionDAO ;
                trials = trialDAO.readAll();
                editions = editionDAO.readAll();
                menu.createNewLine();
                menu.showMessage("Loading data from JSON files...");
                menu.showTittle();
            }
        }
    }

    /**
     *
     * @param fileFormat
     */
    private void chooseRole(String fileFormat) {
        String format;
        menu.showMessage("Welcome to The Trials. Who are you?");
        menu.showRoleMenu();
        do {
            format = menu.askString("Enter a role: ");
            if (!format.equals("A") && !format.equals("B")) menu.errorInput("Error entry.");
        } while (!format.equals("A") && !format.equals("B"));
        selectRole(format);
    }

    /**
     *
     * @param roleFormat
     * @param fileFormat
     */
    private void selectRole(String roleFormat) {
        switch (roleFormat) {
            case "A" -> {
                CompositorController compositorController = new CompositorController(this.menu, trials, editions);
                compositorController.run();
                exit();
            }
            case "B" -> {
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


    }
}
