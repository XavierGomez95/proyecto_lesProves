package presentation;

import business.Edition;
import business.trial.Trial;
import persistence.CsvTrialDAO;
import persistence.JsonTrialDAO;
import persistence.TrialDAO;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    protected Menu menu;
    protected TrialDAO trialDAO;
    protected List<Trial> trials;
    protected List<Edition> editions = new ArrayList<>();

    public Controller(Menu menu) {
        this.menu = menu;
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
                trialDAO = new CsvTrialDAO();
                trials = trialDAO.readAll(); // Falta por solucionar el problema
                //trialDAO.writeAll();
                menu.createNewLine();
                menu.showMessage("Loading data from CSV files...");
                menu.showTittle();
            }
            case "II" -> {
                trialDAO = new JsonTrialDAO();
                trials = trialDAO.readAll();
                //trialDAO.writeAll();
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

    private void exit() {
        if (!trials.isEmpty()) {
            //quitar sout, es prueba
            System.out.println("guardando");
            trialDAO.writeAll(trials);
        }
    }
}
