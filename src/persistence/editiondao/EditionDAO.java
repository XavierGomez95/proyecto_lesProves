package persistence.editiondao;

import business.Edition;

import java.util.List;

public interface EditionDAO {
    List<Edition> readAll();

    void writeAll(List<Edition> jsonTrials);
}
