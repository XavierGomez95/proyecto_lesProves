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
     * @param trialName
     * @param magazinesName
     * @param quartile
     * @param acceptanceProbability
     * @param revisionProbability
     * @param rejectionProbability
     * @return
     */
    public PublicArticle createArticle(String trialName, String magazinesName, String quartile, int acceptanceProbability, int revisionProbability, int rejectionProbability) {
        PublicArticle publicArticle = new PublicArticle(magazinesName, quartile, acceptanceProbability, revisionProbability, rejectionProbability, trialName);
        trials.add(publicArticle);
        return publicArticle;
    }

    /**
     * @param trialName
     * @param mastersName
     * @param ectsNumber
     * @param creditPassProbability
     * @return
     */
    public StudyMaster createMaster(String trialName, String mastersName, int ectsNumber, int creditPassProbability) {
        StudyMaster studyMaster = new StudyMaster(trialName, mastersName, ectsNumber, creditPassProbability);
        trials.add(studyMaster);
        return studyMaster;
    }

    /**
     * @param trialName
     * @param fieldOfStudy
     * @param defenseDifficulty
     * @return
     */
    public PhDefense createPHD(String trialName, String fieldOfStudy, int defenseDifficulty) {
        PhDefense phDefense = new PhDefense(trialName, fieldOfStudy, defenseDifficulty);
        trials.add(phDefense);
        return phDefense;
    }

    /**
     * @param trialName
     * @param entityName
     * @param budgetAmount
     * @return
     */
    public BudgedRequest createBudgetRequest(String trialName, String entityName, long budgetAmount) {
        BudgedRequest budgedRequest = new BudgedRequest(trialName, entityName, budgetAmount);
        trials.add(budgedRequest);
        return budgedRequest;
    }

    /**
     * @return
     */
    public List<String> trialListNames() {
        List<String> list = new ArrayList<>();
        for (Trial t : trials) {
            list.add(t.getName());
        }
        return list;
    }

    /**
     * @param i
     * @param trialsName
     * @return
     */
    public boolean deleteTrial(int i, String trialsName) {
        boolean deleted = false;
        if (trials.get(i).isNameEqual(trialsName)) {
            trials.remove(i);
            deleted = true;
        }
        return deleted;
    }

    /*
    public boolean deleteTrial(int i, String trialsName) {
        boolean deleted = false;
        if (trials.get(i).getName().equals(trialsName)) {
            trials.remove(i);
            deleted = true;
        }
        return deleted;
    }
     */

    // Lo he cambiado porque sino me peta (no se por que) :I
    /*
    public boolean deleteTrial(int i, String trialsName) {
        if (trials.get(i).getName().equals(trialsName)) trials.remove(i);
        return trials.get(i).getName().equals(trialsName);
    }
     */

    /**
     * @return a list of a specific trial.
     */
    public String trialStringInfo(int index) {
        String list = "";

        int len = trials.size();
        for (int i = 0; i < len; i++) {
            if (index == i) list = trials.get(i).listInfo();
            //list.addAll(t.listInfo());
        }
        return list;
    }

    /**
     *
     * @param trialName
     * @return
     */
    public boolean isTrialNameUnique(String trialName) {
        boolean unique = true;
        for (Trial t : trials) {
            if (t.checkName(trialName)) unique = false;
        }
        return unique;
    }

    /**
     *
     * @param s
     * @return
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
}
