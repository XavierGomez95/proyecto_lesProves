package presentation;

import business.CompositorManager;
import business.Edition;
import business.trial.Trial;
import persistence.JsonTrialDAO;

import java.util.ArrayList;
import java.util.List;

public class CompositorController extends Controller {
    private CompositorManager compositorM;

    //private List<Trial> trials;
    //private List<Edition> editions;

    public CompositorController(Menu menu, List<Trial> trials, List<Edition> editions) {
        super(menu);
        this.compositorM = new CompositorManager(trials, editions);
        //this.trials = trials;
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
            case 1 -> option = manageTrials(); // 4.3.1
            case 2 -> option = manageEditions(); // 4.3.2
            case 3 -> menu.showMessage("Shutting down...");
        }
        return option;
    }

    private String manageEditions() {
        String option = "";
        do {
            menu.showEditionsMenu();
            do {
                option = menu.askString("Enter an option: ");
                if (!(option.equals("a") || option.equals("b") || option.equals("c") || option.equals("d") || option.equals("e")))
                    menu.errorInput("Enter a letter.");
            } while (!(option.equals("a") || option.equals("b") || option.equals("c") || option.equals("d") || option.equals("e")));

            menu.createNewLine();

            switch (option) {
                case "a" -> createEdition();
                case "b" -> listEdition();
                case "c" -> duplicateEdition();
                case "d" -> DeleteEdition();
            }
        } while (!(option.equals("e")));
        return option;
    }

    private void DeleteEdition() {

    }

    private void duplicateEdition() {

    }

    private void listEdition() {

    }

    private void createEdition() {
        List<String> list = compositorM.trialListNames();
        int editionsYear = menu.askInteger("Enter the edition's year: ");
        int numberPlayers = menu.askInteger("Enter the initial number of players: ");
        int numberTrials = menu.askInteger("Enter the number of trials: ");

        menu.createNewLine();
        menu.menuEditions(list);
        menu.createNewLine();

        list = pickTrials(numberTrials, list);

        compositorM.createEdition(editionsYear, numberPlayers, numberTrials, (ArrayList<String>)list);

        menu.createNewLine();
        menu.showMessage("The edition was created succesfully!");

        // menu.showMessage("The edition was created successfully!");
    }

    private List<String> pickTrials(int maxTrials, List<String> listNames) {
        List<String> list = new ArrayList<>();
        int trial;
        for (int i = 0; i < maxTrials; i++) {
            do {
            trial = menu.askInteger("Pick a trial (" + (i + 1) + "/" + maxTrials + "): ");
            if (trial < 1 || trial > maxTrials) menu.errorInput("Enter a number between 1 and " + maxTrials + " (including both)");
            } while(trial < 1 || trial > maxTrials);
            list.add(listNames.get(trial - 1));
        }
        return list;
    }



    private String manageTrials() {
        String option = "";
        do {
            menu.showTrialsMenu();
            do {
                option = menu.askString("Enter an option: ");
                if (!(option.equals("a") || option.equals("b") || option.equals("c") || option.equals("e")))
                    menu.errorInput("Enter a letter.");
            } while (!(option.equals("a") || option.equals("b") || option.equals("c") || option.equals("e")));

            menu.createNewLine();

            switch (option) {
                case "a" -> createTrial();
                case "b" -> listTrial();
                case "c" -> deleteTrial();
            }
        } while (!(option.equals("e")));
        return option;
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
            case 1 -> enterArticleInfo();
            case 2 -> enterMasterInfo();
            case 3 -> enterPHDefenseInfo();
            case 4 -> enterBudgetRequestInfo();
        }

        if (option != 5) menu.showMessage("The trial was created successfully!");
    }

    private void enterBudgetRequestInfo() {
        String trialName = menu.askString("Enter the trial’s name: ");
        String entityName = menu.askString("Enter the entity’s name: ");
        int budgetAmount = menu.askInteger("Enter the budget amount: ");
        compositorM.createBudgetRequest(trialName, entityName, budgetAmount);
    }

    private void enterPHDefenseInfo() {
        String trialName = menu.askString("Enter the trial’s name: ");
        String fieldOfStudy = menu.askString("Enter the thesis field of study: ");
        int defenseDifficulty = menu.askInteger("Enter the defense difficulty: ");
        compositorM.createPHD(trialName, fieldOfStudy, defenseDifficulty);
    }

    private void enterMasterInfo() {
        String trialName = menu.askString("Enter the trial’s name: ");
        String mastersName = menu.askString("Enter the master’s name: ");
        int ectsNumber = menu.askInteger("Enter the master’s ECTS number: ");
        int creditPassProbability = menu.askInteger("Enter the credit pass probability: ");
        compositorM.createMaster(trialName, mastersName, ectsNumber, creditPassProbability);
    }

    private void enterArticleInfo() {
        String trialName = menu.askString("Enter the trial’s name: ");
        String magazinesName = menu.askString("Enter the journal’s name: ");
        String quartile = menu.askString("Enter the journal’s quartile: ");
        int acceptanceProbability = menu.askInteger("Enter the acceptance probability: ");
        int revisionProbability = menu.askInteger("Enter the revision probability: ");
        int rejectionProbability = menu.askInteger("Enter the rejection probability: ");
        compositorM.createArticle(trialName, magazinesName, quartile, acceptanceProbability, revisionProbability, rejectionProbability);
    }

    // REVISAR SI ESTO ESTA BIEN AQUI O VA EN LA CLASE TRIAL
    private void listTrial() {
        List<String> list = compositorM.trialListInfo();
        if (!list.isEmpty()) menu.showlist(list); // 4.3.1.2
        else menu.showMessage("There are no trials. Please create first a trial.");
    }

    private void deleteTrial() {
        List<String> list = compositorM.trialListNames();
        if (!list.isEmpty()) {
            menu.menuTrials(list); // 4.3.1.3
            int indexTrialToDelete = menu.askInteger("Enter an option: ");
            if (indexTrialToDelete < list.size()) {
                String trialsName = menu.askString("Enter the trial’s name for confirmation: ");

                boolean deleted = compositorM.deleteTrial(indexTrialToDelete - 1, trialsName);

                if (deleted) menu.showMessage("The trial was successfully deleted.");
                else menu.errorInput("The trial has not been successfully deleted.");
            }
        } else menu.showMessage("There are no trials. Please create first a trial.");
    }

    private void addPlayers() {

    }


}
