package presentation;

import business.ConductorManager;
import business.Edition;
import business.trial.Trial;
import persistence.JsonTrialDAO;

import java.time.Year;
import java.util.List;

public class ConductorController extends Controller {
    ConductorManager conductorM;

    public ConductorController(Menu menu, List<Trial> trials, List<Edition> editions) {
        super(menu);
        this.conductorM = new ConductorManager(trials, editions);

    }

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

    private void trialMenu() {

    }

    private void createMenu() {

    }

    private void listMenu() {

    }

    private void deleteMenu() {

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
