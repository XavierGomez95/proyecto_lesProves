package business;

import business.trial.*;

import java.util.ArrayList;
import java.util.Iterator;
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

    /**
     * @return
     */
    public List<String> trialListInfo() {
        List<String> list = new ArrayList<>();
        for (Trial t : trials) {
            list.addAll(t.listInfo());
        }
        return list;
    }

    /**
     * @param editionsYear
     * @param numberPlayers
     * @param numberTrials
     * @param list
     */
    public void createEdition(int editionsYear, int numberPlayers, int numberTrials, ArrayList<String> list) {
        editions.add(new Edition(editionsYear, numberPlayers, numberTrials, list));
    }

    /**
     * @return
     */
    public List<String> editionListInfo() {
        List<String> list = new ArrayList<>();
        for (Edition e : editions) {
            list.add(e.getYear());
        }
        return list;
    }

    /**
     * @param editionsYear
     * @return
     */
    public boolean isCoincident(int editionsYear) {
        for (Edition e : editions) {
            if (e.isYearCoincident(editionsYear)) return true;
        }
        return false;
    }

    /**
     * @param i
     * @return
     */
    public List<String> getYearEditionInfo(int i) {
        List<String> list = new ArrayList<>();
        int cont = 0;
        for (int j = 0; j < editions.size(); j++) {
            // if (cont == i) list = editions.get(j).listInfo(list);
            if (cont == i) {
                list.addAll(editions.get(j).listInfo());
                List<String> trials = editions.get(j).listTrials();
                for (String s : trials) {
                    String name = s.split(" ")[1];
                    Trial trial = getByName(name);

                    list.add(s + trial.getType());

                }
            }
            cont++;
        }

        return list;
    }


    public Trial getByName(String s) {
        Trial trial = null;
        for (Trial t : trials) {
            if (t.getName().equals(s)) {
                trial = t;
            }
        }
        return trial;
    }

    /**
     * @param i
     * @param editionsYear
     * @param numberPlayers
     */
    public void duplicateEdition(int i, int editionsYear, int numberPlayers) {
        ArrayList<String> listNameTrials = new ArrayList<>();
        int cont = 0;
        for (Edition e : editions) {
            if (cont == i) {
                listNameTrials = e.getNameTrials();
            }
            cont++;
        }
        editions.add(new Edition(editionsYear, numberPlayers, listNameTrials.size(), listNameTrials));
    }

    /**
     * @param index
     * @param year
     * @return
     */
    public boolean deleteEdition(int index, int year) {
        boolean deleted = false;
        int cont = 0;
        Iterator<Edition> e = editions.iterator();
        while (e.hasNext()) {
            Edition edition = e.next();
            if (edition.isYearCoincident(year) && index == cont) {
                e.remove();
                deleted = true;
            }
            cont++;
        }

        return deleted;
    }

    public boolean isTrialNameUnique(String trialName) {
        boolean unique = true;
        for (Trial t : trials) {
            if (t.checkName(trialName)) unique = false;
        }
        return unique;
    }
}
