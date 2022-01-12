package presentation;

import business.CompositorManager;
import business.Edition;
import business.trial.Trial;
import persistence.JsonTrialDAO;

import java.time.Year;
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
        List<String> list = compositorM.editionListInfo();
        int option, size;
        if (!list.isEmpty()) {
            menu.menuTrials(list); // 4.3.2.2

            size = list.size() + 1;
            option = (int) askNumber("Enter an option: ",
                    "Enter a correct opcion between " + 1 + " and " + size, 1, size);
            if (option < size) {
                List<String> listEditions = compositorM.getYearEditionInfo(option - 1);
                menu.createNewLine();
                menu.showListEditionByYear(listEditions);
            }

        }
        else menu.showMessage("There are no trials. Please create first a trial.");
    }

    private void createEdition() {
        List<String> list = compositorM.trialListNames();
        int editionsYear, numberPlayers, numberTrials;
        do {
            editionsYear = menu.askInteger("Enter the edition's year: ");
            if (compositorM.isCoincident(editionsYear)) menu.errorInput("This year exists, please enter another year.");
            if (editionsYear < Year.now().getValue()) menu.errorInput("Enter the curremt year or upper (dont repeat existing year editions).");
        } while (editionsYear < Year.now().getValue() || compositorM.isCoincident(editionsYear));

        numberPlayers = (int) askNumber("Enter the initial number of players: ",
                    "Enter a number between 1 and 5 (including both)", 1, 5);
        numberTrials = (int) askNumber("Enter the number of trials: ",
                    "Enter a number between 3 and 12 (including both)", 3, 12);
        menu.createNewLine();
        menu.menuEditions(list);
        menu.createNewLine();

        list = pickTrials(numberTrials, list, list.size());

        compositorM.createEdition(editionsYear, numberPlayers, numberTrials, (ArrayList<String>) list);

        menu.createNewLine();
        menu.showMessage("The edition was created succesfully!");
    }

    private List<String> pickTrials(int maxTrials, List<String> listNames, int size) {
        List<String> list = new ArrayList<>();
        int trial;
        for (int i = 0; i < maxTrials; i++) {
            trial = (int) askNumber("Pick a trial (" + (i + 1) + "/" + maxTrials + "): ",
                    "Enter a number between 1 and " + maxTrials + " (including both)", 1, size);
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
        option = (int) askNumber("Enter an option: ",
                "Enter a number between 1 and 4 (including both).", 1, 4);
        switch (option) {
            case 1 -> enterArticleInfo();
            case 2 -> enterMasterInfo();
            case 3 -> enterPHDefenseInfo();
            case 4 -> enterBudgetRequestInfo();
        }
        menu.showMessage("The trial was created successfully!");
    }

    private void enterBudgetRequestInfo() {
        String trialName = menu.askString("Enter the trial’s name: ");
        String entityName = menu.askString("Enter the entity’s name: ");
        long budgetAmount = (int) askNumber("Enter the budget amount: ",
                "Enter a correct value between 1000 and 2000000000 (including both)", 1000, 2000000000);
        compositorM.createBudgetRequest(trialName, entityName, budgetAmount);
    }

    private void enterPHDefenseInfo() {
        int defenseDifficulty;
        String trialName = menu.askString("Enter the trial’s name: ");
        String fieldOfStudy = menu.askString("Enter the thesis field of study: ");
        defenseDifficulty = (int) askNumber("Enter the defense difficulty: ",
                "Enter a correct value between 0 and 100 (including both)", 1, 10);
        compositorM.createPHD(trialName, fieldOfStudy, defenseDifficulty);
    }

    private void enterMasterInfo() {
        int ectsNumber, creditPassProbability;
        String trialName = menu.askString("Enter the trial’s name: ");
        String mastersName = menu.askString("Enter the master’s name: ");
        ectsNumber = (int) askNumber("Enter the master’s ECTS number: ",
                "Enter a correct value between 60 and 120 (including both)", 60, 120);
        creditPassProbability = (int) askNumber("Enter the credit pass probability: ",
                "Enter a correct value between 0 and 100 (including both)", 0, 100);
        compositorM.createMaster(trialName, mastersName, ectsNumber, creditPassProbability);
    }

    private void enterArticleInfo() {
        int acceptanceProbability, revisionProbability, rejectionProbability;
        String trialName = menu.askString("Enter the trial’s name: ");
        String magazinesName = menu.askString("Enter the journal’s name: ");
        String quartile = menu.askString("Enter the journal’s quartile: ");
        acceptanceProbability = (int) askNumber("Enter the acceptance probability: ",
                "Enter a correct value between 0 and 100 (including both)", 0, 100);
        revisionProbability = (int) askNumber("Enter the revision probability: ",
                "Enter a correct value between 0 and 100 (including both)", 0, 100);
        rejectionProbability = (int) askNumber("Enter the rejection probability: ",
                "Enter a correct value between 0 and 100 (including both)", 0, 100);
        compositorM.createArticle(trialName, magazinesName, quartile, acceptanceProbability, revisionProbability, rejectionProbability);
    }

    private long askNumber (String msg, String errMsg, long min, long max) {
        long num;
        do {
            num = menu.askInteger(msg);
            if (num < min || num > max) menu.errorInput(errMsg);
        } while (num < min || num > max);
        return num;
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
