package presentation;

import business.Edition;
import business.trial.Trial;
import persistence.TrialDAO;

import java.util.List;

public class Controller {
    protected Menu menu;
    protected TrialDAO trialDAO;
    protected List<Trial> trials;
    private List<Edition> editions;

    public Controller(Menu menu, TrialDAO lesProvesDAO) {
        this.menu = menu;
        this.trialDAO = lesProvesDAO;
    }

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


    private void loadData(String format) {
        switch (format) {
            case "I" -> {
                trials = trialDAO.readCsv(); // Falta por solucionar el problema
                //trialDAO.writeCsv();
                menu.createNewLine();
                menu.showMessage("Loading data from CSV files...");
                menu.showTittle();
            }
            case "II" -> {
                trials = trialDAO.readJson();
                //trialDAO.writeJson();
                menu.createNewLine();
                menu.showMessage("Loading data from JSON files...");
                menu.showTittle();
            }
        }
    }


    private void chooseRole(String fileFormat) { // Este metodo en teoria recibe un ArrayList<String>
        String format;
        menu.showMessage("Welcome to The Trials. Who are you?");
        menu.showRoleMenu();
        do {
            format = menu.askString("Enter a role: ");
            if (!format.equals("A") && !format.equals("B")) menu.errorInput("Error entry.");
        } while (!format.equals("A") && !format.equals("B"));
        selectRole(format, fileFormat);
    }

    // Si quieres podemos fusionar este metodo y el loadData, y cambiar el nombre.
    private void selectRole(String roleFormat, String fileFormat) {
        switch (roleFormat) {
            case "A" -> {
                CompositorController compositorController = new CompositorController(this.menu, this.trialDAO, fileFormat, trials, editions);
                compositorController.run();
            }
            case "B" -> {
                ConductorController conductorController = new ConductorController(this.menu, this.trialDAO, fileFormat);
                conductorController.run();
            }
        }
    }
}