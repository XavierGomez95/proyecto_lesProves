package presentation;

import business.CompositorManager;
import business.trial.Trial;
import persistence.TrialDAO;

import java.util.List;

public class CompositorController extends Controller {
    CompositorManager compositorM;
    String fileFormat; // COMPROBAR SI ESTO ESTA BIEN AQUI
    List<Trial> trials;

    public CompositorController(Menu menu, TrialDAO trialDAO, String fileFormat, List<Trial> trials) {
        super(menu, trialDAO);
        this.compositorM = new CompositorManager();
        this.fileFormat = fileFormat;
        this.trials = trials;
    }

    public void run() {
        int mode;
        //String factionInput;
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

    private String enterMode(int mode) {
        String option = "";
        switch (mode) {
            case 1 -> manageTrials(); // 4.3.1
            case 2 -> manageEditions();// 4.3.2
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

    private void manageEditions() {
        String option;
        do {
            menu.showEditionsMenu();
            do {
                option = menu.askString("Enter an option: ");
                if (!(option.equals("a") || option.equals("b") || option.equals("c") || option.equals("d") || option.equals("e")))
                    menu.errorInput("Enter a letter.");
            } while (!(option.equals("a") || option.equals("b") || option.equals("c") || option.equals("d") || option.equals("e")));

            System.out.println("SOME STUFF HERE"); // TEMPORAL
                    /*
                    switch (option) {
                        case 1 -> // option 1
                        case 2 -> // option 2
                        case 3 -> // option 3
                        case 4 -> // option 4
                    }
                    */
        } while (!(option.equals("e")));
    }

    private void manageTrials() {
        String option;
        do {
            menu.showTrialsMenu();
            do {
                option = menu.askString("Enter an option: ");
                if (!(option.equals("a") || option.equals("b") || option.equals("c") || option.equals("e")))
                    menu.errorInput("Enter a letter.");
            } while (!(option.equals("a") || option.equals("b") || option.equals("c") || option.equals("e")));

            System.out.println("SOME STUFF HERE"); // TEMPORAL

            switch (option) {
                case "a" -> createTrial();
                case "b" -> listTrial();
                case "c" -> deleteTrial();
            }
        } while (!(option.equals("e")));
    }

    private void createTrial() {
        int option;
        menu.showTrialTypesMenu();
        do {
            option = menu.askInteger("Enter an option: ");
            if (!(option == 1 || option == 2 || option == 3 || option == 4 || option == 5))
                menu.errorInput("Enter a number between 1 and 5 (including both).");
        } while (!(option == 1 || option == 2 || option == 3 || option == 4 || option == 5));

        switch (option) {
            case 1 ->  enterArticleInfo();
            case 2 ->  enterMasterInfo();
            case 3 ->  enterPHDefenseInfo();
            case 4 ->  enterBudgetRequestInfo();
        }

        if (option != 5) menu.showMessage("The trial was created successfully!");
    }

    private void enterBudgetRequestInfo() {
        String trialName = menu.askString("Enter the trial’s name: ");
        String entityName = menu.askString("Enter the entity’s name: ");
        int budgetAmount = menu.askInteger("Enter the budget amount: ");
    }

    private void enterPHDefenseInfo() {
        String trialName = menu.askString("Enter the trial’s name: ");
        String fieldOfStudy = menu.askString("Enter the thesis field of study: ");
        int defenseDifficulty = menu.askInteger("Enter the defense difficulty: ");
    }

    private void enterMasterInfo() {
        String trialName = menu.askString("Enter the trial’s name: ");
        String mastersName = menu.askString("Enter the master’s name: ");
        int ectsNumber = menu.askInteger("Enter the master’s ECTS number: ");
        int creditPassProbability = menu.askInteger("Enter the credit pass probability: ");
    }

    private void enterArticleInfo() {
        String trialName = menu.askString("Enter the trial’s name: ");
        String magazinesName = menu.askString("Enter the journal’s name: ");
        String quartile = menu.askString("Enter the journal’s quartile: ");
        int acceptanceProbability = menu.askInteger("Enter the acceptance probability: ");
        int revisionProbability = menu.askInteger("Enter the revision probability: ");
        int rejectionProbability = menu.askInteger("Enter the rejection probability: ");
    }

    // REVISAR SI ESTO ESTA BIEN AQUI O VA EN LA CLASE TRIAL
    private void listTrial() {
        List<String> list = trialListNames();
        if (!list.isEmpty()) menu.showTrials(list);
        else menu.showMessage("There are no trials. Please create first a trial.");
    }

    private void deleteTrial() {
        List<String> list = trialListNames();
        if (!list.isEmpty()) {
            menu.showTrials(list);
            int indexTrialToDelete = menu.askInteger("Enter an option: ");
            String trialsName = menu.askString("Enter the trial’s name for confirmation: ");
            menu.showMessage("The trial was successfully deleted.");
        }
        else menu.showMessage("There are no trials. Please create first a trial.");
    }

    private List<String> trialListNames() {
        List<String> list = null;
        for (Trial t : trials) {
            String name = t.getInfo();
            list.add(name);
        }
        return list;
    }

    private void addPlayers() {

    }


}
