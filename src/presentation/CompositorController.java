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
        String option = "e";
        // 4.3
        while (option.equals("a") || option.equals("b") || option.equals("c") || option.equals("d") || option.equals("e")) {
            menu.showMessage("Entering management mode...");
            menu.showManagerMenu(); // 4.3
            do {
                mode = menu.askInteger("Enter an option: ");
                if (mode < 1 || mode > 3) menu.errorInput("Enter a number between 1 and 3 (included).");
            } while (mode < 1 || mode > 3);
            option = enterMode(mode);
        }
    }

    private String enterMode (int mode) {
        String option = "exit"; // para que en el case 3 se salga del programa
        switch (mode) {
            case 1 -> { // 4.3.1
                menu.showTrialsMenu();
                do {
                    menu.showEditionsMenu();
                    do {
                        option = menu.askString("Enter an option: ");
                        if (!(option.equals("a") || option.equals("b") || option.equals("c") || option.equals("e")))
                            menu.errorInput("Enter a letter.");
                    } while (!(option.equals("a") || option.equals("b") || option.equals("c") || option.equals("e")));

                    System.out.println("SOME STUFF HERE");
                    /*
                    switch (option) {
                        case "a" -> //option 1
                        case "b" -> //option 2
                        case "c" -> //option 3
                    }
                    */

                } while (!(option.equals("e")));
            }
            case 2 -> { // 4.3.2
                do {
                    menu.showEditionsMenu();
                    do {
                        option = menu.askString("Enter an option: ");
                        if (!(option.equals("a") || option.equals("b") || option.equals("c") || option.equals("d") || option.equals("e")))
                            menu.errorInput("Enter a letter.");
                    } while (!(option.equals("a") || option.equals("b") || option.equals("c") || option.equals("d") || option.equals("e")));

                    System.out.println("SOME STUFF HERE");
                    /*
                    switch (option) {
                        case 1 -> //option 1
                        case 2 -> //option 2
                        case 3 -> //option 3
                        case 4 -> //option 4
                    }
                    */

                } while (!(option.equals("e")));
            }
            case 3 -> { // Exit del 4.3
                menu.showMessage("Shutting down...");
                if (!trials.isEmpty()) {
                    switch (fileFormat) {
                        case "I" -> trialDAO.writeCsv(trials);
                        case "II" -> trialDAO.writeJson(trials);
                    }
                }
            }
        }
        return option;
    }


    private void addPlayers () {

    }


}
