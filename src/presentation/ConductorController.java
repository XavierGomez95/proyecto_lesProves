package presentation;

import business.EditionManager;
import business.Edition;
import business.trial.Trial;

import java.time.Year;
import java.util.List;

public class ConductorController {
    EditionManager conductorM;
    private Menu menu;

    public ConductorController(Menu menu, List<Trial> trials, List<Edition> editions) {
        //super(menu);
        this.conductorM = new EditionManager(editions);
        this.menu = menu;
    }

    /**
     *
     */
    public void run() {
        menu.createNewLine();
        menu.showMessage("Entering execution mode...");

        if (conductorM.checkCurrentYearEdition()) {
            menu.showMessage("Existing edition for " + Year.now().getValue()); // TEMPORAL
        } else {
            menu.createNewLine();
            menu.showMessage("No edition defined for the current year " + Year.now().getValue());
            menu.createNewLine();
        }
    }


    private void createPaper() {

    }

    private void createMaster() {

    }

    private void createPhD() {

    }

    private void createBudget() {

    }

    private void editionMenu() {

    }

    private void createEdition() {

    }

    private void listEdition() {

    }

    private void deleteEdition() {

    }
}
