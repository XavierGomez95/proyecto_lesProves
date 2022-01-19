package presentation;

import business.EditionManager;
import business.TrialManager;
import business.Edition;
import business.trial.Trial;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class CompositorController {
    private TrialManager trialManager;
    private EditionManager editionManager;
    private Menu menu;

    public CompositorController(Menu menu, List<Trial> trials, List<Edition> editions) {
        this.trialManager = new TrialManager(trials);
        this.editionManager = new EditionManager(editions);
        this.menu = menu;
    }

    /**
     *
     */
    public void run() {
        int mode;
        String option = "e";
        // 4.3
        while (option.equals("a") || option.equals("b") || option.equals("c") || option.equals("d") || option.equals("e")) {
            menu.createNewLine();
            menu.showMessage("Entering management mode...");
            menu.showManagerMenu(); // 4.3
            do {
                mode = menu.askInteger("Enter an option: ");
                if (mode < 1 || mode > 3) {
                    menu.showError("Enter a number between 1 and 3 (included).");
                    menu.createNewLine();
                }
            } while (mode < 1 || mode > 3);
            option = enterMode(mode);
        }
    }

    /**
     * @param mode
     * @return
     */
    private String enterMode(int mode) {
        String option = "";
        switch (mode) {
            case 1 -> option = manageTrials(); // 4.3.1
            case 2 -> option = manageEditions(); // 4.3.2
            case 3 -> {
                menu.createNewLine();
                menu.showSuccess("Shutting down...");
            }
        }
        return option;
    }

    /**
     * @return
     */
    private String manageEditions() {
        String option = "";
        do {
            menu.showEditionsMenu();
            do {
                option = menu.askString("Enter an option: ");
                if (!(option.equals("a") || option.equals("b") || option.equals("c") || option.equals("d") || option.equals("e"))) {
                    menu.showError("Enter a letter.");
                    menu.createNewLine();
                }
            } while (!(option.equals("a") || option.equals("b") || option.equals("c") || option.equals("d") || option.equals("e")));

            //menu.createNewLine();

            switch (option) {
                case "a" -> createEdition();
                case "b" -> listEdition();
                case "c" -> duplicateEdition();
                case "d" -> deleteEdition();
            }
        } while (!(option.equals("e")));
        return option;
    }

    /**
     *
     */
    private void deleteEdition() {
        List<String> list = editionManager.editionListInfo();
        int year, numberPlayers;
        if (!list.isEmpty()) {
            menu.showMessage("Which edition do you want to delete?");
            menu.createNewLine();
            menu.menuTrials(list); // 4.3.2.3

            int size = list.size() + 1; // back es list.size() + 1
            int option = (int) askNumber("Enter an option: ",
                    "Enter a correct opcion between " + 1 + " and " + size, 1, size);

            if (option < size) {
                menu.createNewLine();
                year = menu.askInteger("Enter the edition's year for confirmation: ");

                boolean deleted = editionManager.deleteEdition(option - 1, year);

                if (deleted) {
                    menu.createNewLine();
                    menu.showSuccess("The edition was successfully deleted!");
                } else {
                    menu.createNewLine();
                    menu.showSuccess("The edition has not been deleted!");
                }
            }
        }
    }

    /**
     *
     */
    private void duplicateEdition() {
        List<String> list = editionManager.editionListInfo();
        int year, numberPlayers;
        if (!list.isEmpty()) {
            menu.showMessage("Which edition do you want to clone?");
            menu.createNewLine();
            menu.menuTrials(list); // 4.3.2.3

            int size = list.size() + 1;
            int option = (int) askNumber("Enter an option: ",
                    "Enter a correct opcion between " + 1 + " and " + size, 1, size);
            if (option < size) {
                //list = pickTrials(size, list, list.size());
                menu.createNewLine();
                do {
                    year = menu.askInteger("Enter the edition's year: ");
                    if (editionManager.isCoincident(year)) {
                        menu.showError("This year exists, please enter another year.");
                        menu.createNewLine();
                    }
                    if (year < Year.now().getValue()) {
                        menu.showError("Enter the curremt year or upper (dont repeat existing year editions).");
                        menu.createNewLine();
                    }
                } while (year < Year.now().getValue() || editionManager.isCoincident(year));
                numberPlayers = (int) askNumber("Enter the initial number of players: ",
                        "Enter a number between 1 and 5 (including both)", 1, 5);
                editionManager.duplicateEdition(option - 1, year, numberPlayers);

                menu.createNewLine();
                menu.showSuccess("The edition was duplicated succesfully!");
            }
        } else {
            menu.showError("There are no trials. Please create first a trial.");
        }
    }

    /**
     *
     */
    private void listEdition() {
        List<String> listEditions = editionManager.editionListInfo();
        if (!listEditions.isEmpty()) {
            menu.createNewLine();
            menu.showMessage("Here are the current editions, do you want to see more details or go back?");
            menu.createNewLine();
            menu.menuTrials(listEditions); // 4.3.2.2

            int size = listEditions.size() + 1;
            int option = (int) askNumber("Enter an option: ",
                    "Enter a correct option between " + 1 + " and " + size, 1, size);
            if (option <= size) {
                List<String> listEditionsInfo = editionManager.getYearEditionInfo(option - 1, trialManager);
                menu.createNewLine();
                menu.showListEditionByYear(listEditionsInfo);
            }
        } else {
            menu.createNewLine();
            menu.showError("There are no trials. Please create first a trial.");
        }
    }

    /**
     *
     */
    private void createEdition() {
        List<String> list = trialManager.trialListNames();
        int editionsYear, numberPlayers, numberTrials;
        if (list.size() > 0) {
            menu.createNewLine();
            do {
                editionsYear = menu.askInteger("Enter the edition's year: ");
                if (editionManager.isCoincident(editionsYear)) {
                    menu.showError("This year exists, please enter another year.");
                    menu.createNewLine();
                }
                if (editionsYear < Year.now().getValue()) {
                    menu.showError("Enter the curremt year or upper (dont repeat existing year editions).");
                    menu.createNewLine();
                }
            } while (editionsYear < Year.now().getValue() || editionManager.isCoincident(editionsYear));

            numberPlayers = (int) askNumber("Enter the initial number of players: ",
                    "Enter a number between 1 and 5 (including both)", 1, 5);
            numberTrials = (int) askNumber("Enter the number of trials: ",
                    "Enter a number between 3 and 12 (including both)", 3, 12);
            menu.createNewLine();
            menu.menuEditions(list);
            menu.createNewLine();

            list = pickTrials(numberTrials, list, list.size());

            editionManager.createEdition(editionsYear, numberPlayers, numberTrials, (ArrayList<String>) list);

            menu.createNewLine();
            menu.showSuccess("The edition was created succesfully!");
        } else {
            menu.showError("To create an edition, first, you must create trials.");
            menu.createNewLine();
        }
    }

    /**
     * @param maxTrials
     * @param listNames
     * @param size
     * @return
     */
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

    /**
     * @return
     */
    private String manageTrials() {
        String option = "";
        do {
            menu.showTrialsMenu();
            do {
                option = menu.askString("Enter an option: ");
                if (!(option.equals("a") || option.equals("b") || option.equals("c") || option.equals("d"))) {
                    menu.showError("Enter a letter.");
                    menu.createNewLine();
                }
            } while (!(option.equals("a") || option.equals("b") || option.equals("c") || option.equals("d")));

            if (!option.equals("d")) menu.createNewLine();

            switch (option) {
                case "a" -> createTrial();
                case "b" -> listTrial();
                case "c" -> deleteTrial();
            }
        } while (!(option.equals("d")));
        return option;
    }

    /**
     *
     */
    private void createTrial() {
        int option;

        menu.showTrialTypesMenu();
        option = (int) askNumber("Enter an option: ",
                "Enter a number between 1 and 4 (including both).", 1, 4);
        menu.createNewLine();
        switch (option) {
            case 1 -> enterArticleInfo();
            case 2 -> enterMasterInfo();
            case 3 -> enterPHDefenseInfo();
            case 4 -> enterBudgetRequestInfo();
        }
        menu.createNewLine();
        menu.showSuccess("The trial was created successfully!");
    }

    /**
     *
     */
    private void enterBudgetRequestInfo() {
        String trialName = askTrialName();
        String entityName = menu.askString("Enter the entity’s name: ");
        long budgetAmount = (int) askNumber("Enter the budget amount: ",
                "Enter a correct value between 1000 and 2000000000 (including both)", 1000, 2000000000);
        trialManager.createBudgetRequest(trialName, entityName, budgetAmount);
    }

    /**
     *
     */
    private void enterPHDefenseInfo() {
        int defenseDifficulty;
        String trialName = askTrialName();
        String fieldOfStudy = menu.askString("Enter the thesis field of study: ");
        defenseDifficulty = (int) askNumber("Enter the defense difficulty: ",
                "Enter a correct value between 1 and 10 (including both)", 1, 10);
        trialManager.createPHD(trialName, fieldOfStudy, defenseDifficulty);
    }

    /**
     *
     */
    private void enterMasterInfo() {
        int ectsNumber, creditPassProbability;
        String trialName = askTrialName();
        String mastersName = askTypeTrialName("Enter the master’s name: ",
                "The master's name must not be empty!");
        ectsNumber = (int) askNumber("Enter the master’s ECTS number: ",
                "Enter a correct value between 60 and 120 (including both)", 60, 120);
        creditPassProbability = (int) askNumber("Enter the credit pass probability: ",
                "Enter a correct value between 0 and 100 (including both)", 0, 100);
        trialManager.createMaster(trialName, mastersName, ectsNumber, creditPassProbability);
    }

    /**
     *
     */
    private void enterArticleInfo() {
        int acceptanceProbability, revisionProbability, rejectionProbability;
        String trialName = askTrialName();
        String magazinesName = askTypeTrialName("Enter the journal’s name: ",
                "The magazine's name must not be empty!");
        String quartile = askQuartile();
        int max = 100;
        acceptanceProbability = (int) askNumber("Enter the acceptance probability: ",
                "Enter a correct value between 0 and " + max + " (including both)", 0, max);
        max = 100 - acceptanceProbability;
        revisionProbability = (int) askNumber("Enter the revision probability: ",
                "Enter a correct value between 0 and " + max + " (including both)", 0, max);
        max = 100 - acceptanceProbability - revisionProbability;
        rejectionProbability = (int) askNumber("Enter the rejection probability: ",
                "Enter a correct value between 0 and " + max +" (including both)", 0, max);
        trialManager.createArticle(trialName, magazinesName, quartile, acceptanceProbability, revisionProbability, rejectionProbability);
    }

    /**
     *
     * @return
     */
    private String askQuartile() {
        String quartile;
        do {
            quartile = menu.askString("Enter the journal’s quartile: ");
            if (!quartile.equals("Q1") && !quartile.equals("Q2") && !quartile.equals("Q3") && !quartile.equals("Q4")) {
                menu.showError("Enter a correct value (Q1, Q2, Q3, Q4).");
                menu.createNewLine();
            }
        } while (!quartile.equals("Q1") && !quartile.equals("Q2") && !quartile.equals("Q3") && !quartile.equals("Q4"));
        return quartile;
    }

    /**
     *
     * @param msg
     * @param error
     * @return
     */
    private String askTypeTrialName(String msg, String error) {
        String magazine;
        do {
            magazine = menu.askString(msg);
            if (magazine.isEmpty()) {
                menu.showError(error);
                menu.createNewLine();
            }
        } while (magazine.isEmpty());
        return magazine;
    }

    /**
     *
     * @return
     */
    private String askTrialName() {
        String trialName;
        do {
            trialName = menu.askString("Enter the trial’s name: ");
            if (trialName.isEmpty()) {
                menu.showError("The trial's name must not be empty!");
                menu.createNewLine();
            }
            if (!trialManager.isTrialNameUnique(trialName)) {
                menu.showError("This name is already in use.");
                menu.createNewLine();
            }
        } while (trialName.isEmpty() || !trialManager.isTrialNameUnique(trialName));
        return trialName;
    }

    /**
     * @param msg
     * @param errMsg
     * @param min
     * @param max
     * @return
     */
    private long askNumber(String msg, String errMsg, long min, long max) {
        long num;
        do {
            num = menu.askInteger(msg);
            if (num < min || num > max) {
                if (max > 0) menu.showError(errMsg);
                else menu.showMessage("The probability must be 0.");
                menu.createNewLine();
            }
        } while (num < min || num > max);
        return num;
    }

    // REVISAR SI ESTO ESTA BIEN AQUI O VA EN LA CLASE TRIAL

    /**
     *
     */
    private void listTrial() {
        int inputOption, backOption;

        menu.showMessage("Here are the current trials, do you want to see more details or go back?");
        menu.createNewLine();

        List<String> listNames = trialManager.trialListNames();

        backOption = listNames.size() + 1;

        if (!listNames.isEmpty()) {
            menu.menuTrials(listNames);

            do {
                inputOption = (int) askNumber("Enter an option: ",  new StringBuilder("Incorrect input. Enter a number between ")
                        .append(1).append(" and ").append(backOption).toString(), 1, backOption);
            } while (inputOption > backOption || inputOption < 1);
            if (inputOption != backOption) menu.createNewLine();



            // ¡¡¡AQUI ES DONDE HE DE HACER EL FILTRO SOLO PARA MOSTRAR LA INFORMACION DE LA OPCION SELECCIONADA!!!

            if (inputOption != backOption) {
                String info = trialManager.trialStringInfo(inputOption - 1);
                if (!info.isEmpty()) menu.showMessage(info); // 4.3.1.2
                else menu.showError("There are no trials. Please create first a trial.");
            }
        }
    }

    /**
     *
     */
    private void deleteTrial() {
        int indexTrialToDelete;
        List<String> list = trialManager.trialListNames();

        menu.showMessage("Which trial do you want to delete?");
        menu.createNewLine();

        if (!list.isEmpty()) {
            menu.menuTrials(list); // 4.3.1.3
            do {
                indexTrialToDelete = menu.askInteger("Enter an option: ");
                if (indexTrialToDelete <= list.size() && indexTrialToDelete > 0) {
                    menu.createNewLine();
                    String trialsName = menu.askString("Enter the trial’s name for confirmation: ");
                    if (editionManager.dependentTrial(trialsName)) {
                        menu.showError("The trial cannot be deleted because one edition depends on it.");
                    } else {

                        boolean deleted = trialManager.deleteTrial(indexTrialToDelete - 1, trialsName);

                        if (deleted) {
                            menu.createNewLine();
                            menu.showSuccess("The trial was successfully deleted.");
                        } else menu.showError("The trial has not been successfully deleted.");
                    }
                }
                if (indexTrialToDelete <= 0 || indexTrialToDelete > list.size() + 1) {
                    menu.showError("Enter a value between 1 and " + (list.size() + 1));
                    menu.createNewLine();
                }
            } while (indexTrialToDelete <= 0 || indexTrialToDelete > list.size() + 1);
        } else menu.showError("There are no trials. Please create first a trial.");
    }
}
