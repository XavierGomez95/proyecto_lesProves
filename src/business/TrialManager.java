package business;

import business.trial.*;

import java.util.ArrayList;
import java.util.List;

public class TrialManager {
    private List<Trial> trials;


    public TrialManager(List<Trial> trials) {
        this.trials = trials;
    }

    /**
     * @param trialName             name of a public article trial type.
     * @param magazinesName         name of a magazine.
     * @param quartile              String representin a quartile (Q1, Q2, Q3 or Q4).
     * @param acceptanceProbability probability (integer between 0 and 100 including both)
     * @param revisionProbability   probability (integer between 0 and 100 including both)
     * @param rejectionProbability  probability (integer between 0 and 100 including both)
     * @return a new instance of PublicArticle.
     */
    public PublicArticle createArticle(String trialName, String magazinesName, String quartile, int acceptanceProbability, int revisionProbability, int rejectionProbability) {
        PublicArticle publicArticle = new PublicArticle(magazinesName, quartile, acceptanceProbability, revisionProbability, rejectionProbability, trialName);
        trials.add(publicArticle);
        return publicArticle;
    }

    /**
     * @param trialName             name of a master trial type.
     * @param mastersName           name of a master.
     * @param ectsNumber            number of credits of a master (between 60 and 120 including both).
     * @param creditPassProbability probability (integer between 0 and 100 including both)
     * @return a new instance of StudyMaster.
     */
    public StudyMaster createMaster(String trialName, String mastersName, int ectsNumber, int creditPassProbability) {
        StudyMaster studyMaster = new StudyMaster(trialName, mastersName, ectsNumber, creditPassProbability);
        trials.add(studyMaster);
        return studyMaster;
    }

    /**
     * @param trialName         name of a PhD Defense trial type.
     * @param fieldOfStudy      name of a field of study.
     * @param defenseDifficulty an integer representing the defense difficulty of a PhD Defense (between 1 and 10).
     * @return a new instance of PhDefense.
     */
    public PhDefense createPHD(String trialName, String fieldOfStudy, int defenseDifficulty) {
        PhDefense phDefense = new PhDefense(trialName, fieldOfStudy, defenseDifficulty);
        trials.add(phDefense);
        return phDefense;
    }

    /**
     * @param trialName    name of a budget request trial type.
     * @param entityName   name of an entity name.
     * @param budgetAmount long type representing a budget amount (between 1000 and 2000000000 including both).
     * @return a new instance of BudgetRequest.
     */
    public BudgetRequest createBudgetRequest(String trialName, String entityName, long budgetAmount) {
        BudgetRequest budgetRequest = new BudgetRequest(trialName, entityName, budgetAmount);
        trials.add(budgetRequest);
        return budgetRequest;
    }

    /**
     * {@link Trial#getName()}
     *
     * @return a list of names, one for each trial.
     */
    public List<String> trialListNames() {
        List<String> list = new ArrayList<>();
        for (Trial t : trials) {
            list.add(t.getName());
        }
        return list;
    }

    /**
     * {@link Trial#isNameEqual(String)}
     *
     * @param i          specific position of a Trial type List.
     * @param trialsName contains a namr of a trial.
     * @return true (if trial has been deleted), false (if not).
     */
    public boolean deleteTrial(int i, String trialsName) {
        boolean deleted = false;
        if (trials.get(i).isNameEqual(trialsName)) {
            trials.remove(i);
            deleted = true;
        }
        return deleted;
    }

    /**
     * @return a list of a specific trial.
     * {@link Trial#listInfo()}
     */
    public String trialStringInfo(int index) {
        String list = "";
        int len = trials.size();
        for (int i = 0; i < len; i++) {
            if (index == i) list = trials.get(i).listInfo();
        }
        return list;
    }

    /**
     * Compare if the name received by parameter is the same as the name of an existing trial.
     * {@link Trial#checkName(String)}
     *
     * @param trialName is the name of a trial.
     * @return true (if name is unique), false (if not).
     */
    public boolean isTrialNameUnique(String trialName) {
        boolean unique = true;
        for (Trial t : trials) {
            if (t.checkName(trialName)) unique = false;
        }
        return unique;
    }

    /**
     * Gets a trial with a specific name.
     * {@link Trial#isNameEqual(String)}
     *
     * @param s represents a name (type String).
     * @return a specific trial.
     */
    public Trial getByName(String s) {
        Trial trial = null;
        for (Trial t : trials) {
            if (t.isNameEqual(s)) {
                trial = t;
            }
        }
        return trial;
    }

    /**
     * it is used {@link BudgetRequest#calculateFormula(int totalPI)}
     *
     * @param totalPI       all the PI of the players
     * @param budgetRequest the trial you want to execute
     * @return if the trial was passed or not (true/false)
     */
    public boolean executeBudgetRequest(int totalPI, BudgetRequest budgetRequest) {
        return budgetRequest.calculateFormula(totalPI);
    }
}
