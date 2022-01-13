package persistence.trialdao;

import business.trial.Trial;

import java.util.List;

public interface TrialDAO {
    List<Trial> readAll();

    void writeAll(List<Trial> jsonTrials);
}
