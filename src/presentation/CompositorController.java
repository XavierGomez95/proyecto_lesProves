package presentation;

import business.CompositorManager;
import persistence.TrialDAO;

public class CompositorController extends Controller{
    CompositorManager compositorM;

    public CompositorController(Menu menu, TrialDAO trialDAO) {
        super(menu, trialDAO);
        this.compositorM = new CompositorManager();
    }

    public void run () {
        int mode;
        menu.showMessage("Entering management mode...");
        menu.showManagerMenu(); // 4.3
        do {
            mode = menu.askOptionInteger("Pick a faction: ");
            if (mode < 1 || mode > 3) menu.errorInput("Enter a number between 1 and 3 (included).");
        } while (mode < 1 || mode > 3);
        enterMode(mode);
    }

    private void enterMode (int mode) {
        switch (mode) {
            case 1 -> menu.showTrialsMenu();
            case 2 -> menu.showEditionsMenu();
            case 3 -> menu.showMessage("Shutting down...");
        }
    }

    private void addPlayers () {

    }


}
