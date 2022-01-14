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
     * @param i
     * @return
     */
    public List<String> getYearEditionInfo(int i, TrialManager trialManager) {
        List<String> list = new ArrayList<>();
        int cont = 0;
        for (int j = 0; j < editions.size(); j++) {
            // if (cont == i) list = editions.get(j).listInfo(list);
            if (cont == i) {
                list.addAll(editions.get(j).listInfo());
                List<String> trials = editions.get(j).listTrials();
                for (String s : trials) {
                    String name = s.split(" ")[1];
                    Trial trial = trialManager.getByName(name);

                    list.add(s + trial.getType());

                }
            }
            cont++;
        }

        return list;
    }


    /**
     * @return
     */
    public boolean checkCurrentYearEdition() {
        boolean existing = false;
        for (Edition e : editions) {
            e.isYearCoincident(Year.now().getValue());
            existing = true;
        }
        return existing;
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
}
