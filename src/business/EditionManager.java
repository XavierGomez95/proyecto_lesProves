package business;

import business.trial.Trial;

import java.time.Year;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EditionManager {
    private List<Edition> editions;


    public EditionManager(List<Edition> editions) {
        this.editions = editions;

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
     * @param index
     * @return
     */
    public List<String> getYearEditionInfo(int index, TrialManager trialManager) {
        List<String> list = new ArrayList<>();
        int currentPosition = 0;

        for (Edition edition : editions) {
            if (currentPosition == index) {
                list.addAll(edition.listInfo());
                List<String> nameTrials = edition.getNameTrials();
                for (int i = 0; i < nameTrials.size(); i++) {
                    Trial trial = trialManager.getByName(nameTrials.get(i).split(",")[0]);
                    list.add("\t" + (i + 1) + "- " + nameTrials.get(i) + trial.getType());
                }
            }
            currentPosition++;
        }
        return list;
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

    /**
     * @param trialsName
     * @return
     */
    public boolean dependentTrial(String trialsName) {
        boolean exist = false;
        for (Edition edition : editions) {
            for (String name : edition.getNameTrials()) {
                if (trialsName.equals(name)) {
                    exist = true;
                    break;
                }
            }
        }
        return exist;
    }

    /**
     * @return players number of current edition
     */
    public int checkNumPlayers() {
        int numPlayers = 0;
        for (Edition e : editions) {
            if (e.isYearCoincident(Year.now().getValue())) {
                numPlayers = e.getNumPlayers();
            }

        }
        return numPlayers;

    }

    /**
     * For {@link ExecutionManager}
     *
     * @param editionsYear current year
     * @return names of all trials edition
     */
    public List<String> listTrialsEdition(int editionsYear) {
        for (Edition e : editions) {
            if (e.isYearCoincident(editionsYear)) return e.getNameTrials();
        }
        return null;
    }
}
