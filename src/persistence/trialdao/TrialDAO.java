package persistence.trialdao;

import business.trial.Trial;

import java.util.List;

public interface TrialDAO {
    /**
     * @return list of {@link Trial} read on file.
     */
    List<Trial> readAll();

    /**
     * writes trials in file
     *
     * @param trials updated Trial list
     */
    void writeAll(List<Trial> trials);
}
