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
     * @param editionsYear number of a year (int)
     * @param numberPlayers number of players (between 1 and 5) (int)
     * @param numberTrials number of trials (between 3 and 12) (int).
     * @param list ArrayList of type String
     */
    public void createEdition(int editionsYear, int numberPlayers, int numberTrials, ArrayList<String> list) {
        editions.add(new Edition(editionsYear, numberPlayers, numberTrials, list));
    }

    /**
     * @return an ArrayList of each year of each edition (String).
     */
    public List<String> editionListInfo() {
        List<String> list = new ArrayList<>();
        for (Edition e : editions) {
            list.add(e.getYear());
        }
        return list;
    }

    /**
     * @param editionsYear year of an edition (int).
     * @return true (the year received by parameter is equal to a year of an existing edition), or false (if not).
     */
    public boolean isCoincident(int editionsYear) {
        for (Edition e : editions) {
            if (e.isYearCoincident(editionsYear)) return true;
        }
        return false;
    }

    /**
     * @param index is a specific position in an ArrayList (int).
     * @return an ArrayList of type String.
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
     * @param i is a specific position in an ArrayList (int).
     * @param editionsYear year of an edition (int).
     * @param numberPlayers number of players (int).
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
     * @param index is a specific position in an Edition type List (int).
     * @param year is a specific year used to check if there's this existing year in an edition (int).
     * @return true (if the trial was deleted), or false (if not).
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
     * @param trialsName a name of a trial (String).
     * @return true (if exist a dependency between an edition and a trial), or false (if not).
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
     * @param editionsYear current year (int).
     * @return names of all trials edition
     */
    public List<String> listTrialsEdition(int editionsYear) {
        for (Edition e : editions) {
            if (e.isYearCoincident(editionsYear)) return e.getNameTrials();
        }
        return null;
    }
}
