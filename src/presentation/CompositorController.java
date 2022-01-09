package presentation;

import business.CompositorManager;
import persistence.TrialDAO;

public class CompositorController extends Controller{
    CompositorManager compositorM;
    String fileFormat;

    public CompositorController(Menu menu, TrialDAO trialDAO, String fileFormat) {
        super(menu, trialDAO);
        this.compositorM = new CompositorManager();
        this.fileFormat = fileFormat;
    }

    public void run () {
        int mode;
        String factionInput;
        menu.showMessage("Entering management mode...");
        menu.showManagerMenu(); // 4.3
        do {
            mode = menu.askInteger("Pick a faction: ");
            if (mode < 1 || mode > 3) menu.errorInput("Enter a number between 1 and 3 (included).");
        } while (mode < 1 || mode > 3);
    }

    private void enterMode (int mode) {
        switch (mode) {
            case 1 -> menu.showTrialsMenu();
            case 2 -> menu.showEditionsMenu();
            case 3 -> {
                menu.showMessage("Shutting down...");
                if (!trials.isEmpty()) {
                    switch (fileFormat) {
                        case "I" -> trialDAO.writeCsv(trials);
                        case "II" -> trialDAO.writeJson(trials);
                    }
                }
            }
        }
    }

    private void addPlayers () {

    }


}
