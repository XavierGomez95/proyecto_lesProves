package persistence.editiondao;

import business.Edition;

import java.util.List;

public interface EditionDAO {
    /**
     * @return list of {@link Edition} read on file.
     */
    List<Edition> readAll();

    /**
     * writes editions in file
     *
     * @param editions updated Edition list
     */
    void writeAll(List<Edition> editions);
}
