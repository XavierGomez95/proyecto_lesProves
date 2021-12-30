package presentation;

import persistence.TrialDAO;

public class Controller {
    protected Menu menu;
    private TrialDAO trialDAO;

    public Controller(Menu menu, TrialDAO lesProvesDAO) {
        this.menu = menu;
        this.trialDAO = lesProvesDAO;
    }

    public void run() {
        chooseFormat();
        chooseRole();
    }


    private void chooseFormat() {
        String format;
        menu.showMessage("The IEEE needs to know where your allegiance lies.");
        menu.showFormatMenu();
        do {
            format = menu.askOptionString("Pick a faction: ");
            if (!format.equals("I") && !format.equals("II"))
                menu.errorInput("Error entry."); // Poner el mensaje que quieras.
        } while (!format.equals("I") && !format.equals("II"));
        loadData(format);
    }


    private void loadData(String format) {
        switch (format) {
            case "I" -> {
                trialDAO.writeCsv(); // No se si hemos de crear los dos antes, o solo uno al momento de quererlo leer.
                //lesProvesDAO.readCsv(); // Falta por solucionar el problema
                menu.createNewLine();
                menu.showMessage("Loading data from CSV files...");
                menu.showTittle();
            }
            case "II" -> {
                // trialDAO.writeJson();
                //lesProvesDAO.readJson();
                menu.createNewLine();
                menu.showMessage("Loading data from JSON files...");
                menu.showTittle();
            }
        }
    }


    private void chooseRole() { // Este metodo en teoria recibe un ArrayList<String>
        String format;
        menu.showMessage("Welcome to The Trials. Who are you?");
        menu.showRoleMenu();
        do {
            format = menu.askOptionString("Enter a role: ");
            if (!format.equals("A") && !format.equals("B")) {
                menu.errorInput("Error entry."); // Poner el mensaje que quieras.
            }
        } while (!format.equals("A") && !format.equals("B"));
        selectRole(format);
    }

    // Si quieres podemos fusionar este metodo y el loadData, y cambiar el nombre.
    private void selectRole(String format) {
        switch (format) {
            case "A" -> {
                CompositorController compositorController = new CompositorController(this.menu, this.trialDAO);
                compositorController.run();
            }
            case "B" -> {
                ConductorController conductorController = new ConductorController(this.menu, this.trialDAO);
                conductorController.run();
            }
        }
    }
}