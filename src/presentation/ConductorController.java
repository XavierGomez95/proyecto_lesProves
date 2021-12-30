package presentation;

import business.ConductorManager;
import persistence.TrialDAO;

public class ConductorController extends Controller{
    ConductorManager conductorM;

    public ConductorController(Menu menu, TrialDAO trialDAO) {
        super(menu, trialDAO);
        this.conductorM = new ConductorManager();
    }

    public void run () {
        menu.showMessage("ENTERING THE CONDUCTOR CONTROLLER"); // Mensaaje temporal
    }

    private void trialMenu () {

    }

    private void createMenu () {

    }

    private void listMenu () {

    }

    private void deleteMenu () {

    }

    private void createPaper () {

    }

    private void createMaster () {

    }

    private void createPhD () {

    }

    private void createBudget () {

    }

    private void editionMenu () {

    }

    private void createEdition () {

    }

    private void listEdition () {

    }

    private void deleteEdition () {

    }
}
