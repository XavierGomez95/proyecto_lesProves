package business;

import business.trial.*;

import java.util.ArrayList;
import java.util.List;

public class CompositorManager {
    private List<Edition> editions;
    private List<Trial> trials;
    // Como acceder a las subclases desde aqui o desde trial. se considera high compling o esta correcto

    public CompositorManager(List<Trial> trials, List<Edition> editions) {
        this.trials = trials;
        this.editions = editions;
    }

    public void trialManagement() {

    }

    public PublicArticle createArticle(String trialName, String magazinesName, String quartile, int acceptanceProbability, int revisionProbability, int rejectionProbability) {
        PublicArticle publicArticle = new PublicArticle(magazinesName, quartile, acceptanceProbability, revisionProbability, rejectionProbability, trialName);
        trials.add(publicArticle);
        return publicArticle;
    }

    public StudyMaster createMaster(String trialName, String mastersName, int ectsNumber, int creditPassProbability) {
        StudyMaster studyMaster = new StudyMaster(trialName, mastersName, ectsNumber, creditPassProbability);
        trials.add(studyMaster);
        return studyMaster;
    }

    public PhDefense createPHD(String trialName, String fieldOfStudy, int defenseDifficulty) {
        PhDefense phDefense = new PhDefense(trialName, fieldOfStudy, defenseDifficulty);
        trials.add(phDefense);
        return phDefense;
    }

    public BudgedRequest createBudgetRequest(String trialName, String entityName, int budgetAmount) {
        BudgedRequest budgedRequest = new BudgedRequest(trialName, entityName, budgetAmount);
        trials.add(budgedRequest);
        return budgedRequest;
    }


    public List<String> trialListNames() {
        List<String> list = new ArrayList<>();
        for (Trial t : trials) {
            list.add(t.getName());
        }
        return list;
    }


    public boolean deleteTrial(int i, String trialsName) {
        boolean deleted = false;
        if (trials.get(i).getName().equals(trialsName)) {
            trials.remove(i);
            deleted = true;
        }
        return deleted;
    }

    // Lo he cambiado porque sino me peta (no se por que) :I
    /*
    public boolean deleteTrial(int i, String trialsName) {
        if (trials.get(i).getName().equals(trialsName)) trials.remove(i);
        return trials.get(i).getName().equals(trialsName);
    }
     */

    public List<String> trialListInfo() {
        List<String> list = new ArrayList<>();
        for (Trial t : trials) {
            list.addAll(t.listInfo());
        }
        return list;
    }
}
