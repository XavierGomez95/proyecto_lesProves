package business;

import business.trial.Trial;

import java.time.Year;
import java.util.List;

public class ConductorManager {
    private List<Edition> editions;
    private List<Trial> trials;

    public ConductorManager(List<Trial> trials, List<Edition> editions) {
        this.editions = editions;
        this.trials = trials;
    }

    public void addPlayerNames () {

    }

    public void executeTrial () {

    }

    public boolean checkCurrentYearEdition() {
        boolean existing = false;
        for (Edition e : editions) {
            e.isYearCoincident(Year.now().getValue());
            existing = true;
        }
        return existing;
    }
}
